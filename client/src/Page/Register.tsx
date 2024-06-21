import { Button, Container, Heading, Input, Spacer, Stack } from "@chakra-ui/react"
import PasswordInput from "../components/PasswordInput";

const Register = () => {
    return (
        <Container>
            <Stack>
                <Heading as='h2'>Register for new account</Heading>
                <Spacer />
                <Stack spacing="12px">
                    <Input placeholder='Enter Username' size='md' />
                    <Input placeholder='Enter Email' size='md' />
                    <PasswordInput />
                </Stack>
            <Button>register</Button>
            </Stack>
            
        </Container>
    )
}

export default Register;