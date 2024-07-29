import React from "react";
import ShowLogo from "../../assets/logoForHome";

function Header(){
    return (
        <div>
            <header className="main-header">
                <div className="profile-container">
                    <img src={`${process.env.PUBLIC_URL}../pictures/jason.jpg`} alt="" className="profile-picture" />
                </div>
                <nav className="nav">
                    <ul>
                        <li><a href="/report">Report</a></li>
                        <li><a href="/workingTime">Schedule</a></li>
                        <li><a href="/attendance">Attendance</a></li>
                        <li><a href="/student">Students</a></li>
                        <li><a href="/registration">Registration</a></li>
                    </ul>
                </nav>
                <ShowLogo />
            </header>
        </div>
    )
}

export default Header;