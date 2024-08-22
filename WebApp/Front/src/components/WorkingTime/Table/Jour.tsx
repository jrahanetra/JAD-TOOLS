import React, { useState } from "react";
import DayEDT from "../../../models/DayEDT";

type Props = {
    dayCourses : DayEDT[]
}

const heightPerHour = 50; // Hauteur de chaque heure en pixels
const timeSlots = [
  "08:00", "09:00", "10:00", "11:00", "12:00",
  "13:00", "14:00", "15:00", "16:00", "17:00"
];

// Fonction pour calculer la position du cours en pixels
const calculateTopPosition = (startTime: string) => {
  const [hour, minute] = startTime.split(':').map(Number);
  const hourIndex = timeSlots.indexOf(`${hour.toString().padStart(2, '0')}:00`);
  return hourIndex * heightPerHour + (minute / 60) * heightPerHour;
};

// Fonction pour calculer la hauteur du cours en pixels
const calculateHeight = (startTime : string, endTime : string) => {
  const [startHour, startMinute] = startTime.split(':').map(Number);
  const [endHour, endMinute] = endTime.split(':').map(Number);
  const durationHours = endHour - startHour + (endMinute - startMinute) / 60;
  return durationHours * heightPerHour;
};

function JourDataEDT({ dayCourses } : Props){

    return (
        <div className="containerColumn1">
            {dayCourses?.map((course) => {
                const topPosition = calculateTopPosition(course.courseBeginTime);
                console.log(topPosition)
                const height = calculateHeight(course.courseBeginTime, course.courseEndTime);
                console.log(height)
                return (
                    <div 
                        key={course.courseId} 
                        className="container-item"
                        style={{                
                            position:"absolute",         
                            top: `${topPosition}px`, 
                            height: `${height}px`, 
                        }}
                    >
                        <div className="divContainer">
                            <h4 className="nameSubject">{course.subjectDto.subjectName}</h4>
                            <p className="profName">Mr {course.subjectDto.teacherDto.firstname}</p>
                        </div>
                    </div>
                );
            })}
        
        </div>
    )
}
export default JourDataEDT