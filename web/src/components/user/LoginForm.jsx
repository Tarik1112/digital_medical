import { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { LoginAPI } from "../../services/userServices";
import {useNavigate} from "react-router-dom"


const LoginForm = () => {
    const navigate = useNavigate();
    const classes = useStyles();
    const [userEmail, setUserEmail] = useState("");
    const [userPassword, setUserPassword] = useState("");


    const handleSubmit =  async(event) =>{
        
        event.preventDefault();
        const userLogin = await LoginAPI({
            "password": userPassword,
            "email": userEmail
        });
            console.log('userlogin', userLogin);
            if(userLogin.hasOwnProperty('token')){
                console.log("OK");
            }else{
                console.log("Error")
            }
        
    };

    const handleRoute = () => {
        navigate("/register");
    }


    return (
        <div className={classes.mainContainer}>
            <div className={classes.colorContainer}> 
                Login
            </div>
            <div className={classes.formContainer}>
                <form onSubmit={handleSubmit}>
                    <h2>Enter email</h2>
                    <input type="text" placeholder="Email" onChange={(e) => setUserEmail(e.target.value)}/>

                    <h2>Enter password</h2>
                    <input type="text" placeholder="password" onChange={(e) => setUserPassword(e.target.value)}/>

                    <button type="submit">Login</button>
                </form>
                <button onClick={handleRoute}>Register</button>
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


export default LoginForm