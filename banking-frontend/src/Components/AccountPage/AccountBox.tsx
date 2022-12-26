import React from "react";
import styled from "styled-components";

const Container = styled.div`
    border: 2px solid ${props => props.theme.primaryDark};
    border-radius: ${props => props.theme.borderRadius};
    margin: 1rem;
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

type AccountBoxPropTypes = {
    name: string;
    balance: number;
};

const AccountBox: React.FC<AccountBoxPropTypes> = ({ name, balance }) => {
    return (
        <Container>
            <Top>
                <AccountName>{name}</AccountName>
            </Top>
            <Bottom>
                <Data>Available: ${balance}</Data>
                <Data>Current: ${balance}</Data>
                <Data />
            </Bottom>
        </Container>
    );
};

export default AccountBox;
