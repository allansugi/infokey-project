import { Button, Card, CardBody, Checkbox, Container, NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper, Stack, Text, useToast } from "@chakra-ui/react"
import { useState } from "react";
import PasswordService from "../service/PasswordService";
import { HTTPStatus } from "../helpers/httpstatus";

const PasswordGeneratorComponent = () => {

    const [password, setPassword] = useState("");

    const [length, setLength] = useState(8);
    const lower = true;
    const [upper, setUpper] = useState(false);
    const [number, setNumber] = useState(false);
    const [special, setSpecial] = useState(false);

    const toast = useToast();

    const handleGeneratePassword = async() => {
        const response = await PasswordService.generatePassword(length, lower, upper, number, special);
        if (response.status === HTTPStatus.OK) {
            const responseBody = await response.json();
            setPassword(responseBody.generatedPassword)
        }
    }

    const handleCopyPassword = () => {
        navigator.clipboard.writeText(password).then(() => {
            toast({
                title: 'Password Copied',
                description: "Password has been copied",
                status: 'success',
                duration: 3000,
                isClosable: true,
            });
            
        }, () => {
            toast({
                title: 'Copy Failed',
                description: "Failed to copy Password",
                status: 'error',
                duration: 3000,
                isClosable: true,
            })
        })
    }

    return (
        <Stack direction="column" spacing="24px">
            <Container>
                <Card align='center'>
                    <CardBody>
                        <Text>{password}</Text>
                    </CardBody>
                </Card>
            </Container>
            
            <Container>
                <Text as="b">Length</Text>
                <NumberInput 
                    defaultValue={8} 
                    min={8} 
                    max={20} 
                    onChange={(value) => setLength(parseInt(value))}
                >
                    <NumberInputField />
                    <NumberInputStepper>
                        <NumberIncrementStepper />
                        <NumberDecrementStepper />
                    </NumberInputStepper>
                </NumberInput>
            </Container>

           <Container>
                <Text as="b">Options</Text>
                <Stack>
                    <Checkbox defaultChecked isDisabled>
                        abc
                    </Checkbox>
                    <Checkbox 
                        onChange={(e) => setUpper(e.target.checked)}
                    >
                        ABC
                    </Checkbox>
                    <Checkbox 
                        onChange={(e) => setNumber(e.target.checked)}
                    >
                        0123
                    </Checkbox>
                    <Checkbox 
                        onChange={(e) => setSpecial(e.target.checked)}
                    >
                        !@#$%^
                    </Checkbox>
                </Stack>
           </Container>

           <Container>
                <Stack>
                    <Button colorScheme="teal" onClick={handleGeneratePassword}>Generate Password</Button>
                    <Button colorScheme="teal" onClick={handleCopyPassword}>Copy Password</Button>
                </Stack>
           </Container>
        </Stack>
    )
}

export default PasswordGeneratorComponent;