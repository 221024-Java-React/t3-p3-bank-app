import axios from "axios";

export const axInst = axios.create({
    baseURL: "http://34.229.147.87:8000",
});

// export const axInst = axios.create({
//     baseURL: "http://localhost:8000",
// });
