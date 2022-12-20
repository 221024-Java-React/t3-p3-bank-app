export interface User {
    userId: string;
    firstName: string;
    lastName: string;
    email: string;
    address: string;
    phoneNumber: string;
    password: string
}

export interface UserContextState {
    currentUser: User;
    loggedIn: boolean;
    registerUser: (user: User) => void;
    loginUser: (user: User) => void;
    updateCurrentUser: (user: User) => void;
    logoutUser: () => void;
}
