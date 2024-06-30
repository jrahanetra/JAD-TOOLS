import React from "react";
import TableData from "../components/Student/Table/StudentTable";
import FilterComponent from "../components/Student/Filtering/filter";

function Student(){
    return(
        <div className="container-attend">
            <h1>Student</h1>
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>
                <div className="div-container-tabFilter">
                    <TableData />
                    <FilterComponent />
                </div>
            </div>
        </div>
    )
}
export default Student;