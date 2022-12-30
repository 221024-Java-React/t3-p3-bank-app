import React, { useState, useContext } from "react";
import styled from "styled-components";
import { UserContext } from "../../Context/UserContext";
import { UserContextState } from "../../Interfaces/User";
import { axInst } from "../../Util/axInstance";
import AccountHeader from "./AccountHeader";

const TransferForm = styled.form``;
const TransferContainer = styled.div`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
    margin: 1rem 0;
    padding: 1rem;
`;
const TransferBox = styled.div`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
    margin-bottom: 1rem;
`;
const Top = styled.div``;
const BoxTitle = styled.p`
    font-size: ${props => props.theme.fontSize.h1};
    font-weight: bold;
    margin: 0.25rem 1rem;
    color: ${props => props.theme.primaryDark};
`;
const Bottom = styled.div`
    display: flex;
    justify-content: space-between;
    background: ${props => props.theme.primaryLight};
`;
const Data = styled.p`
    font-size: ${props => props.theme.fontSize.h2};
    font-weight: bold;
    margin-left: 1rem;
    color: ${props => props.theme.primaryDark};
    margin: 1rem 0 0.5rem 1rem;
`;
const Container = styled.div`
    display: flex;
    justify-content: space-evenly;
`;
const AmountBox = styled.div`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
`;
const DateBox = styled.div`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
`;
const MemoBox = styled.div`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
    display: flex;
    justify-content: space-around;
`;

const initInputs = {
    fromAccount: "",
    toAccount: "",
    amount: 0,
};

const dummyAccountData = [
    {
        type: "Checking",
    },
    {
        type: "Savings",
    },
    {
        type: "Credit",
    },
];

const TransferFunds = () => {
    const [inputs, setInputs] = useState(initInputs);
    const { currentUser } = useContext(UserContext) as UserContextState;

    const handleInputChange = (e: React.ChangeEvent<HTMLSelectElement | HTMLInputElement>) => {
        const { name, value } = e.target;
        setInputs(prev => ({ ...prev, [name]: value }));
    };

    const handleFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const { data: transaction } = await axInst.post("/accounts/transfer", inputs);

            setInputs(initInputs);
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <>
            <AccountHeader
                title="Make a Transfer"
                btnTitle="Account Summary"
                btnLink="/accounts/summary"
            />
            <TransferForm onSubmit={handleFormSubmit}>
                <TransferContainer>
                    <div>
                        <TransferBox>
                            <Top>
                                <BoxTitle>Source Account</BoxTitle>
                            </Top>
                            <Bottom>
                                <select onChange={handleInputChange}>
                                    {dummyAccountData.map(a => (
                                        <option key={a.type}>{a.type}</option>
                                    ))}
                                </select>
                                <Data>Balance: $BALANCE</Data>
                            </Bottom>
                        </TransferBox>
                        <TransferBox>
                            <Top>
                                <BoxTitle>Destination Account</BoxTitle>
                            </Top>
                            <Bottom>
                                <select onChange={handleInputChange}>
                                    {dummyAccountData.map(a => (
                                        <option key={a.type}>{a.type}</option>
                                    ))}
                                </select>
                                <Data>Balance: $BALANCE</Data>
                            </Bottom>
                        </TransferBox>
                        <Container>
                            <AmountBox>
                                <Top>
                                    <BoxTitle>Enter Amount</BoxTitle>
                                </Top>
                                <Bottom>
                                    <label htmlFor="amount" />
                                    <input type="number" step="0.01" name="amount" id="amount" />
                                </Bottom>
                            </AmountBox>
                            <DateBox>
                                <Top>
                                    <BoxTitle>Select Date</BoxTitle>
                                </Top>
                                <Bottom>
                                    <label htmlFor="date" />
                                    <input type="date" name="date" id="date" />
                                </Bottom>
                            </DateBox>
                        </Container>
                    </div>
                </TransferContainer>
                <MemoBox>
                    <span>Memo: </span>
                    <label htmlFor="memo" />
                    <input type="text" name="memo" id="memo" />
                    <button type="submit">Submit</button>
                </MemoBox>
            </TransferForm>
        </>
    );
};

export default TransferFunds;
