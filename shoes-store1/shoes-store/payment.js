(function(){
  function getCurrentUser(){
    try{
      const d=localStorage.getItem('shoes_current_user');
      return d?JSON.parse(d):null;
    }catch(e){return null}
  }

  function getCart(){
    const user=getCurrentUser();
    if(!user)return{};
    try{
      const d=localStorage.getItem('shoes_cart_'+user.email);
      return d?JSON.parse(d):{};
    }catch(e){return{}}
  }

  function formatCardNum(v){
    return v.replace(/\D/g,'').replace(/(\d{4})(?=\d)/g,'$1 ').slice(0,19);
  }

  function formatExpiry(v){
    const n=v.replace(/\D/g,'');
    if(n.length>=2){return n.slice(0,2)+'/'+n.slice(2,4);}
    return n;
  }

  document.addEventListener('DOMContentLoaded',function(){
    const cart=getCart();
    const items=Object.values(cart);
    const total=items.reduce((s,it)=>s+it.price*it.quantity,0);
    const totalEl=document.getElementById('payment-total');
    if(totalEl)totalEl.textContent='$'+total.toFixed(2);

    if(items.length===0){window.location.href='cart.html';return;}

    const cardNum=document.getElementById('cardnumber');
    const expiry=document.getElementById('expiry');
    const cvv=document.getElementById('cvv');
    if(cardNum){cardNum.addEventListener('input',function(){this.value=formatCardNum(this.value);});}
    if(expiry){expiry.addEventListener('input',function(){this.value=formatExpiry(this.value);});}
    if(cvv){cvv.addEventListener('input',function(){this.value=this.value.replace(/\D/g,'').slice(0,4);});}

    const tabs=document.querySelectorAll('.payment-tab');
    const cardFields=document.getElementById('card-fields');
    const upiFields=document.getElementById('upi-fields');
    let method='card';
    tabs.forEach(t=>{
      t.addEventListener('click',function(){
        method=this.dataset.method;
        tabs.forEach(x=>x.classList.remove('active'));
        this.classList.add('active');
        cardFields?.classList.toggle('hidden',method!=='card');
        upiFields?.classList.toggle('hidden',method!=='upi');
      });
    });

    const form=document.getElementById('payment-form');
    const errorEl=document.getElementById('payment-error');
    const payBtn=document.getElementById('pay-btn');

    form?.addEventListener('submit',function(e){
      e.preventDefault();
      errorEl?.classList.add('hidden');
      const fullname=document.getElementById('fullname').value.trim();
      const address=document.getElementById('address').value.trim();

      if(!fullname){showError('Please enter your full name');return;}
      if(!address){showError('Please enter billing address');return;}

      if(method==='card'){
        const cn=cardNum.value.replace(/\s/g,'');
        const ex=expiry.value;
        const c=cvv.value;
        if(cn.length<16){showError('Enter a valid card number');return;}
        if(!/^\d{2}\/\d{2}$/.test(ex)){showError('Enter expiry as MM/YY');return;}
        if(c.length<3){showError('Enter valid CVV');return;}
      }else{
        const upi=document.getElementById('upi-id').value.trim();
        if(!upi||!upi.includes('@')){showError('Enter valid UPI ID');return;}
      }

      payBtn.disabled=true;
      payBtn.textContent='Processing...';
      setTimeout(function(){
        const orderId='ORD-'+Date.now();
        const order={id:orderId,items:items,total:total,date:new Date().toISOString()};
        let orders=[];
        try{
          const d=localStorage.getItem('shoes_orders_'+getCurrentUser().email);
          orders=d?JSON.parse(d):[];
        }catch(x){}
        orders.unshift(order);
        localStorage.setItem('shoes_orders_'+getCurrentUser().email,JSON.stringify(orders));
        localStorage.removeItem('shoes_cart_'+getCurrentUser().email);
        window.location.href='order-success.html?orderId='+orderId;
      },1200);
    });

    function showError(m){errorEl.textContent=m;errorEl.classList.remove('hidden');}
  });
})();
