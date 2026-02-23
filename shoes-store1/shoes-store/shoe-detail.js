(function(){
  function getParam(name){
    const p=new URLSearchParams(window.location.search);
    return p.get(name);
  }

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
  }

  function addToCart(shoe){
    const cart=getCart();
    if(cart[shoe.id]){cart[shoe.id].quantity++;}else{cart[shoe.id]={...shoe,quantity:1};}
    saveCart(cart);
  }

  document.addEventListener('DOMContentLoaded',function(){
    const id=getParam('id');
    const container=document.getElementById('shoe-detail');
    if(!id||!container){
      container.innerHTML='<p>Shoe not found.</p>';
      return;
    }
    const shoe=getShoeById(id);
    if(!shoe){
      container.innerHTML='<p>Shoe not found.</p>';
      return;
    }

    container.innerHTML=`
      <div class="shoe-detail-image">
        <img src="${shoe.image}" alt="${shoe.name}" width="500" height="500" loading="eager">
      </div>
      <div class="shoe-detail-info">
        <span class="shoe-detail-category">${shoe.category}</span>
        <h1>${shoe.name}</h1>
        <p class="shoe-detail-price">$${shoe.price.toFixed(2)}</p>
        ${shoe.rating?`<p class="shoe-detail-rating"><span style="color:#ffc107">★</span> ${shoe.rating}/5</p>`:''}
        <p class="shoe-detail-desc">${shoe.description}</p>
        <button type="button" class="btn-primary" id="add-to-cart-btn">Add to Cart</button>
      </div>
    `;

    document.getElementById('add-to-cart-btn').addEventListener('click',function(){
      addToCart(shoe);
      const btn=this;
      const orig=btn.textContent;
      btn.textContent='Added!';
      btn.disabled=true;
      setTimeout(function(){btn.textContent=orig;btn.disabled=false;},1500);
    });
  });
})();
