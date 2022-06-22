import { BrowserRouter,Redirect ,Route, Routes, Link, useRoutes } from "react-router-dom";
import RegisterForm from "../components/user/RegisterForm";
import LoginForm from "../components/user/LoginForm";



const Main = () => {
 
    const Paths = () => useRoutes([
        {path: "/login", element: <LoginForm />},
        {path: "/register", element: <RegisterForm />},
    ]);


    return (
        <BrowserRouter>
            <Paths />
        </BrowserRouter>  
    );
}

export default Main