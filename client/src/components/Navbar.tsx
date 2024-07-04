import { Flex, Heading, Spacer, ButtonGroup, Box, Breadcrumb, BreadcrumbItem, BreadcrumbLink, Text } from "@chakra-ui/react"
import LoginButton from "./LoginButton";
import LogoutButton from "./LogoutButton";
import { Link } from "react-router-dom";
import RegisterButton from "./RegisterButton";
import { useState } from "react";

const NavBar = () => {

    // const { user, isAuthenticated } = useContext(CurrentUserContext) as userGetterSetter;
    const [currentUser, setCurrentUser] = useState(() => {
        return sessionStorage.getItem("user") || null;
    });

    const [authenticated, setIsAuthenticated] = useState(() => {
        return sessionStorage.getItem("user") !== null;
    })

    return (
        <Flex minWidth='max-content' alignItems='center' gap='2' p={3}>

            <Box p='2'>
                <Heading size='md'>InfoKey</Heading>
            </Box>

            <Breadcrumb>
                {!authenticated && 
                    <BreadcrumbItem>
                        <BreadcrumbLink as={Link} to={'/home'}>Home</BreadcrumbLink>
                    </BreadcrumbItem>
                }

                <BreadcrumbItem>
                    <BreadcrumbLink as={Link} to={'/docs'}>Docs</BreadcrumbLink>
                </BreadcrumbItem>

                <BreadcrumbItem>
                    <BreadcrumbLink as={Link} to={'/about'}>About</BreadcrumbLink>
                </BreadcrumbItem>

                {
                    authenticated &&
                    
                    <BreadcrumbItem>
                        <BreadcrumbLink as={Link} to={`/user/${currentUser}/vault`}>Vault</BreadcrumbLink>
                    </BreadcrumbItem>
                }
            </Breadcrumb>

            <Spacer />
            {authenticated && <Text>Welcome {currentUser}</Text>}
            <ButtonGroup gap='2'>
                {authenticated ? <LogoutButton /> : <LoginButton />}
                {!authenticated && <RegisterButton />}
            </ButtonGroup>
        </Flex>
    )
}

export default NavBar;