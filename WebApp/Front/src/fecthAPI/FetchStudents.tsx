import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import StudentCustomizeData from "../models/StudentCustomizeData";
import Etudiant from "../models/Etudiant";

function fecthStudent(
  student: Etudiant[],
  setEtudiant: React.Dispatch<React.SetStateAction<Etudiant[]>>
) {
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/students");
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setEtudiant(data);
      } catch (error) {
        toast.error(`Error : ${error}`);
      }
    };
    fetchData();
  }, []);
  return "Success Fetch"; // ou un indicateur de chargement ou un message de succès
}

export default fecthStudent;
