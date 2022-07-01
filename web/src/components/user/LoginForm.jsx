import { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { LoginAPI } from "../../services/userServices";
import {useNavigate} from "react-router-dom"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {useDispatch} from "react-redux";
import {setAuthenticateToken} from "../../redux/actions/Actions";

const LoginForm = () => {
    const navigate = useNavigate();
    const classes = useStyles();
    const [userEmail, setUserEmail] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const dispatch = useDispatch();

    const handleSubmit =  async(event) =>{
        
        event.preventDefault();
        const userLogin = await LoginAPI({
            "password": userPassword,
            "email": userEmail
        });
            console.log('userlogin', userLogin);
            if(userLogin.hasOwnProperty('token')){
                console.log("OK");
                navigate("/dashboard", {state: userLogin});
                dispatch(setAuthenticateToken(userLogin));
            }else{
                console.log("Error")
            }
        
    };



    return (
        <div className={classes.mainContainer}>
            <div className={classes.headline}>
                <p>Login to the system.</p>
            </div>
            <div className={classes.formContainer}>
                <form className="rounded p-4 p-sm-3" onSubmit={handleSubmit}>
                    <Form.Group className="mb-3">
                        <Form.Label className="label">Email</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" onChange={(e) => setUserEmail(e.target.value)}/>                
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" onChange={(e) => setUserPassword(e.target.value)} />
                    </Form.Group>
                    <button className="btn btn-primary" type="submit">Login</button>

                    <p className="mt-3">You don't have an account? <a href="/register">Sign up</a></p>
                </form>
            </div>
        </div>
    )
}

const useStyles = makeStyles({

    mainContainer: {
        height: "100vh",
        backgroundColor: "#90DDF0"
    },

    headline:{
        paddingTop: "50px",
        fontSize: "32px",
    },

    formContainer: {
        width: "55%",
        margin: "50px auto",
        backgroundColor: "white",
    }

});


export default LoginForm