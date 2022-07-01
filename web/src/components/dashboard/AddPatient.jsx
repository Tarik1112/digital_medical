import Sidebar from "../dashboard/Sidebar"
import {useState} from "react"
import { makeStyles } from "@material-ui/core/styles";
import { RegisterPatientAPI } from "../../services/userServices";
import {useNavigate} from "react-router-dom"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

const AddPatient = () =>{

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
        await RegisterPatientAPI({
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
               
                navigate("/dashboard");

              }
          });
        }else{
            event.preventDefault();
            alert("Enter valid code")
            
        }
    }

    return (
        <div>
        <Sidebar />
        <div className={classes.formContainer} >
             
             <p className={classes.headline}>Enter patients data</p>

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
                <Form.Control type="date" placeholder="Enter date" onChange={(e) => setUserBirth(e.target.value)}/>   
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label className="label">Gender</Form.Label>
                <Form.Control type="text" placeholder="Enter gender" onChange={(e) => setUserGender(e.target.value)}/>   
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label className="label">Department Code</Form.Label>
                <Form.Control type="password" placeholder="Enter code" onChange={(e) => setUserDepartment(e.target.value)}/>
            </Form.Group>



            <button className="btn btn-primary" type="submit" disabled={!userName || !userSurname || !userEmail || !userPassword} >Register Patient</button>


        </form>
        </div>
        </div>
    );
}

export default AddPatient;

const useStyles = makeStyles({
    formContainer: {
        position: "fixed",
        right: "0",
        width: "75%"
    },

    headline: {
        fontSize: "32px",
        fontWeight: "400",
        padding: "20px 0px"
    }
})