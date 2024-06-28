import React from "react";

function InfoTable(){
    return(
        <div className="container-tab">
            <table>
                <thead>
                    <tr>
                        <td className="td-head">Course</td>
                        <td className="td-head">Semester</td>
                        <td className="td-head">Date</td>
                        <td className="td-head">Hour</td>
                        <td className="td-head">Excuse</td>
                    </tr>
                </thead>
            </table>
        </div>
    )
}
export default InfoTable;