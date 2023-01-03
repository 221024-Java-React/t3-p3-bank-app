import { useState, useContext, useMemo, useEffect } from "react";
import styled from "styled-components";
import AccountHeader from "./AccountHeader";
import AccountBox from "./AccountBox";
import { UserContext } from "../../Context/UserContext";
import { UserContextState } from "../../Interfaces/User";
import { Account } from "../../Interfaces/Account";

const Container = styled.div`
    border: 2px solid ${props => props.theme.primaryMed};
    border-radius: ${props => props.theme.borderRadius};
    margin-top: 1.5rem;
    padding: 1rem;
    padding-top: 0;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
`;
const Accounts = styled.div``;
const SummaryFooter = styled.div`
    background: ${props => props.theme.primaryMed};
    border-radius: ${props => props.theme.borderRadius};
`;
const FooterData = styled.h1`
    font-size: ${props => props.theme.fontSize.h1};
    color: white;
    padding: 0 1rem;
`;

const AccountSummary = () => {
    const [bankAccounts, setBankAccounts] = useState<Account[]>([]);
    const [totalBalance, setTotalBalance] = useState<number>(0);
    const { getBankAccounts, currentUser } = useContext(UserContext) as UserContextState;

    useEffect(() => {
        getBankAccounts().then(accounts => {
            accounts ? setBankAccounts(accounts) : setBankAccounts([]);
        });
    }, []);

    useMemo(() => currentUser.accounts?.forEach(ba => setTotalBalance(prev => prev + ba.balance)), []);

    console.log(currentUser)

    return (
        <>
            <AccountHeader
                title="Account Summary"
                btnTitle="Make a Transfer"
                btnLink="/accounts/transfer"
            />
            <Container>
                <Accounts>
                    {bankAccounts.map(ba => {
                        return <AccountBox key={ba.type} name={ba.type} balance={ba.balance} />;
                    })}
                </Accounts>
                <SummaryFooter>
                    <FooterData>Balance Total: $ {totalBalance}</FooterData>
                </SummaryFooter>
            </Container>
        </>
    );
};

export default AccountSummary;
