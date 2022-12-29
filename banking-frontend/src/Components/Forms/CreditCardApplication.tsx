import React, { useState } from "react";
import styled from "styled-components";
import AccountHeader from "../AccountPage/AccountHeader";

const Container = styled.div``;
const ApplicationForm = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
`;
const SectionHeader = styled.div`
    padding: 0.5em;
    background-color: ${(props) => props.theme.background};
    border-radius: 3px 3px 0 0;
    font-weight: bold;
    color: ${(props) => props.theme.color};
`;
const SectionWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border: 2px solid ${(props) => props.theme.primaryMed};
    border-radius: ${(props) => props.theme.borderRadius};
    margin-top: 1.5rem;
    /* padding: 1rem; */
    justify-content: space-between;
    background: ${(props) => props.theme.primaryLightest};
    color: ${(props) => props.theme.primaryDark};
`;
const InputWrapper = styled.div`
    display: flex;
    /* padding-block: 0.2em; */
    padding: 0.5em;
`;
const Label = styled.label`
    font-weight: bold;
    padding-right: 0.5em;
`;
const Input = styled.input`
    background: transparent;
    border: 1px solid ${(props) => props.theme.border};
    border-radius: ${(props) => props.theme.borderRadius};
    padding-inline: 5px;
    outline: none;
`;
const FinalSection = styled.div`
    display: flex;
`;
const SubmitButton = styled.button`
    margin-top: 1.5rem;
    display: flex;
    justify-content: center;
    background-color: ${(props) => props.theme.background};
    border: 2px solid ${(props) => props.theme.primaryMed};
    border-radius: ${(props) => props.theme.borderRadius};
    font-weight: bold;
    font-size: 2em;
    color: ${(props) => props.theme.color};
    outline: none;
    cursor: pointer;
`;

const initInputs = {};

const CreditCardApplication = () => {
    const [inputs, setInputs] = useState(initInputs);

    const handleFormSubmit = (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
    };

    return (
        <>
            <AccountHeader
                title="Credit Card Application"
                btnTitle={undefined}
                btnLink={undefined}
            />
            <Container>
                <ApplicationForm onSubmit={handleFormSubmit}>
                    <SectionWrapper>
                        <SectionHeader>Basic Questions</SectionHeader>
                        <InputWrapper>
                            <Label style={{ flex: 1 }}>
                                Do you have a GoodBank credit card already?
                            </Label>
                            <Input
                                type="radio"
                                id="yes"
                                name="q1"
                                value="yes"
                            />
                            <Label htmlFor="yes">Yes</Label>
                            <Input type="radio" id="no" name="q1" value="no" />
                            <Label htmlFor="no">No</Label>
                        </InputWrapper>
                        <InputWrapper>
                            <Label style={{ flex: 1 }}>
                                Are you over the age of 16?
                            </Label>
                            <Input
                                type="radio"
                                id="yes"
                                name="q1"
                                value="yes"
                            />
                            <Label htmlFor="yes">Yes</Label>
                            <Input type="radio" id="no" name="q1" value="no" />
                            <Label htmlFor="no">No</Label>
                        </InputWrapper>
                    </SectionWrapper>
                    <SectionWrapper>
                        <SectionHeader>Income Information</SectionHeader>
                        <InputWrapper>
                            <Label>Total Net Worth:</Label>
                            <Input
                                type="text"
                                id="net-worth"
                                name="q1"
                                placeholder="$ 0.00"
                                style={{ flex: 1 }}
                            />
                        </InputWrapper>
                        <InputWrapper>
                            <Label>Monthly Income:</Label>
                            <Input
                                type="text"
                                id="net-worth"
                                name="q1"
                                placeholder="$ 0.00"
                                style={{ flex: 1 }}
                            />
                        </InputWrapper>
                    </SectionWrapper>
                    <SectionWrapper>
                        <SectionHeader>Debt Information</SectionHeader>
                        <InputWrapper>
                            <Label>Monthly Car Payment</Label>
                            <Input
                                type="text"
                                id="net-worth"
                                name="q1"
                                placeholder="$ 0.00"
                                style={{ flex: 1 }}
                            />
                        </InputWrapper>
                        <InputWrapper>
                            <Label>Monthly Rent/Mortgage:</Label>
                            <Input
                                type="text"
                                id="net-worth"
                                name="q1"
                                placeholder="$ 0.00"
                                style={{ flex: 1 }}
                            />
                        </InputWrapper>
                        <InputWrapper>
                            <Label>Sum All Other Monthly Debt Payments:</Label>
                            <Input
                                type="text"
                                id="net-worth"
                                name="q1"
                                placeholder="$ 0.00"
                                style={{ flex: 1 }}
                            />
                        </InputWrapper>
                        <InputWrapper>
                            <Label>
                                Sum All Other Monthly Credit Card Limits:
                            </Label>
                            <Input
                                type="text"
                                id="net-worth"
                                name="q1"
                                placeholder="$ 0.00"
                                style={{ flex: 1 }}
                            />
                        </InputWrapper>
                    </SectionWrapper>
                    <FinalSection>
                        <SectionWrapper style={{ flex: 1, marginRight: "1em" }}>
                            <SectionHeader>Enter DOB:</SectionHeader>
                            <InputWrapper>
                                <Input
                                    type="date"
                                    id="net-worth"
                                    name="q1"
                                    style={{ flex: 1, color: "#154481" }}
                                />
                            </InputWrapper>
                        </SectionWrapper>
                        <SectionWrapper style={{ flex: 1 }}>
                            <SectionHeader>Enter FICO Score</SectionHeader>
                            <InputWrapper>
                                <Input
                                    type="text"
                                    id="net-worth"
                                    name="q1"
                                    placeholder="0-850"
                                    style={{ flex: 1 }}
                                />
                            </InputWrapper>
                        </SectionWrapper>
                    </FinalSection>
                    <SubmitButton>SUBMIT</SubmitButton>
                </ApplicationForm>
            </Container>
        </>
    );
};

export default CreditCardApplication;
