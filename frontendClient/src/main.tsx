import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './app/Layout/App.tsx'
import './app/Layout/index.css'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
