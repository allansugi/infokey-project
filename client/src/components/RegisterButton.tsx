import { Button } from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";

const RegisterButton = () => {
  // const { loginWithRedirect } = useAuth0();
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/register");
  }
  return (
    // <Button colorScheme='teal' variant='ghost' onClick={() => loginWithRedirect()}>Log In</Button>
    <Button colorScheme='teal' variant='ghost' onClick={handleClick}>Register</Button>
  )
};

export default RegisterButton;