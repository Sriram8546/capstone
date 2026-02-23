(function(){
  function getParam(name){
    const p=new URLSearchParams(window.location.search);
    return p.get(name);
  }

  function getCurrentUser(){
    try{
      const d=localStorage.getItem('shoes_current_user');
      return d?JSON.parse(d):null;
    }catch(e){return null}
  }

  document.addEventListener('DOMContentLoaded',function(){
    const orderId=getParam('orderId');
    const idEl=document.getElementById('order-id');
    const totalEl=document.getElementById('order-total');
    const dateEl=document.getElementById('order-date');

    if(!orderId){
      const user=getCurrentUser();
      let orders=[];
      try{
        const d=localStorage.getItem('shoes_orders_'+user?.email);
        orders=d?JSON.parse(d):[];
      }catch(e){}
      const last=orders[0];
      if(last){
        if(idEl)idEl.textContent='Order ID: '+last.id;
        if(totalEl)totalEl.textContent='Total: $'+last.total.toFixed(2);
        if(dateEl)dateEl.textContent='Date: '+new Date(last.date).toLocaleString();
      }else{
        if(idEl)idEl.textContent='Order placed successfully!';
      }
    }else{
      const user=getCurrentUser();
      let orders=[];
      try{
        const d=localStorage.getItem('shoes_orders_'+user?.email);
        orders=d?JSON.parse(d):[];
      }catch(e){}
      const order=orders.find(o=>o.id===orderId);
      if(order){
        if(idEl)idEl.textContent='Order ID: '+order.id;
        if(totalEl)totalEl.textContent='Total: $'+order.total.toFixed(2);
        if(dateEl)dateEl.textContent='Date: '+new Date(order.date).toLocaleString();
      }else{
        if(idEl)idEl.textContent='Order ID: '+orderId;
      }
    }
  });
})();
