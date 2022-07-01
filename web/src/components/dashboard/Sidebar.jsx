import { makeStyles } from "@material-ui/core/styles";
import { useState, useEffect } from "react";
import { GetCurrentUserAPI } from "../../services/userServices";
import {useNavigate} from "react-router-dom"

const Sidebar = () => {

    const classes = useStyles();

    const navigate = useNavigate();




    const handleRoutePatientAdd = () => {
        navigate("/add-patient");
    }

    const handleRoutePatients = () => {
        navigate("/patients");
    }

    return (
        <div className={classes.mainContainer}>
            <p className={classes.headline}>Digital Medical</p>
                    
            <p className={classes.sidebarList} onClick={handleRoutePatientAdd}>
                Add your patient
            </p>
            <p className={classes.sidebarList} onClick={handleRoutePatients}>
                View patients
            </p>

        </div>
    );
}

export default Sidebar;

const useStyles = makeStyles({
    mainContainer: {
        backgroundColor: "#00B0C4",
        height: "100vh",
        position: "fixed",
        left: "0px",
        width: "25%"
    },

    headline:{
        color: "white",
        fontSize: "32px",
        fontWeight: "400",
        paddingTop: "50px"
    },

    roleHeadline:{
        color: "white",
        fontSize: "24px",
        fontWeight: "400",
        paddingBottom: "40px"
    },

    sidebarList:{
        fontSize: "24px",
        color: "white",
        fontWeight: "300",
        cursor: "pointer",

        "&:hover": {
            color: "black"
        }
    }
});