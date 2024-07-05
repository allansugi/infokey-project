import { Button } from "@chakra-ui/button";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { CurrentUserContext, userGetterSetter } from "../App";

const LogoutButton = () => {
  const navigate = useNavigate();
  const { setUser, setIsAuthenticated } = useContext(CurrentUserContext) as userGetterSetter;

  const handleClick = () => {
    setUser('');
    setIsAuthenticated(false);
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("id");
    navigate("/home");
  }

  return (
    <Button color='teal' variant='ghost' onClick={handleClick}>
      Log Out
    </Button>
  );
};

export default LogoutButton;