# KICKS - Shoes Shopping Website

A modern, fully functional e-commerce shoes store built with vanilla HTML, CSS, and JavaScript. Uses Local Storage for data persistence.

## Features

- **Login/Signup** - User authentication with Local Storage
- **Home** - Shoe catalog with search
- **Shoe Details** - Full product view with Add to Cart
- **Cart** - Quantity controls, remove items, auto-total
- **Payment** - Card/UPI form with validation
- **Order Success** - Confirmation with order ID
- **Order History** - Persisted purchase history

## How to Run

1. Open `index.html` in a browser, or
2. Use a local server (e.g. `npx serve .` or `python -m http.server 8000`)

**Note:** For full functionality (auth redirects, query params), use a local server. Opening files directly (file://) may have CORS restrictions on some browsers.

## Tech Stack

- HTML5
- CSS3 (Dark theme, responsive)
- Vanilla JavaScript (no frameworks)
- Local Storage

## Pages

| Page | File |
|------|------|
| Login/Signup | index.html |
| Home | home.html |
| Shoe Detail | shoe-detail.html?id=1 |
| Cart | cart.html |
| Payment | payment.html |
| Order Success | order-success.html |
| Orders | orders.html |
