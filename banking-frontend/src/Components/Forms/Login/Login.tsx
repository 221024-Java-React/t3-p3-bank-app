import React, { useContext } from 'react'
import styled from 'styled-components'
import { Context } from '../../../Context/UserContext'
import { UserContextState } from '../../../Types/User'

const Container = styled.div`
`

const Login: React.FC = () => {


    const { loginUser } = useContext(Context) as UserContextState;

    return (
        <Container>
            Login
        </Container>
    )
}

export default Login
