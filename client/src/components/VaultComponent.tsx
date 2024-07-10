import { Button, Container, Input, InputGroup, InputRightElement, Stack, useToast } from "@chakra-ui/react"
import {SearchIcon} from "@chakra-ui/icons"
import { useLoaderData, useNavigate } from "react-router-dom"
import { AccountDetail, AccountDetails } from "../loaders/accountLoaders"
import { useState } from "react"
import VaultService from "../service/VaultService"
import { HTTPStatus } from "../helpers/httpstatus"
import AccountItemCard from "./AccountItemCard"

const VaultComponent = () => {
    const { items } = useLoaderData() as AccountDetails;
    const toast = useToast();
    const navigate = useNavigate();

    const [id, setId] = useState(() => {
        return sessionStorage.getItem("id") || null;
    })

    const [accountItems, setItems] = useState(items);
    const [filteredAccountItems, setFilteredAccountItems] = useState(items);

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
            setFilteredAccountItems(items.filter((item: AccountDetail) => item.id !== accountId));
            toast({
                title: 'Account deleted.',
                description: "Account has been deleted.",
                status: 'success',
                duration: 3000,
                isClosable: true,
            })
        }
    }

    const handleSearch = (search: string) => {
        if (search === '') {
            setFilteredAccountItems([...accountItems]);
        } else {
            const filerAccount = accountItems.filter((item: AccountDetail) => { 
                return item.name.startsWith(search) || item.username.startsWith(search);
            })
            setFilteredAccountItems(filerAccount);
        }
    }
    
    return (
        <>
           <Container> 
                <Stack spacing="12px">
                    <Stack direction="row">
                        <InputGroup>
                            <Input 
                                placeholder='Search Accounts' 
                                onChange={(e) => handleSearch(e.target.value)}
                            />
                            <InputRightElement>
                                <SearchIcon />
                            </InputRightElement>
                        </InputGroup>
                        <Button onClick={handleNewAccount}>New Account</Button>
                    </Stack>
                    
                    {
                        filteredAccountItems.map((account: AccountDetail) => {
                            return(
                                <AccountItemCard account={account} userId={id} handleDelete={handleDelete} />
                            )
                        })
                    }
                </Stack>
            </Container>
        </>
    )
}

export default VaultComponent;