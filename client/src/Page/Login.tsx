import { Alert, AlertDescription, AlertIcon, AlertTitle, Button, Container, Heading, Input, InputGroup, InputRightElement, Spacer, Stack } from "@chakra-ui/react"
import PasswordInput from "../components/PasswordInput";
import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { CurrentUserContext, userGetterSetter } from "../App";

const Login = () => {
    const [failed, setFailed] = useState(false);
    const [username, setUsername] = useState('');

    const { setUser, setIsAuthenticated } = useContext(CurrentUserContext) as userGetterSetter;

    const navigate = useNavigate();

    const [show, setShow] = useState(false);

    const handleShowPassword = () => {
        setShow(!show);
    }

    const handleLogin = () => {
        // TODO: make login request
        const success = true;
        if (success) {
            setUser(username);
            setIsAuthenticated(true);
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