import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import TableEDT from "../components/WorkingTime/Table/TableEdt";
import DayEDT from "../models/DayEDT";

function WorkingTime() {
    const [weekEDT, setWeekEDT] = useState<DayEDT[]>([]);
    const [allMonday, setAllMonday] = useState<string[]>([]);

    const [dateSelected, setDateSelected] = useState<string>("")

    const fetchEDTData = async (date: string) => {
        const postData = {
            dateLundi: date,
            levelId: "2", // NIVEAU
            majorId: "2", // PARCOURS
        };
        const params = new URLSearchParams(postData).toString();
        try {
            const response = await fetch(`http://localhost:8080/courses?${params}`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setWeekEDT(data);
                
        }catch(error) {
            toast.error(`Error: ${error}`);
        }
    };

    const fetchDate = async () => {
        const postLundi = {
            beginDate: "26-08-2024", // NIVEAU
            endDate: "10-09-2024", // PARCOURS
        };
        const params1 = new URLSearchParams(postLundi).toString();
        try {
            const response = await fetch(`http://localhost:8080/dates?${params1}`);
            if(!response.ok){
                throw new Error(`Network response was not ok`);
            }
            const data = await response.json();
            setAllMonday(data);
        } 
        catch (error) {
            toast.error(`Error: ${error}`)
        }
    };
    // Fetch les dates seulement au début
    useEffect(() => {
        fetchDate();
    }, []);
    // Fetch les EDT quand les dates sont disponibles ou quand dateSelected change
    useEffect(() => {
        if (dateSelected === "") {
            allMonday?.forEach(monday => {
                fetchEDTData(monday);
            });
        } else {
            fetchEDTData(dateSelected);
        }
    }, [allMonday, dateSelected]);  // Ajouter des dépendances correctes

    console.log(allMonday)
    console.log(weekEDT)
    return (
        <div className="container-attend">
            <div className="container-title">
                <h1>Schedule</h1>
            </div>
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>
                <TableEDT edt={weekEDT} />
            </div>
        </div>
    )
}
export default WorkingTime;