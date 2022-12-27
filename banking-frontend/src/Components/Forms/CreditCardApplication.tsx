import React, { useState } from "react";
import styled from "styled-components";
import AccountHeader from "../AccountPage/AccountHeader";

const Container = styled.div`
    border: 2px solid ${props => props.theme.primaryMed};
    border-radius: ${props => props.theme.borderRadius};
    margin-top: 1.5rem;
    padding: 1rem;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
`;
const ApplicationForm = styled.form``;
const Label = styled.label``;
const Input = styled.input``;

const initInputs = {};

const CreditCardApplication = () => {
    const [inputs, setInputs] = useState(initInputs);

    const handleFormSubmit = (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
    };

    return (
        <>
            <AccountHeader
                title="Apply for a Credit Card"
                btnTitle={undefined}
                btnLink={undefined}
            />
            <Container>
                <ApplicationForm onSubmit={handleFormSubmit}>
                    <Label>Do you have a GoodBank credit card already?</Label>
                    <Input type="radio" id="yes" name="q1" value="yes" />
                    <Label htmlFor="yes">Yes</Label>
                    <Input type="radio" id="no" name="q1" value="no" />
                    <Label htmlFor="no">No</Label>
                </ApplicationForm>
            </Container>
        </>
    );
};

export default CreditCardApplication;
