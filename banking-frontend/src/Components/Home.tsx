import React, { useEffect, useState } from 'react'
import styled, { ThemeProvider } from 'styled-components'
import { lightTheme, darkTheme } from './Theme'

const Container = styled.div`
    background-color: ${(props) => props.theme.body};
    color: ${(props) => props.theme.text};
    height: 100vh;
    width: 100vw;
    display: grid;
    place-items: center;
`
const ThemeButton = styled.button`
    position: absolute;
    bottom: 20px;
    right: 15px;
`

const Home: React.FC = () => {

    const [theme, setTheme] = useState('light');

    const themeToggler = () => {
        if (localStorage.getItem('theme') === 'light') {
            setTheme('dark');
            localStorage.setItem('theme', 'dark')
        } else {
            setTheme('light');
            localStorage.setItem('theme', 'light')
        }
    }

    useEffect(() => {
        themeToggler();
    }, [])

    return (
        <ThemeProvider theme={theme === 'light' ? lightTheme : darkTheme}>
            <ThemeButton onClick={themeToggler}>
                {
                    theme === 'light' ?
                        <div>Light</div>
                        :
                        <div>Dark</div>
                }
            </ThemeButton>
            <Container>
                Home
            </Container>
        </ThemeProvider>
    )
}

export default Home
