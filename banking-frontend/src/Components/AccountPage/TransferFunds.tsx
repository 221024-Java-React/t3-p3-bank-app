import React, { useState } from "react";
import styled from "styled-components";
import { axInst } from "../../Util/axInstance";

const Container = styled.div`
    display: grid;
    place-items: center;
    height: 100%;
`;
const Title = styled.h1``;

const initInputs = {
    fromAccount: "",
    toAccount: "",
    amount: 0,
};

const TransferFunds = () => {
    const [inputs, setInputs] = useState(initInputs);

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

    return (
        <Container>
            <Title>Transfer Funds</Title>
            <form onSubmit={handleFormSubmit}>
                <label htmlFor="fromAccount">From:</label>
                <select name="fromAccount" id="fromAccount" onChange={handleFormChange}>
                    <option>Checking</option>
                    <option>Savings</option>
                </select>
                <label htmlFor="toAccount">To:</label>
                <select name="toAccount" id="fromAccount" onChange={handleFormChange}>
                    <option>Checking</option>
                    <option>Savings</option>
                </select>
                <label htmlFor="amount">Amoung:</label>
                <input type="number" step="0.01" onChange={handleFormChange} />
            </form>
        </Container>
    );
};

export default TransferFunds;
