import React, { useEffect, useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import styled, { ThemeProvider } from 'styled-components'
import Login from './Components/Forms/Login/Login'
import Register from './Components/Forms/Register/Register'
import Navbar from './Components/Navbar/Navbar'
import { lightTheme, darkTheme } from './Components/Theme'
import WelcomePage from './Components/WelcomePage/WelcomePage'

const Container = styled.div`
    background-color: ${(props) => props.theme.body};
    color: ${(props) => props.theme.text};
    height: 100vh;
    width: 100vw;
    display: flex;
    flex-direction: column;
`
const ThemeButton = styled.button`
    position: absolute;
    bottom: 20px;
    right: 15px;
`

function App() {

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
                <Navbar />
                <Routes>
                    <Route path='/' element={<WelcomePage />} />
                    <Route path='/register' element={<Register />} />
                    <Route path='/login' element={<Login />} />
                </Routes>
            </Container>
        </ThemeProvider>
    );
}

export default App;
