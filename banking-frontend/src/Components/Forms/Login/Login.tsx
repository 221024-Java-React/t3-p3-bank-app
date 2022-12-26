import React, { useContext, useState } from "react";
import styled from "styled-components";
import { UserContext } from "../../../Context/UserContext";
import { UserContextState } from "../../../../src/Interfaces/User";
import { axInst } from "../../../Util/axInstance";
import { useNavigate } from "react-router";
import { Account } from "../../../Interfaces/Account";

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
    const { setCurrentUser, loginUser, currentUser } = useContext(UserContext) as UserContextState;
    const navigate = useNavigate();

    const handleFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setInputs(prev => ({ ...prev, [name]: value }));
    };

    // const getAccounts = async () => {
    //     try {
    //         const { data: accounts } = await axInst.post<Account[]>("/accounts/account", {
    //             headers: { "Access-Control-Allow-Origin": "*" },
    //             params: { userId: currentUser.userId },
    //         });
    //         console.log(accounts, "accounts");
    //         console.log(currentUser, "before setting currentuser account");
    //         setCurrentUser({ ...currentUser, accounts: accounts });
    //         console.log(currentUser, "after setting currentuser account");

    //         console.log(currentUser);
    //     } catch (e) {
    //         console.log(e);
    //     }
    // };

    const handleFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const { data } = await axInst.post("/users/login", inputs);

            loginUser(data);
            console.log(data);
            setInputs(initInputs);
            navigate("/home");
        } catch (e) {
            console.log(e);
        }
    };

    // useEffect(() => {
    //     getAccounts();
    // }, []);

    // console.log(currentUser);

    return (
        <Container>
            <Form onSubmit={handleFormSubmit}>
                <Label>Email:</Label>
                <Input
                    type="text"
                    name="email"
                    value={inputs.email}
                    onChange={handleFormChange}
                ></Input>
                <Label>Password:</Label>
                <Input
                    type="password"
                    name="password"
                    value={inputs.password}
                    onChange={handleFormChange}
                ></Input>
                <SubmitButton type="submit" value="Log In" />
            </Form>
        </Container>
    );
};

export default Login;
