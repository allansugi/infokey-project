import { HamburgerIcon } from "@chakra-ui/icons"
import { Card, CardBody, Flex, Box, Spacer, Menu, MenuButton, IconButton, MenuList, MenuItem, Text, useToast } from "@chakra-ui/react"
import { Outlet, Link as RouterLink, useNavigate } from "react-router-dom"
import { AccountDetail } from "../loaders/accountLoaders"
import { HTTPStatus } from "../helpers/httpstatus"
import VaultService from "../service/VaultService"

interface AccountItemProps {
    account: AccountDetail;
    userId: string | null;
    handleDelete: (accountId: string) => void;
}

const AccountItemCard = (props: AccountItemProps) => {

    const { account, userId, handleDelete } = props;
    const toast = useToast();
    const navigate = useNavigate();

    const handleDeleteAccount = async(accountId: string) => {
        handleDelete(accountId);
    }

    const handleCopyUsername = async(accountId: string) => {
        const token = sessionStorage.getItem("token")
        if (token === null) {
            return; 
        }
        const response = await VaultService.readAccountItem(token, accountId);
        if (response.status === HTTPStatus.OK) {
            const body = await response.json();
            navigator.clipboard.writeText(body.username).then(() => {
                toast({
                    title: 'Username Copied',
                    description: "Username has been copied",
                    status: 'success',
                    duration: 3000,
                    isClosable: true,
                });
                
            }, () => {
                toast({
                    title: 'Username Copy Failed',
                    description: "Failed to copy username",
                    status: 'error',
                    duration: 3000,
                    isClosable: true,
                })
            })
        }

    }

    const handleCopyPassword = async(accountId: string) => {
        const token = sessionStorage.getItem("token");
        if (token === null) {
            return; 
        }
        const response = await VaultService.readAccountItem(token, accountId);
        if (response.status === HTTPStatus.OK) {
            const body = await response.json();
            navigator.clipboard.writeText(body.password).then(() => {
                toast({
                    title: 'Password Copied',
                    description: "Password has been copied",
                    status: 'success',
                    duration: 3000,
                    isClosable: true,
                });
            }, () => {
                toast({
                    title: 'Password Copy Failed',
                    description: "Failed to copy password",
                    status: 'error',
                    duration: 3000,
                    isClosable: true,
                })
            })
        }
    }

    return (
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
                            <MenuItem onClick={() => {handleCopyUsername(account.id)}}>Copy Username</MenuItem>
                            <MenuItem onClick={() => {handleCopyPassword(account.id)}}>Copy Password</MenuItem>
                            <RouterLink to={`${account.id}`}>
                                <MenuItem onClick={() => navigate(`/user/${userId}/vault/${account.id}`)}>Edit Account</MenuItem>
                            </RouterLink>
                            <MenuItem onClick={() => handleDeleteAccount(account.id)}>Delete</MenuItem>
                        </MenuList>
                    </Menu>
                </Flex>
            </CardBody>
        </Card>
    )
}

export default AccountItemCard;