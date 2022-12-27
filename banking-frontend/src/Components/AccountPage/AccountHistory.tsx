import React, { useContext } from "react";
import styled from "styled-components";
import { AccountContextState } from "../../Interfaces/Account";
import { AccountContext } from "../../Context/AccountContext";
import AccountHeader from "./AccountHeader";
import AccountBox from "./AccountBox";

const FilterBox = styled.div`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
`;
const FilterTitle = styled.h3`
    text-align: center;
    margin: 2rem;
`;
const Table = styled.table`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
    border-collapse: collapse;
    margin: 1rem 0;
    width: 100%;
`;
const TH = styled.th`
    border: 2px solid ${props => props.theme.primaryDark};
    border-collapse: collapse;
    text-align: left;
    color: ${props => props.theme.primaryDark};
    padding: 0.25rem;
`;
const TD = styled.td`
    border-left: 2px solid ${props => props.theme.primaryDark};
    color: ${props => props.theme.primaryDark};
    font-weight: bold;
    padding: 0.25rem;
`;

const AccountHistory: React.FC = () => {
    // const { currentAccount } = useContext(AccountContext) as AccountContextState;

    // dummy data
    const currentAccount = {
        type: "Checking",
        balance: 25000,
        transactions: [
            {
                date: "Some Date",
                description: "Some description",
                amount: 100,
                balance: 0,
                type: "Some type",
            },
            {
                date: "Some Date",
                description: "Some description",
                amount: 100,
                balance: 0,
                type: "Some type",
            },
        ],
    };

    return (
        <>
            <AccountHeader
                title="Account History"
                btnTitle="Account Summary"
                btnLink="/accounts/summary"
            />
            <AccountBox name={currentAccount.type} balance={currentAccount.balance} />
            <FilterBox>
                <FilterTitle>Filter Options</FilterTitle>
            </FilterBox>
            <Table>
                <thead>
                    <tr>
                        <TH scope="column">Date</TH>
                        <TH scope="column">Description</TH>
                        <TH scope="column">Amount</TH>
                        <TH scope="column">Balance</TH>
                        <TH scope="column">Type</TH>
                    </tr>
                </thead>
                <tbody>
                    {currentAccount.transactions.map(t => {
                        return (
                            <tr key={t.date + t.amount}>
                                <TD>{t.date}</TD>
                                <TD>{t.description}</TD>
                                <TD>{t.amount}</TD>
                                <TD>{t.balance}</TD>
                                <TD>{t.type}</TD>
                            </tr>
                        );
                    })}
                </tbody>
            </Table>
        </>
    );
};

export default AccountHistory;
