import { Flex, Heading, Spacer, ButtonGroup, Box, Breadcrumb, BreadcrumbItem, BreadcrumbLink } from "@chakra-ui/react"
import LoginButton from "./LoginButton";
import { useAuth0 } from "@auth0/auth0-react";
import LogoutButton from "./LogoutButton";
import { Link } from "react-router-dom";

const NavBar = () => {
    const { user, isAuthenticated } = useAuth0();
    console.log(user)
    return (
        <Flex minWidth='max-content' alignItems='center' gap='2' p={3}>

            <Box p='2'>
                <Heading size='md'>InfoKey</Heading>
            </Box>

            <Breadcrumb>
                <BreadcrumbItem>
                    <BreadcrumbLink href='/home'>Home</BreadcrumbLink>
                </BreadcrumbItem>

                <BreadcrumbItem>
                    <BreadcrumbLink href='/docs'>Docs</BreadcrumbLink>
                </BreadcrumbItem>

                <BreadcrumbItem>
                    <BreadcrumbLink href='/about'>About</BreadcrumbLink>
                </BreadcrumbItem>

                {/* TODO: replace link with user id */}
                {
                    isAuthenticated &&
                    <BreadcrumbItem>
                        <BreadcrumbLink as={Link} to="/user/1/vault">Vault</BreadcrumbLink>
                    </BreadcrumbItem>
                }

                <BreadcrumbItem>
                    <BreadcrumbLink as={Link} to="/user/1/vault">VaultTest</BreadcrumbLink>
                </BreadcrumbItem>
                
            </Breadcrumb>

            <Spacer />
            <ButtonGroup gap='2'>
                {isAuthenticated ? <LogoutButton /> : <LoginButton />}
            </ButtonGroup>
        </Flex>
    )
}

export default NavBar;