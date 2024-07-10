import { Container, Stack, Input, InputGroup, InputRightElement, Button, Heading, HStack, useToast } from "@chakra-ui/react";
import { useState } from "react";
import { useNavigate, useLoaderData } from "react-router-dom";
import { AccountItemDetail } from "../loaders/accountLoaders";
import VaultService from "../service/VaultService";
import { HTTPStatus } from "../helpers/httpstatus";

const EditAccountItem = () => {
    const navigate = useNavigate();
    const [show, setShow] = useState(false);

    const item = useLoaderData() as AccountItemDetail;
    const toast = useToast();

    const [name, setName] = useState(item.name);
    const [username, setUsername] = useState(item.username);
    const [password, setPassword] = useState(item.password);

    const handleClose = () => {
        navigate(-1);
    }

    const handleShowPassword = () => {
        setShow(!show);
    }

    const handleSubmit = async() => {
        if (name !== '' && username !== '' && password !== '') {
            const token = sessionStorage.getItem("token");
            if (token === null) {
                return;
            }
           
            const response = await VaultService.updateAccountItem(token, item.id, name, username, password);
            
            if (response.status === HTTPStatus.NO_CONTENT) {
                toast({
                    title: 'Account updated',
                    description: "Account has been updated.",
                    status: "success",
                    duration: 3000,
                    isClosable: true,
                })
                navigate(-1);
            } else {
                toast({
                    title: 'Update Error',
                    description: "There is a problem updating your account, try again later",
                    status: "error",
                    duration: 3000,
                    isClosable: true,
                })
            }
            
        }
    }

    return (
        <>
            <Container width="100%" height="80%" display="flex" flexDirection="column" justifyContent="center">
                <Stack spacing="12px">
                    <Heading as='h2'>Edit Account</Heading>
                    <Input 
                        placeholder="Enter Name" 
                        defaultValue={name}
                        onChange={(e) => setName(e.target.value)}/>
                    <Input 
                        placeholder="Enter Username" 
                        defaultValue={username}
                        onChange={(e) => setUsername(e.target.value)}/>
                    <InputGroup size='md'>
                        <Input
                            pr='4.5rem'
                            type={show ? 'text' : 'password'}
                            placeholder='Enter password'
                            defaultValue={password}
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

export default EditAccountItem;