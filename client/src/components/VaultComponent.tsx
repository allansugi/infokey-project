import { Card, CardBody } from "@chakra-ui/card"
import { Box, Button, Container, Flex, IconButton, Input, InputGroup, InputRightElement, Menu, MenuButton, MenuItem, MenuList, Spacer, Stack, Text, useToast } from "@chakra-ui/react"
import {HamburgerIcon, SearchIcon} from "@chakra-ui/icons"
import { Outlet, redirect, useLoaderData, useNavigate } from "react-router-dom"
import { AccountDetail, AccountDetails } from "../loaders/accountLoaders"
import { useState } from "react"
import { Link as RouterLink } from 'react-router-dom'
import VaultService from "../service/VaultService"
import { HTTPStatus } from "../helpers/status"

const VaultComponent = () => {
    const { items } = useLoaderData() as AccountDetails;
    const toast = useToast();
    const navigate = useNavigate();
    const [user, setUser] = useState(() => {
        return sessionStorage.getItem("user") || null;
    })

    const [id, setId] = useState(() => {
        return sessionStorage.getItem("id") || null;
    })

    const [accountItems, setItems] = useState(items);

    const handleNewAccount = () => {
        navigate(`/user/${id}/vault/add-account`);
    }

    const handleDelete = async(accountId: string) => {
        const token = sessionStorage.getItem("token");
        if (token === null) {
            return; 
        }
        const response = await VaultService.deleteAccountItem(token, accountId);
        if (response.status == HTTPStatus.NO_CONTENT) {
            setItems(items.filter((item: AccountDetail) => item.id !== accountId));
            toast({
                title: 'Account deleted.',
                description: "Account has been deleted.",
                status: 'success',
                duration: 9000,
                isClosable: true,
            })
            redirect(`/user/${user}/vault`)
        }
    }
    
    return (
        <>
           <Container> 
                <Stack spacing="12px">
                    <Stack direction="row">
                        <InputGroup>
                            <Input placeholder='Search Accounts' />
                            <InputRightElement>
                                <SearchIcon />
                            </InputRightElement>
                        </InputGroup>
                        <Button onClick={handleNewAccount}>New Account</Button>
                    </Stack>
                    
                    {
                        accountItems.map((account: AccountDetail) => {
                            return(
                                <Card>
                                    <CardBody>
                                        <Flex direction="row" justifyItems="center">
                                            <Box>
                                                <Text as="b">{account.name}</Text>
                                                <Text>{account.username}</Text>
                                            </Box>
                                            <Spacer />
                                            <Outlet />
                                            <Menu>
                                                <MenuButton colorScheme="teal" variant="ghost" as={IconButton} icon={<HamburgerIcon />} />
                                                <MenuList>
                                                    <MenuItem>Copy Username</MenuItem>
                                                    <MenuItem>Copy Password</MenuItem>
                                                    <RouterLink to={`${account.id}`}>
                                                        <MenuItem onClick={() => navigate(`/user/${id}/vault/${account.id}`)}>Edit Account</MenuItem>
                                                    </RouterLink>
                                                    <MenuItem onClick={() => handleDelete(account.id)}>Delete</MenuItem>
                                                </MenuList>
                                            </Menu>
                                        </Flex>
                                    </CardBody>
                                </Card>
                            )
                        })
                    }
                </Stack>
            </Container>
        </>
    )
}

export default VaultComponent;