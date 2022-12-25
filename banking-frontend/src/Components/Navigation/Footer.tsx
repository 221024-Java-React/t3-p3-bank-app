import React from "react";
import styled from "styled-components";

const Container = styled.div`
    position: sticky;
    bottom: 0;
    margin-top: 5rem;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    padding: 1rem;
`;

const Footer = () => {
    return <Container>Footer</Container>;
};

export default Footer;
