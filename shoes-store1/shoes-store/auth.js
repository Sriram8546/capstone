(function(){
  const STORAGE=window.localStorage;
  const KEYS={users:'shoes_users',currentUser:'shoes_current_user'};

  function getUsers(){
    try{
      const d=STORAGE.getItem(KEYS.users);
      return d?JSON.parse(d):{};
    }catch(e){return{}}
  }

  function saveUsers(users){STORAGE.setItem(KEYS.users,JSON.stringify(users));}

  function getCurrentUser(){
    try{
      const d=STORAGE.getItem(KEYS.currentUser);
      return d?JSON.parse(d):null;
    }catch(e){return null}
  }

  function setCurrentUser(user){STORAGE.setItem(KEYS.currentUser,JSON.stringify(user));}
  function clearCurrentUser(){STORAGE.removeItem(KEYS.currentUser);}

  function initProtectedPage(){
    const p=window.location.pathname||'';
    const isLoginPage=p.endsWith('index.html')||p.endsWith('/')||p.endsWith('shoes-store')||/\/(shoes-store)?\/?$/.test(p);
    if(isLoginPage){
      if(getCurrentUser()){window.location.href='home.html';}
      return;
    }
    if(!getCurrentUser()){window.location.href='index.html';}
  }

  function updateCartBadge(){
    const badge=document.getElementById('cart-count');
    if(!badge)return;
    const cart=getCart();
    const total=Object.values(cart).reduce((s,it)=>s+it.quantity,0);
    badge.textContent=total;
  }

  function getCart(){
    const user=getCurrentUser();
    if(!user)return{};
    try{
      const key='shoes_cart_'+user.email;
      const d=STORAGE.getItem(key);
      return d?JSON.parse(d):{};
    }catch(e){return{}}
  }

  document.addEventListener('DOMContentLoaded',function(){
    if(document.getElementById('auth-form')){
      const form=document.getElementById('auth-form');
      const toggle=document.getElementById('auth-toggle');
      const nameField=document.getElementById('name-field');
      const submitBtn=document.getElementById('auth-submit');
      const errorEl=document.getElementById('auth-error');
      let mode='login';

      toggle?.addEventListener('click',function(e){
        const btn=e.target.closest('.toggle-btn');
        if(!btn)return;
        mode=btn.dataset.mode;
        toggle.querySelectorAll('.toggle-btn').forEach(b=>b.classList.remove('active'));
        btn.classList.add('active');
        nameField?.classList.toggle('hidden',mode!=='signup');
        submitBtn.textContent=mode==='signup'?'Sign Up':'Login';
        errorEl?.classList.add('hidden');
      });

      form?.addEventListener('submit',function(e){
        e.preventDefault();
        const email=document.getElementById('email').value.trim();
        const password=document.getElementById('password').value;
        const name=document.getElementById('name')?.value?.trim();
        errorEl?.classList.add('hidden');

        if(!email||!password){showError('Please fill in all fields');return;}
        if(password.length<6){showError('Password must be at least 6 characters');return;}

        const users=getUsers();
        if(mode==='signup'){
          if(users[email]){showError('Email already registered');return;}
          if(!name){showError('Please enter your name');return;}
          users[email]={email,password,name};
          saveUsers(users);
          setCurrentUser(users[email]);
          window.location.href='home.html';
        }else{
          const u=users[email];
          if(!u||u.password!==password){showError('Invalid email or password');return;}
          setCurrentUser(u);
          window.location.href='home.html';
        }
      });

      function showError(msg){errorEl.textContent=msg;errorEl.classList.remove('hidden');}
    }

    const logoutBtn=document.getElementById('logout-btn');
    if(logoutBtn){
      logoutBtn.addEventListener('click',function(e){e.preventDefault();clearCurrentUser();window.location.href='index.html';});
    }

    initProtectedPage();
    updateCartBadge();
  });
})();
