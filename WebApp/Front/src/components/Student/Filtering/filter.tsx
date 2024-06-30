import React from "react"

function FilterComponent(){
    return(
        <div className="div-container-filter">
            <div className="div-filter-title">
                <h2 className="h2-filter">↓FILTER</h2>
            </div>
            <div className="div-filter-by">
                <div>
                    <h2 className="h2-filter-by">↓By Level</h2>
                    <h3>L1</h3>
                    <h3>L2</h3>
                </div>
                <div>
                    <h2  className="h2-filter-by">↓By Name</h2>
                    <h3>↓A</h3>
                    <h3>Z↑</h3>
                </div>
                <div>
                    <h2  className="h2-filter-by">↓By Attend</h2>
                    <h3>Yes</h3>
                    <h3>No</h3>
                </div>
            </div>
        </div>
    )
}

export default FilterComponent;