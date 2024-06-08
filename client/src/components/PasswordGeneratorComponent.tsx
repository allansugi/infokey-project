import { Button, Card, CardBody, Checkbox, Container, NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper, Stack, Text } from "@chakra-ui/react"
import { useState } from "react";

const PasswordGeneratorComponent = () => {

    const [password, setPassword] = useState("");

    const handleGeneratePassword = () => {
        setPassword("you pressed generate password");
    }

    const handleCopyPassword = () => {
        setPassword("You pressed Copy password");
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
                <NumberInput defaultValue={8} min={8} max={20}>
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
                    <Checkbox>
                        ABC
                    </Checkbox>
                    <Checkbox>
                        0123
                    </Checkbox>
                    <Checkbox>
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