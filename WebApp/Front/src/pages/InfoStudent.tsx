import React, { useEffect, useState } from "react";
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
        })
    }, [])

    return(
        <div className="container-attend">
            <h1>Contain</h1>
            <InfoStudent std={studentInfo}/>
        </div>
    )
}
export default DisplayStudent;