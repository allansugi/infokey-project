import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { Auth0Provider } from '@auth0/auth0-react'
import { ChakraProvider } from '@chakra-ui/react'
import { Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from 'react-router-dom'
import Home from './Page/Home.tsx'
import About from './Page/About.tsx'
import Docs from './Page/Docs.tsx'
import Vault from './Page/Vault.tsx'
import EditAccountModal from './components/EditAccountModal.tsx'
import { accountLoader } from './loaders/accountLoaders.tsx'
import NewAccountModal from './components/NewAccountModal.tsx'

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/'element={<App />}>
      <Route path='home' element={<Home />}/>
      <Route path='docs' element={<Docs />}/>
      <Route path='about' element={<About />}/>
      <Route path='/user/:userId/vault' element={<Vault />} loader={accountLoader}>
        <Route path=':accountId' element={<EditAccountModal />}/>
        <Route path='create' element={<NewAccountModal />}/>
      </Route>
    </Route>
  )
)

// TODO: put auth0 details in .env file 
ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Auth0Provider
      domain="dev-gx0otiklhixnfqhl.us.auth0.com"
      clientId="9NrHwqKeAAQbKXD86MfayQClqhO6Ov6f"
      authorizationParams={{
        redirect_uri: window.location.origin
      }}
    >
      <ChakraProvider>
        <RouterProvider router={router}/>
      </ChakraProvider>
    </Auth0Provider>
  </React.StrictMode>,
)
