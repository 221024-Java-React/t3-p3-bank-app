import React, { useState } from "react";
import styled from "styled-components";
import { axInst } from "../../../Util/axInstance";

const Container = styled.div`
    display: grid;
    place-items: center;
    height: 100%;
`;
const Form = styled.form`
    display: flex;
    flex-direction: column;
`;
const Label = styled.label`
    margin: 3px;
`;
const Input = styled.input`
    margin: 10px;
`;
const SubmitButton = styled.input`
    margin: 10px;
`;

const initInputs = {
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    address: "",
    accountType: "",
};

const Register: React.FC = () => {
    const [inputs, setInputs] = useState(initInputs);

    const handleFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setInputs(prev => ({ ...prev, [name]: value }));
    };

    const handleFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            await axInst.post("/users/register", inputs);

            setInputs(initInputs);
            console.log(inputs);
        } catch (e) {
            console.log(e, "this is the error");
        }
    };

    return (
        <Container>
            <Form onSubmit={handleFormSubmit}>
                <Label>First Name:</Label>
                <Input
                    type="text"
                    name="firstName"
                    value={inputs.firstName}
                    onChange={handleFormChange}
                ></Input>
                <Label>Last Name:</Label>
                <Input
                    type="text"
                    name="lastName"
                    value={inputs.lastName}
                    onChange={handleFormChange}
                ></Input>
                <Label>Email:</Label>
                <Input
                    type="text"
                    name="email"
                    value={inputs.email}
                    onChange={handleFormChange}
                ></Input>
                <Label>Phone Number:</Label>
                <Input
                    type="text"
                    name="phoneNumber"
                    value={inputs.phoneNumber}
                    onChange={handleFormChange}
                ></Input>
                <Label>Address:</Label>
                <Input
                    type="text"
                    name="address"
                    value={inputs.address}
                    onChange={handleFormChange}
                ></Input>
                <Label>Checking, Savings, or Both:</Label>
                <Input
                    type="text"
                    name="accountType"
                    value={inputs.accountType || ""}
                    onChange={handleFormChange}
                ></Input>
                <SubmitButton type="submit" value="Register Member" />
            </Form>
        </Container>
    );
};

export default Register;
