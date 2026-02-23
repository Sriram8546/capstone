const SHOES_DATA=[
  {id:'1',name:'Air Max Runner Pro',price:129.99,category:'Running',rating:4.8,image:'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400&h=400&fit=crop',description:'Lightweight performance running shoes with responsive cushioning and breathable mesh upper. Perfect for long-distance runs and daily training.'},
  {id:'2',name:'Classic White Sneakers',price:89.99,category:'Casual',rating:4.6,image:'https://images.unsplash.com/photo-1606107557195-0e29a4b5b4aa?w=400&h=400&fit=crop',description:'Timeless casual sneakers with premium leather construction. Versatile design that pairs with any outfit for everyday comfort.'},
  {id:'3',name:'Elite Basketball Pro',price:159.99,category:'Sports',rating:4.9,image:'https://images.unsplash.com/photo-1608231387042-66d1773070a5?w=400&h=400&fit=crop',description:'High-performance basketball shoes with exceptional ankle support and court grip. Designed for serious athletes who demand the best.'},
  {id:'4',name:'Oxford Dress Shoes',price:199.99,category:'Formal',rating:4.7,image:'https://images.unsplash.com/photo-1614252235316-8c857d38b5f4?w=400&h=400&fit=crop',description:'Elegant leather oxford shoes perfect for formal occasions. Handcrafted with attention to detail for a refined appearance.'},
  {id:'5',name:'Trail Blazer Hiking',price:149.99,category:'Sports',rating:4.5,image:'https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77?w=400&h=400&fit=crop',description:'Rugged hiking boots with waterproof construction and superior traction. Built for challenging terrain and outdoor adventures.'},
  {id:'6',name:'Street Style Loafers',price:79.99,category:'Casual',rating:4.4,image:'https://images.unsplash.com/photo-1533867617858-e7b97e060509?w=400&h=400&fit=crop',description:'Comfortable slip-on loafers with modern street style. Easy to wear and perfect for a relaxed yet polished look.'},
  {id:'7',name:'Marathon Elite 2.0',price:179.99,category:'Running',rating:4.9,image:'https://images.unsplash.com/photo-1605348532760-6753d2c43329?w=400&h=400&fit=crop',description:'Carbon plate technology for maximum energy return. Elite marathon shoes worn by professional runners worldwide.'},
  {id:'8',name:'Minimalist Canvas',price:59.99,category:'Casual',rating:4.3,image:'https://images.unsplash.com/photo-1549298916-b41d501d3772?w=400&h=400&fit=crop',description:'Simple, comfortable canvas shoes for everyday wear. Sustainable materials and classic design that never goes out of style.'}
];

const STORAGE_KEYS={users:'shoes_users',currentUser:'shoes_current_user',cart:'shoes_cart',orders:'shoes_orders'};

function getShoes(){return SHOES_DATA;}
function getShoeById(id){return SHOES_DATA.find(s=>s.id===id);}

function getCurrentUser(){
  try{const d=localStorage.getItem(STORAGE_KEYS.currentUser);return d?JSON.parse(d):null;}catch(e){return null;}
}
