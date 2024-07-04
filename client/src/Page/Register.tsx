import { Button, Container, Heading, Input, InputGroup, InputRightElement, Spacer, Stack, useToast } from "@chakra-ui/react"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { HTTPStatus } from "../helpers/status";
import AuthService from "../service/AuthService";
import NavBar from "../components/Navbar";

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
                const response = await AuthService.register(username, email, password);
    
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

        <>
            <NavBar />
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
        </>
       
    )
}

export default Register;