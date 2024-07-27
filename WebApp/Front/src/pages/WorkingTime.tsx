import React from "react";
import InputSearch from "../components/common/inputSearch";
import TableEDT from "../components/WorkingTime/Table/TableEdt";

function WorkingTime() {
    return (
        <div className="container-attend">
            <div className="container-title">
                <h1>Schedule</h1>
            </div>
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>
                <TableEDT />
            </div>
        </div>
    )
}
export default WorkingTime;