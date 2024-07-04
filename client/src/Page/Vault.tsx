import { Tab, TabList, TabPanel, TabPanels, Tabs } from "@chakra-ui/react"
import VaultComponent from "../components/VaultComponent";
import PasswordGeneratorComponent from "../components/PasswordGeneratorComponent";
import NavBar from "../components/Navbar";

const Vault = () => {

    return (
        <>  
            <NavBar />
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