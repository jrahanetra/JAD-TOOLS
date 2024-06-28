import React, { useState } from "react";
import Etudiant from "../../../models/Etudiant";

type Props = {
    etudiant: Etudiant,
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
        onStudentClick(etudiant.studentId);
    };

    return (
        <tr onClick={handleClick} onMouseEnter={showBorder} onMouseLeave={hideBorder} style={{borderBottom: color}}>
            <td>{etudiant.studentId}</td>
            <td>{etudiant.firstname}</td>
            <td>{etudiant.lastname}</td>
            <td>{etudiant.address}</td>
        </tr>
    );
}
StundentRow.defaultProps = {
    borderColor: "4px solid #257DE4",
};

export default StundentRow;
