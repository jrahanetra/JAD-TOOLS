import React from "react";

function TableEDT(){
    return(
        <div className="container-tableEDT">
                <div className="containerColumn column1">
                    <div className="headerEdt time">
                        <h1 className="h1-edtItem">Time</h1>
                    </div>
                    <div className="containerColumn1">
                        <div>
                            <h1 className="h1-edtItem item">08h-09h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">09h-10h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">10h-11h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">11h-12h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">12h-13h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">13h-14h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">14h-15h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">15h-16h</h1>
                        </div>
                        <div>
                            <h1 className="h1-edtItem item">16h-17h</h1>
                        </div>
                    </div>
                </div>
                <div className="containerColumn column2">
                    <div className="headerEdt"><h1 className="h1-edtItem">Monday</h1></div>
                </div>
                <div className="containerColumn column3">
                    <div className="headerEdt"><h1 className="h1-edtItem">Tuesday</h1></div>  
                </div>
                <div className="containerColumn column4">
                    <div className="headerEdt"><h1 className="h1-edtItem">Wednesday</h1></div>
                </div>
                <div className="containerColumn column5">
                    <div className="headerEdt"><h1 className="h1-edtItem">Thursday</h1></div>
                </div>
                <div className="containerColumn column6">
                    <div className="headerEdt"><h1 className="h1-edtItem">Friday</h1></div>
                </div>
                <div className="containerColumn column7">
                    <div className="headerEdt"><h1 className="h1-edtItem">Saturday</h1></div>
                </div>
                <div className="containerColumn column8">
                    <div className="headerEdt sunday"><h1 className="h1-edtItem">Sunday</h1></div>
                </div>
        </div>
    )
}
export default TableEDT;