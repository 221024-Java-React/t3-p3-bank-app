import styled from "styled-components";

const Container = styled.div`
    width: 100%;
    height: 50vh;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 3px solid black;
`;
const Title = styled.h1``;

const PageNotFound = () => {
    return (
        <Container>
            <Title>Page Not Found</Title>
        </Container>
    );
};

export default PageNotFound;
