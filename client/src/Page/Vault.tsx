import { Tab, TabList, TabPanel, TabPanels, Tabs, Text } from "@chakra-ui/react"
import VaultComponent from "../components/VaultComponent";
import PasswordGeneratorComponent from "../components/PasswordGeneratorComponent";
import { useAuth0 } from "@auth0/auth0-react";

const Vault = () => {
    const { user, isAuthenticated } = useAuth0();

    return (
        <>
            {isAuthenticated && <Text>Hello {user?.preferred_username || user?.name}</Text>}
            <Tabs variant='soft-rounded' colorScheme='green'>
                <TabList>
                    <Tab>Accounts</Tab>
                    <Tab>Password Generator</Tab>
                </TabList>
                <TabPanels>
                    <TabPanel>
                        <VaultComponent />
                    </TabPanel>
                    <TabPanel>
                        <PasswordGeneratorComponent />
                    </TabPanel>
                </TabPanels>
            </Tabs>
        </>
    )
}

export default Vault;