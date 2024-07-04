import { Button } from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";

const LoginButton = () => {
  // const { loginWithRedirect } = useAuth0();
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/login");
  }
  return (
    // <Button colorScheme='teal' variant='ghost' onClick={() => loginWithRedirect()}>Log In</Button>
    <Button colorScheme='teal' variant='ghost' onClick={handleClick}>Login</Button>
  )
};

export default LoginButton;