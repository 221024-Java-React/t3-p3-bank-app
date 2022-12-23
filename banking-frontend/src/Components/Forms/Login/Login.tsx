import React, { useContext, useState } from "react";
import styled from "styled-components";
import { UserContext } from "../../../Context/UserContext";
import { UserContextState } from "../../../../src/Interfaces/User";
import { axInst } from "../../../Util/axInstance";
import { useNavigate } from "react-router";

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
    email: "",
    password: "",
};

const Login: React.FC = () => {
    const [inputs, setInputs] = useState(initInputs);
    const { loginUser } = useContext(UserContext) as UserContextState;

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setInputs(prev => ({ ...prev, [name]: value }));
    };

    const navigate = useNavigate();

    const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const { data } = await axInst.post("/users/login",
                inputs,
            );

            loginUser(data);
            console.log(data);
            setInputs(initInputs);
            // navigate("/home")
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                <Label>Email:</Label>
                <Input
                    type="text"
                    name="email"
                    value={inputs.email}
                    onChange={handleInputChange}
                ></Input>
                <Label>Password:</Label>
                <Input
                    type="password"
                    name="password"
                    value={inputs.password}
                    onChange={handleInputChange}
                ></Input>
                <SubmitButton type="submit" value="Log In" />
            </Form>
        </Container>
    );
};

export default Login;
