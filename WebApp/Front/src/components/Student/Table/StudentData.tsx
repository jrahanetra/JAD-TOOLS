import React, { useState } from "react";
import Etudiant from "../../../models/Etudiant";
import StudentCustomizeData from "../../../models/StudentCustomizeData";

type Props = {
    etudiant: StudentCustomizeData,
    borderColor?: string,
    onStudentClick: (id: number) => void,  
}

function StundentRow({ etudiant, borderColor, onStudentClick}: Props) {
    const [color, setColor] = useState<string>();

    const showBorder = () =>{
        setColor(borderColor)
    }
    const hideBorder = () =>{
        setColor("3px solid #EBEBED")
    }  

    const handleClick = () => {
        // Appeler la fonction de rappel avec l'ID de l'étudiant
        onStudentClick(etudiant.studentDto.studentId);
    };

    return (
        <tr onClick={handleClick} onMouseEnter={showBorder} onMouseLeave={hideBorder} style={{borderBottom: color}}>
            <td>{etudiant.studentDto.studentId}</td>
            <td>{etudiant.studentDto.lastname}</td>
            <td>{etudiant.studentDto.firstname}</td>
            <td>{etudiant.levelDto.levelName}</td>
        </tr>
    );
}
StundentRow.defaultProps = {
    borderColor: "4px solid #257DE4",
};

export default StundentRow;
