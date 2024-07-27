import React, { useEffect, useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import Etudiant from "../../../models/Etudiant";
import StundentRow from "./StudentData";

function TableData(){
    
    const [etudiantList, setEtudiant] = useState<Etudiant[]>([]);

    const fetchData = async () => {
        try{
            const response = await fetch('http://localhost:8080/students');
            if(!response.ok){
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setEtudiant(data)
        }catch(error){
            toast.error(`Error : ${error}`)
        }
    }

    useEffect(() => {
        fetchData();
    }, [])

    return(
        <div className="container-tab">
            <ToastContainer />
            <table>
                <thead>
                    <tr>
                        <td className="td-head">Roll no</td>
                        <td className="td-head">Name</td>
                        <td className="td-head">Year</td>
                        <td className="td-head">Semester</td>
                        <td className="td-head">Course</td>
                        <td className="td-head">Date</td>
                        <td className="td-head">Hour</td>
                        <td className="td-head">Attend</td>
                    </tr>
                </thead> 
                <tbody>
                        {etudiantList.map(student => (
                            <StundentRow key={student.studentId} etudiant={student}/>
                        ))}
                </tbody>     
            </table>         
        </div>
    )
}
export default TableData;