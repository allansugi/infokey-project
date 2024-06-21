import { Flex, Heading, Spacer, ButtonGroup, Box, Breadcrumb, BreadcrumbItem, BreadcrumbLink, Text } from "@chakra-ui/react"
import LoginButton from "./LoginButton";
import LogoutButton from "./LogoutButton";
import { Link } from "react-router-dom";
import RegisterButton from "./RegisterButton";
import { useContext } from "react";
import { CurrentUserContext, userGetterSetter } from "../App";

const NavBar = () => {

    const { user, isAuthenticated } = useContext(CurrentUserContext) as userGetterSetter;

    return (
        <Flex minWidth='max-content' alignItems='center' gap='2' p={3}>

            <Box p='2'>
                <Heading size='md'>InfoKey</Heading>
            </Box>

            <Breadcrumb>
                {!isAuthenticated && 
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
                    isAuthenticated &&
                    
                    <BreadcrumbItem>
                        <BreadcrumbLink as={Link} to="/user/1/vault">Vault</BreadcrumbLink>
                    </BreadcrumbItem>
                }
            </Breadcrumb>

            <Spacer />
            {isAuthenticated && <Text>Welcome {user}</Text>}
            <ButtonGroup gap='2'>
                {isAuthenticated ? <LogoutButton /> : <LoginButton />}
                {!isAuthenticated && <RegisterButton />}
            </ButtonGroup>
        </Flex>
    )
}

export default NavBar;