import { Card, CardBody } from "@chakra-ui/card"
import { Box, Flex, IconButton, Input, InputGroup, InputLeftElement, InputRightElement, Menu, MenuButton, MenuItem, MenuList, Spacer, Stack, Text } from "@chakra-ui/react"
import {CheckIcon, HamburgerIcon, SearchIcon} from "@chakra-ui/icons"
import { Link, Outlet, useLoaderData } from "react-router-dom"
import { AccountDetail, AccountDetails } from "../loaders/accountLoaders"

const VaultComponent = () => {
    const { accounts } = useLoaderData() as AccountDetails;
    return (
        <>
            
            <Stack spacing="12px">
                <InputGroup>
                    <Input placeholder='Search Accounts' />
                    <InputRightElement>
                        <SearchIcon />
                    </InputRightElement>
                </InputGroup>       
                {
                    accounts.map((account: AccountDetail) => {
                        return(
                            <Card>
                                <CardBody>
                                    <Flex direction="row" justifyItems="center">
                                        <Box>
                                            <Text as="b">{account.account_name}</Text>
                                            <Text>{account.username_email}</Text>
                                        </Box>
                                        <Spacer />
                                        <Outlet />
                                        <Menu>
                                            <MenuButton colorScheme="teal" variant="ghost" as={IconButton} icon={<HamburgerIcon />} />
                                            <MenuList>
                                                <MenuItem>Copy Username</MenuItem>
                                                <MenuItem>Copy Password</MenuItem>
                                                <Link to="1">
                                                    <MenuItem>Edit Account</MenuItem>
                                                </Link>
                                                <MenuItem>Delete</MenuItem>
                                            </MenuList>
                                        </Menu>
                                    </Flex>
                                </CardBody>
                            </Card>
                        )
                    })
                }
            </Stack>
        </>
    )
}

export default VaultComponent;