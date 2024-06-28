import React, { useEffect, useState } from "react";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'; 
import Etudiant from "../models/Etudiant";
import InfoStudent from "../components/InfoStudent/InfoHead";

type Props = {
    tofetch: string;
}
function DisplayStudent({tofetch}:Props){
    const [studentInfo, setStudentInfo] = useState<Etudiant>(
        {
            "studentId": 1,
            "address": "Ambatoroka",
            "lastname": "RAHANETRA",
            "firstname": "Jason",
            "email": "jrahanetra@gmail.com",
            "phoneNumber": "038 77 667 97"
          }
    );

    useEffect(()=>{
        fetch(`http://localhost:8080/students/${tofetch}`)
        .then(response => response.json())
        .then((student) =>{
            setStudentInfo(student);
        }).catch( error => {
            toast.error("Failed to fetch students data.")
            console.error(error)
        })
    }, [])

    return(
        <div className="container-attend">
            <h1>Contain</h1>
            <ToastContainer />
            <InfoStudent std={studentInfo}/>
        </div>
    )
}
export default DisplayStudent;