import React, {useEffect, useState} from "react";
import { toast } from "react-toastify";
import StudentCustomizeData from "../models/StudentCustomizeData";

function FecthRegistration(
    studentCustomizeData: StudentCustomizeData[], 
    setCustomizeData : React.Dispatch<React.SetStateAction<StudentCustomizeData[]>>
){
    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await fetch('http://localhost:8080/registrations');
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setCustomizeData(data);
          } catch (error) {
            toast.error(`Error: ${error}`);
          }
        };
    
        fetchData();
      }, [setCustomizeData]);
      return "Success Fetch"; // ou un indicateur de chargement ou un message de succès
}

export default FecthRegistration