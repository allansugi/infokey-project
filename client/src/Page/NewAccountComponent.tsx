import { Button, Input, InputGroup, InputRightElement, Stack, HStack, Container, Heading, useToast } from "@chakra-ui/react"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import VaultService from "../service/VaultService";
import { HTTPStatus } from "../helpers/httpstatus";

const NewAccountComponent = () => {
    const navigate = useNavigate();
    const [show, setShow] = useState(false);

    const toast = useToast();

    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleClose = () => {
        navigate(-1);
    }

    const handleSubmit = async() => {
        const token = sessionStorage.getItem("token");

        if (token == null) {
            return;
        }

        const response = await VaultService.createNewAccountItem(token, name, username, password);
        
        if (response.status === HTTPStatus.CREATED) {
            toast({
                title: 'Account Created',
                description: "New account has been created.",
                status: "success",
                duration: 9000,
                isClosable: true,
            });
            navigate(-1);
        } else {
            toast({
                title: 'Create Account Error',
                description: "There is a problem creating new account, try again later",
                status: "error",
                duration: 9000,
                isClosable: true,
            });
        }
    }

    const handleShowPassword = () => {
        setShow(!show);
    }

    return (
        <>
            <Container width="100%" height="80%" display="flex" flexDirection="column" justifyContent="center">
                <Stack spacing="12px">
                    <Heading as='h2' textAlign="center">Add new account</Heading>
                    <Input placeholder="Account name" onChange={(e) => setName(e.target.value)}/>
                    <Input placeholder="Username" onChange={(e) => setUsername(e.target.value)}/>
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
                    <HStack>
                        <Button colorScheme='blue' mr={3} onClick={handleClose}>
                        Close
                        </Button>
                        <Button variant='ghost' onClick={handleSubmit}>Submit</Button>
                    </HStack>
                </Stack>
            </Container>
        </>
    )
}

export default NewAccountComponent;