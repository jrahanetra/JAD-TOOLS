import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import Level from "../models/Level";

function fecthLevel(
  AllLevel: Level[],
  setLevel: React.Dispatch<React.SetStateAction<Level[]>>
) {
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/levels");
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setLevel(data);
      } catch (error) {
        toast.error(`Error: ${error}`);
      }
    };

    fetchData();
  }, [setLevel]);
  return "Success Fetch"; // ou un indicateur de chargement ou un message de succès
}

export default fecthLevel;
