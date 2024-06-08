import { useAuth0 } from "@auth0/auth0-react";
import { Button } from "@chakra-ui/button";

const LogoutButton = () => {
  const { logout } = useAuth0();

  return (
    <Button color='teal' variant='ghost' onClick={() => logout({ logoutParams: { returnTo: window.location.origin } })}>
      Log Out
    </Button>
  );
};

export default LogoutButton;