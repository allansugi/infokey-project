import './App.css'
import Navbar from './components/Navbar'
import '@fontsource/roboto';
import { Outlet } from 'react-router-dom';

function App() {
  return (
    <>
      <Navbar />
      <Outlet />
    </>
  )
}

export default App
