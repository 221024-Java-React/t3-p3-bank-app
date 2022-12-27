import { Link } from "react-router-dom";
import styled from "styled-components";
import GBLogo_Black from "../../Assets/GoodBank-Logo_Black.png";

const Container = styled.div`
    position: sticky;
    bottom: 0;
    display: flex;
    justify-content: space-evenly;
    border: 2px solid ${props => props.theme.border};
    border-radius: ${props => props.theme.borderRadius};
    background: ${props => props.theme.background};
    box-shadow: 0 -20px 0px ${props => props.theme.background};
`;
const Box = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;
const Data = styled.p`
    margin: 0.15rem;
    font-weight: bold;
`;
const Logo = styled.img`
    width: 10rem;
`;

const Footer = () => {
    return (
        <Container>
            <Box>
                <Data>Oakdale, NY</Data>
                <Data>Meadowbrook, PA</Data>
                <Data>Silverlake, NJ</Data>
            </Box>
            <Link to="/">
                <Logo src={GBLogo_Black} alt="Black GoodBank logo with mountains" />
            </Link>
            <Box>
                <Data>(###)-###-####</Data>
            </Box>
        </Container>
    );
};

export default Footer;
