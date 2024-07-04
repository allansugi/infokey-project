import { Dispatch, SetStateAction, createContext, useState } from 'react';
import './App.css'
import '@fontsource/roboto';
import { Outlet } from 'react-router-dom';

export const CurrentUserContext = createContext<userGetterSetter | null>(null);

export interface userGetterSetter {
  user: string;
  setUser: Dispatch<SetStateAction<string>>;
  isAuthenticated: boolean;
  setIsAuthenticated: Dispatch<SetStateAction<boolean>>;
}

function App() {

  const [user, setUser] = useState<string>('');
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  return (
    <CurrentUserContext.Provider value={{
      user,
      setUser,
      isAuthenticated,
      setIsAuthenticated
    }}>
      <Outlet />
    </CurrentUserContext.Provider>
  )
}

export default App;
