import React, { useState } from 'react'
import { User, UserContextState } from '../Types/User';

interface ProviderProps {
    children: React.ReactNode;
}

export const Context = React.createContext<UserContextState | null>(null);

const UserProvider: React.FC<ProviderProps> = ({ children }) => {

    const [currentUser, setCurrentUser] = useState<User>({
        userId: '',
        firstName: '',
        lastName: '',
        email: '',
        address: '',
        phoneNumber: '',
        password: '',
    });
    const [users, setUsers] = useState<User[]>([]);
    const [loggedIn, setLoggedIn] = useState<boolean>(false);

    const registerUser = (user: User) => {
        const newUser: User = {
            userId: user.userId,
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            address: user.address,
            phoneNumber: user.phoneNumber,
            password: user.password,
        }
    }

    const updateCurrentUser = (user: User) => {
        setCurrentUser(user);
    }

    const loginUser = (user: User) => {
        setLoggedIn(true);
        setCurrentUser(user);
    }
    const logoutUser = () => {
        setLoggedIn(false);
        setCurrentUser({
            userId: '',
            firstName: '',
            lastName: '',
            email: '',
            phoneNumber: '',
            address: '',
            password: '',
        })
    }


    return (
        <Context.Provider value={{ registerUser, loginUser, currentUser, loggedIn, updateCurrentUser, logoutUser }}>
            {children}
        </Context.Provider>
    )
}

export default UserProvider
