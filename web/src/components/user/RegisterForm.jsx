import { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { RegisterAPI } from "../../services/userServices";
import {useNavigate} from "react-router-dom"


const RegisterForm = () => {
    const navigate = useNavigate();
    const classes = useStyles();
    const [userEmail, setUserEmail] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [userName, setUserName] = useState("");
    const [userSurname, setUserSurname] = useState("");
    const [userBirth, setUserBirth] = useState("");
    const [userGender, setUserGender] = useState("");
    const [userDepartment, setUserDepartment] = useState("");


    const handleSubmit =  async(event) =>{
        
        event.preventDefault();
        const userRegister = await RegisterAPI({
            "password": userPassword,
            "email": userEmail,
            "name": userName,
            "surname": userSurname,
            "birthDate": userBirth,
            "gender": userGender,
            "department": userDepartment,
        });
            console.log('userRegister', userRegister);
        
    };

    const handleRoute = () => {
        navigate("/login");
    }


    return (
        <div className={classes.mainContainer}>
            <div className={classes.colorContainer}> 
                Login
            </div>
            <div className={classes.formContainer}>
                <form onSubmit={handleSubmit}>
                    <h2>Enter name</h2>
                    <input type="text" placeholder="Email" onChange={(e) => setUserName(e.target.value)}/>

                    <h2>Enter surname</h2>
                    <input type="text" placeholder="Email" onChange={(e) => setUserSurname(e.target.value)}/>

                    <h2>Enter date of birth</h2>
                    <input type="text" placeholder="Email" onChange={(e) => setUserBirth(e.target.value)}/>

                    <h2>Enter gender</h2>
                    <input type="text" placeholder="Email" onChange={(e) => setUserGender(e.target.value)}/>

                    <h2>Enter department</h2>
                    <input type="text" placeholder="Email" onChange={(e) => setUserDepartment(e.target.value)}/>

                    <h2>Enter email</h2>
                    <input type="text" placeholder="Email" onChange={(e) => setUserEmail(e.target.value)}/>

                    <h2>Enter password</h2>
                    <input type="text" placeholder="password" onChange={(e) => setUserPassword(e.target.value)}/>

                    <button type="submit">Register</button>
                </form>
                <button onClick={handleRoute}>Login</button>
            </div>
        </div>
    )
}

const useStyles = makeStyles({

    mainContainer: {
        display: "flex"
    },

    colorContainer: {
        width: "35%",
        backgroundColor: "#90DDF0"
    },

    formContainer: {
        width: "55%",
        margin: "0 auto"
    }

});


export default RegisterForm