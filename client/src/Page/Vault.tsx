import { Tab, TabList, TabPanel, TabPanels, Tabs, Text } from "@chakra-ui/react"
import VaultComponent from "../components/VaultComponent";
import PasswordGeneratorComponent from "../components/PasswordGeneratorComponent";
import { useContext } from "react";
import { CurrentUserContext, userGetterSetter } from "../App";

const Vault = () => {

    const { user } = useContext(CurrentUserContext) as userGetterSetter;

    return (
        <>
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