import React, { Component } from "react";

type Props = {
    name : string;
    number : number;
    color : string;
    vector : React.ComponentType,
}

function BoxDash({name, number, color, vector: Vector} : Props){
    return(
        <div className="div-dash-box"  style={{borderBottom : `10px solid ${color}`}}>
            <div className="div-dash-name">
                <h1 className="h1-dash-name" style={{ color: `${color}`}}>{name}</h1>
            </div>  
            <div className="div-dash-vector">
                <div className="div-svg-vector" style={{ backgroundColor: `${color}`}}>
                    <Vector />
                </div>
            </div>
            <div className="div-dash-name">
                <h1 className="h1-dash-number">{number}</h1>
            </div>
        </div>
    )
}
export default BoxDash;