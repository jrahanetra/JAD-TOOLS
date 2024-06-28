import React from "react";
import ShowAllDash from "../components/Report/DashReport/AllDash";
import AttendanceGlobal from "../components/Report/ChartsReport/AttendanceGlobal";
import AttendancePerSemester from "../components/Report/ChartsReport/AttendancePerSemester";
import AttendancePerSex from "../components/Report/ChartsReport/AttendancePerSex";
import ReportTable from "../components/Report/Tables/ReportTable";

function Graph(){
    return(
        <div className="container-graph">
            <AttendancePerSex />
            <AttendanceGlobal />
            <AttendancePerSemester />
        </div>
    )
}

function DisplayReport(){
    return (
        <div className="container-attend">
            <h1>Report</h1>
            <div className="container-report">
                <ShowAllDash />
                <Graph />
                <ReportTable />
            </div>
        </div>
    )
}

export default DisplayReport;