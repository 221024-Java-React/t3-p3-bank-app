import axios from 'axios'
import React, { useContext, useState } from 'react'
import styled from 'styled-components'
import { Context } from '../../../Context/UserContext'
import { UserContextState } from '../../../Types/User'

const Container = styled.div`
`
const Form = styled.form`
    display: flex;
    flex-direction: column;
`
const Label = styled.label`
    margin: 3px;
`
const Input = styled.input`
    margin: 10px;
`
const SubmitButton = styled.input`
    margin: 10px;
`

const Login: React.FC = () => {


    const { loginUser } = useContext(Context) as UserContextState;

    const [inputs, setInputs] = useState({
        email: '',
        password: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const name = e.target.name;
        const value = e.target.value;
        setInputs(values => ({ ...values, [name]: value }))
    }

    const handleSubmit = (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log(inputs);
        handleRegister();
        setInputs({
            email: '',
            password: '',
        });
    }

    const handleRegister = async () => {
        let register = inputs;

        try {
            const res = await axios.post('http://localhost:8000/users/login', register);
            const user = await res.data;
            loginUser(user);
        } catch (e) {
        }

    };

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                <Label>Email:</Label>
                <Input type='text' name='email' value={inputs.email || ""} onChange={handleChange}></Input>
                <Label>Password:</Label>
                <Input type='password' name='password' value={inputs.password || ""} onChange={handleChange}></Input>
                <SubmitButton type='submit' value='Register Member' />
            </Form>
        </Container>
    )
}

export default Login