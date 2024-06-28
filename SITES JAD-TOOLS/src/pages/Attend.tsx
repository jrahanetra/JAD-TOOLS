import React from "react";
import TableData from "../components/Attendance/Table/AttendTable";
import FilterComponent from "../components/Attendance/Filtering/filter";

function Attend(){
    return(
        <div className="container-attend">
            <h1>Attendance List</h1>
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>
                <div className="div-container-tabFilter">
                    <FilterComponent />
                    <TableData />
                </div>
            </div>
        </div>
    )
}
export default Attend;