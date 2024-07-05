import { Button } from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";

const LoginButton = () => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/login");
  }
  
  return (
    <Button colorScheme='teal' variant='ghost' onClick={handleClick}>Login</Button>
  )
};

export default LoginButton;