import { Auth0ContextInterface, useAuth0 } from "@auth0/auth0-react";

const Profile = () => {
  const { user, isAuthenticated, isLoading }: Auth0ContextInterface = useAuth0();

  if (isLoading) {
    return <div>Loading ...</div>;
  }

  return (
    user !== undefined && isAuthenticated && (
      <div>
        <img src={user.picture} alt={user.name} />
        <h2>{user.name}</h2>
        <p>{user.email}</p>
      </div>
    )
  );
};

export default Profile;