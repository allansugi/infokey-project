import { Button, Input, InputGroup, InputRightElement, Stack, HStack, Container, Heading } from "@chakra-ui/react"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import VaultService from "../service/VaultService";

const NewAccountComponent = () => {
    const navigate = useNavigate();
    const [show, setShow] = useState(false);

    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleClose = () => {
        navigate(-1);
    }

    const handleSubmit = async() => {
        const token = sessionStorage.getItem("token");
        if (token !== null) {
            await VaultService.createNewAccountItem(token, name, username, password);
            navigate(-1);
        }
    }

    const handleShowPassword = () => {
        setShow(!show);
    }

    return (
        <>
            <Container>
                <Stack>
                    <Heading as='h2'>Add new account</Heading>
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