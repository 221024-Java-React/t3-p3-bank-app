import React from 'react'
import styled from 'styled-components'

const Container = styled.div`
    height: 60px;
    background-color: ${(props) => props.theme.body};
    color: ${(props) => props.theme.text};
    position: sticky;
    z-index: 2;
    width: 100vw;
    top: 0;
    border-bottom: 1px solid ${(props) => props.theme.border};
`
const Wrapper = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
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
    font-size: 14px;
    cursor: pointer;
    margin-left: 24px;
`

const Navbar: React.FC = () => {
    return (
        <Container>
            <Wrapper>
                <Left>BANK NAME</Left>
                <Center>Center</Center>
                <Right>
                    <MenuItem>Home</MenuItem>
                    <MenuItem>Log In</MenuItem>
                </Right>
            </Wrapper>
        </Container>
    )
}

export default Navbar
