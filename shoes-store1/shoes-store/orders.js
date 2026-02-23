(function(){
  function getCurrentUser(){
    try{
      const d=localStorage.getItem('shoes_current_user');
      return d?JSON.parse(d):null;
    }catch(e){return null}
  }

  document.addEventListener('DOMContentLoaded',function(){
    const user=getCurrentUser();
    const listEl=document.getElementById('orders-list');
    const emptyEl=document.getElementById('orders-empty');

    let orders=[];
    try{
      const d=localStorage.getItem('shoes_orders_'+user?.email);
      orders=d?JSON.parse(d):[];
    }catch(e){}

    if(orders.length===0){
      if(listEl)listEl.classList.add('hidden');
      if(emptyEl)emptyEl.classList.remove('hidden');
      return;
    }

    if(emptyEl)emptyEl.classList.add('hidden');
    if(listEl){
      listEl.classList.remove('hidden');
      listEl.innerHTML=orders.map(o=>`
        <div class="order-card">
          <div class="order-header">
            <span class="order-id-text">${o.id}</span>
            <span class="order-date-text">${new Date(o.date).toLocaleString()}</span>
            <span class="order-total-text">$${o.total.toFixed(2)}</span>
          </div>
          <div class="order-items">
            ${o.items.map(it=>`
              <div class="order-item">
                <span class="order-item-name">${it.name} × ${it.quantity}</span>
                <span>$${(it.price*it.quantity).toFixed(2)}</span>
              </div>
            `).join('')}
          </div>
        </div>
      `).join('');
    }
  });
})();
