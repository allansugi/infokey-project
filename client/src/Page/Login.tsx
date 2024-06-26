import { Alert, AlertDescription, AlertIcon, AlertTitle, Button, Container, Heading, Input, InputGroup, InputRightElement, Spacer, Stack } from "@chakra-ui/react"
import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { CurrentUserContext, userGetterSetter } from "../App";
import { HTTPStatus } from "../helpers/status";
import Register from "./Register";
import AuthService from "../service/AuthService";

const Login = () => {
    const [failed, setFailed] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const { setUser, setIsAuthenticated } = useContext(CurrentUserContext) as userGetterSetter;

    const navigate = useNavigate();

    const [show, setShow] = useState(false);

    const handleShowPassword = () => {
        setShow(!show);
    }

    const handleLogin = async() => {
        // TODO: make login request
        const response = await AuthService.login(username, password);

        const token: any = response.body;

        if (response.status == HTTPStatus.OK) {
            setUser(username);
            setIsAuthenticated(true);
            sessionStorage.setItem("token", token);
            navigate(`/user/${username}/vault`);
        } else {
            setFailed(true);
        }
    }

    const handleUsernameEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }
    
    return (
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
    )
}

export default Login;