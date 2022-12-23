import React, { useContext } from 'react'
import { useNavigate } from 'react-router'
import styled from 'styled-components'
import { UserContext } from '../../Context/UserContext'
import { UserContextState } from '../../Interfaces/User'

const Container = styled.div`
    height: 80px;
    background-color: ${(props) => props.theme.body};
    color: ${(props) => props.theme.text};
    position: sticky;
    z-index: 2;
    width: 100vw;
    top: 0;
    border-bottom: 1px solid ${(props) => props.theme.border};
`
const Wrapper = styled.div`
    height: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
`
const Left = styled.div`
    flex: 1;
    display: flex;
    align-items: center;
    padding-left: 20px;
`
const Center = styled.div`
    flex: 1;
    display: flex;
    justify-content: center;
`
const Right = styled.div`
    flex: 1;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding-right: 20px;
`
const MenuItem = styled.div`
    cursor: pointer;
    margin-left: 24px;
    height: 100%;
`

const Navbar: React.FC = () => {

    const { currentUser } = useContext(UserContext) as UserContextState;

    const navigate = useNavigate();

    const navigateHome = () => {
        navigate('/')
    }

    const navigateLogin = () => {
        navigate('/login')
    }

    const navigateRegister = () => {
        navigate('/register')
    }

    return (
        <Container>
            <Wrapper>
                <Left>BANK NAME</Left>
                <Center>Center</Center>
                <Right>
                    <MenuItem onClick={navigateHome}>Home</MenuItem>
                    <MenuItem onClick={navigateLogin}>Log In</MenuItem>
                    {
                        currentUser.type === "REP" &&
                        <MenuItem onClick={navigateRegister}>Register</MenuItem>
                    }
                </Right>
            </Wrapper>
        </Container>
    )
}

export default Navbar
