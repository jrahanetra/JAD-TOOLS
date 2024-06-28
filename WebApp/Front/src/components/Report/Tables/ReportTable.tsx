import React from "react";
import ReportRow from "./ReportData";

function ReportTable(){
    return(
        <div className="table-container">
            <table>
                <thead>
                    <tr>
                        <td className="td-head">Name</td>
                        <td className="td-head">Year</td>
                        <td className="td-head">Semester</td>
                        <td className="td-head">Attendance Count</td>
                        <td className="td-head">Avg</td>
                    </tr>
                </thead>
                <ReportRow />
            </table>
        </div>
    )
}

export default ReportTable;