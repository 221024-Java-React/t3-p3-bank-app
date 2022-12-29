import React, { useState, useContext, useCallback } from "react";
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
`;
const Top = styled.div``;
const AccountName = styled.p`
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
const Calendar = styled.div``;

const initInputs = {
    fromAccount: "",
    toAccount: "",
    amount: 0,
};

const TransferFunds = () => {
    const [inputs, setInputs] = useState(initInputs);
    const { currentUser } = useContext(UserContext) as UserContextState;

    const handleFormChange = (e: React.ChangeEvent<HTMLSelectElement | HTMLInputElement>) => {
        const { name, value } = e.target;
        setInputs(prev => ({ ...prev, [name]: value }));
    };

    const handleFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const { data } = await axInst.post("/accounts/transfer", inputs);

            console.log(data);
            setInputs(initInputs);
        } catch (e) {
            console.log(e);
        }
    };

    // ReactNode type not any
    const accountOptions: any = useCallback(() => {
        currentUser.accounts.map(a => {
            return <option>{a.type}</option>;
        });
    }, []);

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
                        <TransferContainer>
                            <Top>
                                <AccountName>Source Account</AccountName>
                            </Top>
                            <Bottom>
                                <select>{accountOptions}</select>
                                {/* <Data>Balance: ${balance}</Data> */}
                            </Bottom>
                        </TransferContainer>
                        <TransferContainer>
                            <Top>
                                <AccountName>Destination Account</AccountName>
                            </Top>
                            <Bottom>
                                <select></select>
                                {/* <Data>Balance: ${balance}</Data> */}
                            </Bottom>
                        </TransferContainer>
                    </div>
                    <Calendar />
                </TransferContainer>
            </TransferForm>
        </>
    );
};

export default TransferFunds;
