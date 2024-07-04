import { Alert, AlertDescription, AlertIcon, AlertTitle, Button, Container, Heading, Input, InputGroup, InputRightElement, Spacer, Stack } from "@chakra-ui/react"
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { HTTPStatus } from "../helpers/status";
import AuthService from "../service/AuthService";
import NavBar from "../components/Navbar";

const Login = () => {
    const [failed, setFailed] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const [show, setShow] = useState(false);

    const handleShowPassword = () => {
        setShow(!show);
    }

    const handleLogin = async() => {
        // TODO: make login request
        const response = await AuthService.login(username, password);
        
        if (response.status == HTTPStatus.OK) {
            const loginResponse = await response.json();
            setUsername(loginResponse.username);
            // setUser(loginResponse.userId);
            // setIsAuthenticated(true);
            sessionStorage.setItem("token", loginResponse.token);
            sessionStorage.setItem("user", loginResponse.username);
            sessionStorage.setItem("id", loginResponse.id);
            navigate(`/user/${loginResponse.userId}/vault`);
        } else {
            setFailed(true);
        }
    }

    const handleUsernameEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }
    
    return (
        <>
            <NavBar />
            <Container>
                <Stack>
                    <Heading as='h2'>User Login</Heading>
                    <Spacer />
                    {failed && 
                        <Alert status='error'>
                            <AlertIcon />
                            <AlertTitle>Login Failed</AlertTitle>
                            <AlertDescription>Incorrect user credentials</AlertDescription>
                        </Alert>
                    }
                    <Stack spacing="12px">
                        <Input placeholder='Enter email' size='md' onChange={handleUsernameEmail}/>
                        <InputGroup size='md'>
                            <Input
                                pr='4.5rem'
                                type={show ? 'text' : 'password'}
                                placeholder='Enter password'
                                onChange={(e) => setPassword(e.target.value)}
                            />
                            <InputRightElement width='4.5rem'>
                                <Button h='1.75rem' size='sm' onClick={handleShowPassword}>
                                {show ? 'Hide' : 'Show'}
                                </Button>
                            </InputRightElement>
                        </InputGroup>
                    </Stack>
                    <Button onClick={handleLogin}>Login</Button>
                </Stack>
            </Container>
        </>
        
    )
}

export default Login;