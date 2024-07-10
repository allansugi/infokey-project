import { Container, Heading, ListItem, UnorderedList, Text, Stack } from "@chakra-ui/react"
import Navbar from "../components/Navbar";

const Docs = () => {
    return (
        <>
            <Navbar />
            <Container>
                <Stack spacing="12px">
                    <Stack>
                        <Heading as='h2' size="lg">Account Register Requirement</Heading>
                        <Text>when register for new account, user need to meet these requirements:</Text>
                        <UnorderedList>
                            <ListItem>Unique username and valid unique email</ListItem>
                            <ListItem>Length of a password must be at least 8 character</ListItem>
                            <ListItem>Password must contain at least 1 lowercase letter</ListItem>
                            <ListItem>Password must contain at least 1 uppercase letter</ListItem>
                            <ListItem>Password must contain at least 1 numeric</ListItem>
                            <ListItem>Password must contain at least 1 special character</ListItem>
                        </UnorderedList>
                    </Stack>
                    <Stack>
                        <Heading as='h2' size="lg">Account Item Feature</Heading>
                        <Text>
                            User can create, edit, and delete account item. In addition, user can Copy
                            username and password at a touch of a button
                        </Text>
                    </Stack>
                    <Stack>
                        <Heading as='h2' size="lg">Password Generator</Heading>
                        <Text>
                            In Password Generator section, user can generate random password.
                            user can edit the length and characters to be included to generate
                            those passwords
                        </Text>
                    </Stack>
                    <Stack>
                        <Heading as='h2' size="lg">Edit or Delete Account</Heading>
                        <Text>
                            User currently can't change or delete account. The feature will be
                            coming soon
                        </Text>
                    </Stack>
                </Stack>
               
            </Container>
        </>
    )
}

export default Docs;