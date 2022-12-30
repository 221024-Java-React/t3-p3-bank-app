import { Account } from "./Account";

export interface User {
    userId: string;
    type: string;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    address: string;
    phoneNumber: string;
    accounts: Account[];
}

export interface UserContextState {
    currentUser: User;
    setCurrentUser: (user: User) => void;
    loginUser: (email: string, password: string) => void;
    authenticateUser: (email: string, passcode: string) => void;
    logoutUser: () => void;
    getBankAccounts: () => Promise<Account[] | undefined>;
}
