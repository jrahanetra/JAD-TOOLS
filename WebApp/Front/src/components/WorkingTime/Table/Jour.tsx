import React, { useState } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import { CardActionArea } from "@mui/material";
import { useNavigate } from "react-router-dom";
import DayEDT from "../../../models/DayEDT";

type Props = {
  dayCourses: DayEDT[];
};

const heightPerHour = 52; // Hauteur de chaque heure en pixels
const timeSlots = [
  "08:00",
  "09:00",
  "10:00",
  "11:00",
  "12:00",
  "13:00",
  "14:00",
  "15:00",
  "16:00",
];

// Fonction pour calculer la position du cours en pixels
const calculateTopPosition = (startTime: string) => {
  const [hour, minute] = startTime.split(":").map(Number);
  const hourIndex = timeSlots.indexOf(`${hour.toString().padStart(2, "0")}:00`);
  return hourIndex * heightPerHour + (minute / 60) * heightPerHour;
};

// Fonction pour calculer la hauteur du cours en pixels
const calculateHeight = (startTime: string, endTime: string) => {
  const [startHour, startMinute] = startTime.split(":").map(Number);
  const [endHour, endMinute] = endTime.split(":").map(Number);
  const durationHours = endHour - startHour + (endMinute - startMinute) / 60;
  return durationHours * heightPerHour;
};

function JourDataEDT({ dayCourses }: Props) {
  const navigate = useNavigate(); // Initialiser useNavigate

  const handleCardClick = (course: DayEDT) => {
    // Remplacer 'route-path' par la route où tu veux naviguer
    navigate(`/course/${course.courseId}`);
  };

  return (
    <div className="containerColumn1">
      {dayCourses?.map((course) => {
        const topPosition = calculateTopPosition(course.courseBeginTime);
        const height = calculateHeight(
          course.courseBeginTime,
          course.courseEndTime
        );
        return (
          <div
            key={course.courseId}
            className="container-item"
            style={{
              position: "absolute",
              top: `${topPosition}px`,
              height: `${height}px`,
            }}
          >
            <Card
              className="card-custom"
              style={{
                height: `90%`,
                width: "90%",
                transition: `background-color 0.3s ease`,
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)" /* Ombre douce */,
              }}
            >
              <CardActionArea
                style={{ width: "100%", height: "100%" }}
                onClick={() => handleCardClick(course)}
              >
                <CardContent>
                  <h4 className="nameSubject">
                    {course.subjectDto.subjectName}
                  </h4>
                  <p className="profName">
                    Mr {course.subjectDto.teacherDto.firstname}
                  </p>
                  <p className="profName">
                    {course.courseBeginTime} - {course.courseEndTime}
                  </p>
                </CardContent>
              </CardActionArea>
            </Card>
          </div>
        );
      })}
    </div>
  );
}
export default JourDataEDT;
