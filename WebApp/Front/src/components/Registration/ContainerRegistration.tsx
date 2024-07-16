import React from "react";
import TextFieldComponent from "./TextField";

function ContainerRegistration(){
    return(
        <div className="container-attend">
            <div className="container-title">
                <h1>Registration Student</h1>
            </div>
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>

                <TextFieldComponent />
                <TextFieldComponent />
                <TextFieldComponent />
                <TextFieldComponent />
            </div>
        </div>
    )
}
export default ContainerRegistration;