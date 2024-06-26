import { Button, Container, Heading, Input, InputGroup, InputRightElement, Spacer, Stack, useToast } from "@chakra-ui/react"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { HTTPStatus } from "../helpers/status";

const Register = () => {

    const navigate = useNavigate();
    const toast = useToast();

    const [show, setShow] = useState(false);
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleShowPassword = () => {
        setShow(!show);
    }

    const handleSubmit = async () => {
        if (username !== '' && email !== '' && password !== '') {
            try {
                const response = await fetch('http://localhost:8080/api/v1/auth/register', {
                    method: 'POST',
                    mode: 'cors',
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        username: username,
                        email: email,
                        password: password
                    })
                })
    
                if (response.status == HTTPStatus.CREATED) {
                    toast({
                        title: "Registration Successful",
                        description: "Your account has been registered successfully.",
                        status: "success",
                        duration: 3000,
                        isClosable: true,
                    });
                    navigate('/home');
                }
                
            } catch (error) {
                console.log(error);
            }
        }
    }
    
    return (
        <Container>
            <Stack>
                <Heading as='h2'>Register for new account</Heading>
                <Spacer />
                <Stack spacing="12px">
                    <Input placeholder='Enter Username' size='md' onChange={(e) => setUsername(e.target.value)}/>
                    <Input placeholder='Enter Email' size='md' onChange={(e) => setEmail(e.target.value)}/>
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
            <Button onClick={handleSubmit}>register</Button>
            </Stack>
            
        </Container>
    )
}

export default Register;