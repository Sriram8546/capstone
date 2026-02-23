(function(){
  const grid=document.getElementById('shoes-grid');
  const searchInput=document.getElementById('search-input');
  const noResults=document.getElementById('no-results');

  function renderShoes(shoes){
    if(!grid)return;
    grid.innerHTML=shoes.map(s=>`
      <article class="shoe-card" role="listitem">
        <a href="shoe-detail.html?id=${s.id}">
          <img src="${s.image}" alt="${s.name}" width="260" height="260" loading="lazy">
        </a>
        <div class="shoe-card-content">
          <h2 class="shoe-card-title">${s.name}</h2>
          <p class="shoe-card-price">$${s.price.toFixed(2)}</p>
          ${s.rating?`<p class="shoe-card-rating"><span>★</span> ${s.rating}/5</p>`:''}
          <a href="shoe-detail.html?id=${s.id}" class="btn-primary">View Details</a>
        </div>
      </article>
    `).join('');
  }

  function filterShoes(query){
    const shoes=getShoes();
    const q=query.trim().toLowerCase();
    const filtered=q?shoes.filter(s=>s.name.toLowerCase().includes(q)||s.category.toLowerCase().includes(q)):shoes;
    renderShoes(filtered);
    if(noResults){noResults.classList.toggle('hidden',filtered.length>0);}
  }

  if(searchInput){
    searchInput.addEventListener('input',function(){filterShoes(this.value);});
    searchInput.addEventListener('search',function(){filterShoes(this.value);});
  }

  document.addEventListener('DOMContentLoaded',function(){filterShoes('');});
})();
