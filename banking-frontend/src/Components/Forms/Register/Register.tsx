import axios from "axios";
import React, { useContext, useState } from "react";
import styled from "styled-components";
import { Context } from "../../../Context/UserContext";
import { User, UserContextState } from "../../../Interfaces/User";

const Container = styled.div``;
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

const Register: React.FC = () => {
  const { registerUser } = useContext(Context) as UserContextState;

  const [inputs, setInputs] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    address: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const name = e.target.name;
    const value = e.target.value;
    setInputs(values => ({ ...values, [name]: value }));
  };

  const handleSubmit = (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(inputs);
    handleRegister();
    setInputs({
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      address: "",
    });
  };

  const handleRegister = async () => {
    let register = inputs;

    try {
      const res = await axios.post(
        "http://localhost:8000/users/register",
        register
      );
      const user = await res.data;
    } catch (e) {}
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Label>First Name:</Label>
        <Input
          type="text"
          name="firstName"
          value={inputs.firstName || ""}
          onChange={handleChange}
        ></Input>
        <Label>Last Name:</Label>
        <Input
          type="text"
          name="lastName"
          value={inputs.lastName || ""}
          onChange={handleChange}
        ></Input>
        <Label>Email:</Label>
        <Input
          type="text"
          name="email"
          value={inputs.email || ""}
          onChange={handleChange}
        ></Input>
        <Label>Phone Number:</Label>
        <Input
          type="text"
          name="phoneNumber"
          value={inputs.phoneNumber || ""}
          onChange={handleChange}
        ></Input>
        <Label>Address:</Label>
        <Input
          type="text"
          name="address"
          value={inputs.address || ""}
          onChange={handleChange}
        ></Input>
        <SubmitButton type="submit" value="Register Member" />
      </Form>
    </Container>
  );
};

export default Register;
