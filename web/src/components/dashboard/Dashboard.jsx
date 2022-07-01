import { GetCurrentUserAPI } from "../../services/userServices";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import { useSelector } from "react-redux"
import Sidebar from "./Sidebar";

const Dashboard = () => {

    const location = useLocation();
    const classes = useStyles();
    const [currentUser, setCurrentUser] = useState("");
    const [currentUserId, setCurrentUserId] = useState(location.state);

    const info = useSelector((state) => {
        return state.authenticateToken;
      });

    console.log(info);

    async function getSingleUser() {
        await GetCurrentUserAPI(info.authenticateInfo.token).then(async (res)=>{
            setCurrentUser(res);
        })
    }

    useEffect(() => {
        getSingleUser()
    }, []);

    

    return (
        
        <div className={classes.mainContainer}>
            <Sidebar />
            <div className={classes.contentContainer}>
                <p className={classes.welcome}>Hello Mr. {currentUser.name}</p>
            </div>
        
        </div>
    );
}

export default Dashboard


const useStyles = makeStyles({
    mainContainer: {
        
    },
    contentContainer:{
        width: "75%",
        position: "fixed",
        right: "0px"
    },

    welcome: {
        fontSize: "38px",
        fontWeight: "500",
        padding: 30
    }
})