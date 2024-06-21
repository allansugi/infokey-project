import { InputRightElement, Button, Input, InputGroup } from "@chakra-ui/react";
import { useState } from "react";

const PasswordInput = () => {
    const [show, setShow] = useState(false);

    const handleShowPassword = () => {
        setShow(!show);
    }

    return (
        <InputGroup size='md'>
            <Input
                pr='4.5rem'
                type={show ? 'text' : 'password'}
                placeholder='Enter password'
            />
            <InputRightElement width='4.5rem'>
                <Button h='1.75rem' size='sm' onClick={handleShowPassword}>
                {show ? 'Hide' : 'Show'}
                </Button>
            </InputRightElement>
        </InputGroup>
    )
}

export default PasswordInput;