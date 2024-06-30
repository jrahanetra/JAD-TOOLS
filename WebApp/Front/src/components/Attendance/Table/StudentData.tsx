import React from "react";
import Etudiant from "../../../models/Etudiant";

type Props = {
    etudiant: Etudiant,
}

function StundentRow({ etudiant }: Props) {
    return (
        <tr>
            <td>{etudiant.id}</td>
            <td>{etudiant.lastname}</td>
            <td>{etudiant.firstname}</td>
            <td>{etudiant.address}</td>
            <td>{etudiant.email}</td>
            <td>{etudiant.phoneNumber}</td>
            <td>nothingToShow</td>
            <td>nothingToShow</td>
        </tr>
    );
}

export default StundentRow;
