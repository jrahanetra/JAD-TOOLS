import React, { forwardRef, useState } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import {
  Button,
  CardActionArea,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Slide,
} from "@mui/material";
import { TransitionProps } from "@mui/material/transitions";
import DayEDT from "../../../models/DayEDT";
import Course from "../../../models/Course";
import Etudiant from "../../../models/Etudiant";
import fecthStudent from "../../../fecthAPI/FetchStudents";

type Props = {
  dayCourses: DayEDT[];
};

// Utilisation d'une fonction fléchée pour définir Transition
const Transition = forwardRef<
  unknown,
  TransitionProps & { children: React.ReactElement<any, any> }
>(({ children, ...rest }, ref) => (
  <Slide direction="up" ref={ref} {...rest}>
    {children}
  </Slide>
));

Transition.displayName = "Transition";

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
  const [course, setCourse] = useState<Course>();
  const [etudiantList, setEtudiant] = useState<Etudiant[]>([]);

  fecthStudent(etudiantList, setEtudiant);
  // Pour le dialog
  const [open, setOpen] = useState(false);
  const handleClickOpen = (courseParam: Course) => {
    setCourse(courseParam);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  return (
    <div className="containerColumn1">
      {dayCourses?.map((courseItem) => {
        const topPosition = calculateTopPosition(courseItem.courseBeginTime);
        const height = calculateHeight(
          courseItem.courseBeginTime,
          courseItem.courseEndTime
        );
        return (
          <div
            key={courseItem.courseId}
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
                onClick={() => handleClickOpen(courseItem)}
              >
                <CardContent>
                  <h4 className="nameSubject">
                    {courseItem.subjectDto.subjectName}
                  </h4>
                  <p className="profName">
                    Mr {courseItem.subjectDto.teacherDto.firstname}
                  </p>
                  <p className="profName">
                    {courseItem.courseBeginTime} - {courseItem.courseEndTime}
                  </p>
                </CardContent>
              </CardActionArea>
              <Dialog
                open={open}
                TransitionComponent={Transition}
                keepMounted
                onClose={handleClose}
                aria-describedby="alert-dialog-slide-description"
              >
                <DialogTitle>{course?.subjectDto.subjectName}</DialogTitle>
                <DialogContent>
                  <DialogContentText id="alert-dialog-slide-description">
                    Let Google help apps determine location. This means sending
                    anonymous location data to Google, even when no apps are
                    running.
                  </DialogContentText>
                </DialogContent>
                <DialogActions>
                  <Button onClick={handleClose}>Close</Button>
                </DialogActions>
              </Dialog>
            </Card>
          </div>
        );
      })}
    </div>
  );
}
export default JourDataEDT;
