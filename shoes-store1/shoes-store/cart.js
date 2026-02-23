(function(){
  function getCart(){
    const user=getCurrentUser();
    if(!user)return{};
    try{
      const d=localStorage.getItem('shoes_cart_'+user.email);
      return d?JSON.parse(d):{};
    }catch(e){return{}}
  }

  function saveCart(cart){
    const user=getCurrentUser();
    if(!user)return;
    localStorage.setItem('shoes_cart_'+user.email,JSON.stringify(cart));
    const badge=document.getElementById('cart-count');
    if(badge){badge.textContent=Object.values(cart).reduce((s,it)=>s+it.quantity,0);}
    render();
  }

  function updateQuantity(id,delta){
    const cart=getCart();
    const item=cart[id];
    if(!item)return;
    item.quantity+=delta;
    if(item.quantity<=0){delete cart[id];}else{cart[id]=item;}
    saveCart(cart);
  }

  function removeItem(id){
    const cart=getCart();
    delete cart[id];
    saveCart(cart);
  }

  function render(){
    const cart=getCart();
    const items=Object.values(cart);
    const itemsEl=document.getElementById('cart-items');
    const emptyEl=document.getElementById('cart-empty');
    const summaryEl=document.getElementById('cart-summary');
    const checkoutBtn=document.getElementById('checkout-btn');

    if(items.length===0){
      if(itemsEl){itemsEl.innerHTML='';itemsEl.classList.add('hidden');}
      if(emptyEl){emptyEl.classList.remove('hidden');}
      if(summaryEl){summaryEl.classList.add('hidden');}
      return;
    }

    if(emptyEl){emptyEl.classList.add('hidden');}
    if(summaryEl){summaryEl.classList.remove('hidden');}
    if(itemsEl){
      itemsEl.classList.remove('hidden');
      itemsEl.innerHTML=items.map(it=>`
        <div class="cart-item" data-id="${it.id}">
          <img class="cart-item-img" src="${it.image}" alt="${it.name}" width="80" height="80" loading="lazy">
          <div>
            <div class="cart-item-name">${it.name}</div>
            <div class="cart-item-price">$${it.price.toFixed(2)}</div>
          </div>
          <div class="cart-item-qty">
            <button type="button" aria-label="Decrease quantity" data-action="dec">−</button>
            <span>${it.quantity}</span>
            <button type="button" aria-label="Increase quantity" data-action="inc">+</button>
          </div>
          <div>
            <div class="cart-item-total">$${(it.price*it.quantity).toFixed(2)}</div>
            <button type="button" class="cart-item-remove" data-action="remove">Remove</button>
          </div>
        </div>
      `).join('');
    }

    const total=items.reduce((s,it)=>s+it.price*it.quantity,0);
    const subtotalEl=document.getElementById('subtotal');
    const grandTotalEl=document.getElementById('grand-total');
    if(subtotalEl)subtotalEl.textContent='$'+total.toFixed(2);
    if(grandTotalEl)grandTotalEl.textContent='$'+total.toFixed(2);

  }

  document.addEventListener('DOMContentLoaded',function(){
    render();
    document.getElementById('cart-items')?.addEventListener('click',function(e){
      const item=e.target.closest('.cart-item');
      if(!item)return;
      const id=item.dataset.id;
      const btn=e.target.closest('button');
      if(!btn)return;
      const act=btn.dataset.action;
      if(act==='inc')updateQuantity(id,1);
      else if(act==='dec')updateQuantity(id,-1);
      else if(act==='remove')removeItem(id);
    });
  });
})();
