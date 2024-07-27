import React, { useState } from "react";
import BoxDash from "./Common";
import VectorYear from "../../../assets/vectorYear";
import VectorStudent from "../../../assets/vectorStudent";
import VectorWoman from "../../../assets/vectorWoman";
import VectorMan from "../../../assets/vectorMan";
import StudentCustomizeData from "../../../models/StudentCustomizeData";
import FecthRegistration from "../../../fecthAPI/FetchRegistrations";

function ShowAllDash(){
    const [studentCustomizeData, setCustomizeData] = useState<StudentCustomizeData[]>([]);

    FecthRegistration(studentCustomizeData, setCustomizeData)

    return (
        <div className="div-dashs-container">
            <BoxDash 
                name="Année" 
                number={7} 
                color="#22BBEA"
                vector={VectorYear}/>
            <BoxDash 
                name="Étudiants" 
                number={studentCustomizeData.length} 
                color="#CF2E2E"
                vector={VectorStudent}/>
            <BoxDash 
                name="Filles" 
                number={90} 
                color="#22BBEA"
                vector={VectorWoman}/>
            <BoxDash 
                name="Garçons" 
                number={160} 
                color="#FF9933"
                vector={VectorMan}/>
        </div>
    )
}

export default ShowAllDash;