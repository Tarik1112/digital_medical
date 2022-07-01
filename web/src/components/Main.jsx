import { BrowserRouter,Redirect ,Route, Routes, Link, useRoutes } from "react-router-dom";
import RegisterForm from "../components/user/RegisterForm";
import LoginForm from "../components/user/LoginForm";
import Dashboard from "./dashboard/Dashboard";
import AddPatient from "./dashboard/AddPatient";
import PatientList from "./dashboard/PatientList";


const Main = () => {
 
    const Paths = () => useRoutes([
        {path: "/", element: <LoginForm/>},
        {path: "/login", element: <LoginForm />},
        {path: "/register", element: <RegisterForm />},
        {path: "/dashboard", element: <Dashboard />},
        {path: "/add-patient", element: <AddPatient />},
        {path: "/patients", element: <PatientList />}
    ]);


    return (
        <BrowserRouter>
            <Paths />
        </BrowserRouter>  
    );
}

export default Main