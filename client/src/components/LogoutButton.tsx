import { useAuth0 } from "@auth0/auth0-react";
import { Button } from "@chakra-ui/button";
import { useContext } from "react";
import { redirect, useNavigate } from "react-router-dom";
import { CurrentUserContext, userGetterSetter } from "../App";

const LogoutButton = () => {
  const navigate = useNavigate();
  const { setUser, setIsAuthenticated } = useContext(CurrentUserContext) as userGetterSetter;

  const handleClick = () => {
    setUser('');
    setIsAuthenticated(false);
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("user");
    navigate("/home");
  }

  return (
    <Button color='teal' variant='ghost' onClick={handleClick}>
      Log Out
    </Button>
  );
};

export default LogoutButton;