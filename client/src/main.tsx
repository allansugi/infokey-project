import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { ChakraProvider } from '@chakra-ui/react'
import { Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from 'react-router-dom'
import Home from './Page/Home.tsx'
import About from './Page/About.tsx'
import Docs from './Page/Docs.tsx'
import Vault from './Page/Vault.tsx'
import { accountItemLoader, accountLoader } from './loaders/accountLoaders.tsx'
import Login from './Page/Login.tsx'
import Register from './Page/Register.tsx'
import NewAccountComponent from './Page/NewAccountComponent.tsx'
import EditAccountItem from './Page/EditAccountItem.tsx'

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/'element={<App />}>
      <Route index element={<Home />} />
      <Route path='home' element={<Home />}/>
      <Route path='docs' element={<Docs />}/>
      <Route path='about' element={<About />}/>
      <Route path='login' element={<Login />}/>
      <Route path='register' element={<Register />}/>
      <Route path='user/:userId/vault/' element={<Vault />} loader={accountLoader} />
      <Route path='user/:userId/vault/add-account' element={<NewAccountComponent/>} />
      <Route path='user/:userId/vault/:accountId' element={<EditAccountItem />} loader={accountItemLoader}/>
    </Route>
  )
)

// TODO: put auth0 details in .env file 
ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
      <ChakraProvider>
        <RouterProvider router={router}/>
      </ChakraProvider>
  </React.StrictMode>,
)
