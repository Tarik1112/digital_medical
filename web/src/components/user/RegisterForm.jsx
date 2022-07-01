import { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { RegisterAPI } from "../../services/userServices";
import {useNavigate} from "react-router-dom"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

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
        
        if(userDepartment === "1112"){
        event.preventDefault();
        await RegisterAPI({
            "password": userPassword,
            "email": userEmail,
            "name": userName,
            "surname": userSurname,
            "birthDate": userBirth,
            "gender": userGender,
            "department": userDepartment,
        }).then(async (res) => {
            if (res.status === 400) {
                console.log("error")
              }
              else {
               
                navigate("/login");

              }
          });
        }else{
            event.preventDefault();
            alert("Code is invalid")
            
        }

        // event.preventDefault();
        // const userRegister = await RegisterAPI({
        //     "password": userPassword,
        //     "email": userEmail,
        //     "name": userName,
        //     "surname": userSurname,
        //     "birthDate": userBirth,
        //     "gender": userGender,
        //     "department": userDepartment,
        // });
        //     console.log('userRegister', userRegister);
        
    };

    const handleRoute = () => {
        navigate("/login");
    }


    return (
        <div className={classes.mainContainer}>
            <div className={classes.headline}>
                <p>Welcome to Digital Medical.</p>
            </div>
            <div className={classes.formContainer}>

                <form className="rounded p-4 p-sm-3" onSubmit={handleSubmit}>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter name" onChange={(e) => setUserName(e.target.value)}/>
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">Surname</Form.Label>
                        <Form.Control type="text" placeholder="Enter surname" onChange={(e) => setUserSurname(e.target.value)}/>   
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">Email</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" onChange={(e) => setUserEmail(e.target.value)}/>   
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">Password</Form.Label>
                        <Form.Control type="password" placeholder="Enter password" onChange={(e) => setUserPassword(e.target.value)}/>
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">Date of Birth</Form.Label>
                        <Form.Control type="date" placeholder="Enter date of birth" onChange={(e) => setUserBirth(e.target.value)}/>   
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">Gender</Form.Label>
                        <Form.Control type="text" placeholder="Enter gender" onChange={(e) => setUserGender(e.target.value)}/>   
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label className="label">DepartmentCode</Form.Label>
                        <Form.Control type="password" placeholder="Enter your code" onChange={(e) => setUserDepartment(e.target.value)}/>
                    </Form.Group>

                    

                    <button className="btn btn-primary" type="submit" disabled={!userName || !userSurname || !userEmail || !userPassword} >Register</button>

                    <p className="mt-3">You have an account? <a href="/login">Sign in</a></p>


                </form>
            </div>
        </div>
    )
}

const useStyles = makeStyles({

    mainContainer:{
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
        borderRadius: "7%"
    }

});


export default RegisterForm