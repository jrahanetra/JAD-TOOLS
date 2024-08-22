import React from "react";
import JourDataEDT from "./Jour";
import DayEDT from "../../../models/DayEDT";

type Props = {
    edt : DayEDT[] | undefined;
}

// Fonction pour obtenir le jour de la semaine à partir de la date
function getDayOfWeek(dateStr: string): string {
  const [day, month, year] = dateStr.split('-').map(Number);
  const date = new Date(year, month - 1, day);
  const days = ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'];
  return days[date.getDay()];
}

// Regrouper les cours par jour de la semaine
function groupByDayOfWeek(data: DayEDT[] | undefined): { [key: string]: DayEDT[] } {
  if (!data) {
    return {};
  }

  return data.reduce((acc, course) => {
    const dayOfWeek = getDayOfWeek(course.courseDate);
    if (!acc[dayOfWeek]) {
      acc[dayOfWeek] = [];
    }
    acc[dayOfWeek].push(course);
    return acc;
  }, {} as { [key: string]: DayEDT[] });
}

function TableEDT({ edt } : Props){
    const groupedCourses = groupByDayOfWeek(edt);
    console.log(groupedCourses);

    return(
        <div className="container-tableEDT">
            <div className="containerColumn column1">
                <div className="headerEdt time">
                    <h1 className="h1-edtItem">Time</h1>
                </div>
                <div className="containerColumn1">
                    <div className="container-item">
                        <h1 className="h1-edtItem item">08h-09h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">09h-10h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">10h-11h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">11h-12h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">12h-13h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">13h-14h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">14h-15h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">15h-16h</h1>
                    </div>
                    <div className="container-item">
                        <h1 className="h1-edtItem item">16h-17h</h1>
                    </div>
                </div>
            </div>
            <div className="containerColumn column2">
                <div className="headerEdt">
                    <h1 className="h1-edtItem">Monday</h1>
                </div>
                <JourDataEDT dayCourses={groupedCourses.Lundi}/>
            </div>
            <div className="containerColumn column3">
                <div className="headerEdt">
                    <h1 className="h1-edtItem">Tuesday</h1>
                </div>  
                <JourDataEDT dayCourses={groupedCourses.Mardi}/>
            </div>
            <div className="containerColumn column4">
                <div className="headerEdt">
                    <h1 className="h1-edtItem">Wednesday</h1>
                </div>
                <JourDataEDT dayCourses={groupedCourses.Mercredi}/>
            </div>
            <div className="containerColumn column5">
                <div className="headerEdt">
                    <h1 className="h1-edtItem">Thursday</h1>
                </div>
                <JourDataEDT dayCourses={groupedCourses.Jeudi}/>
            </div>
            <div className="containerColumn column6">
                <div className="headerEdt">
                    <h1 className="h1-edtItem">Friday</h1>
                </div>
                <JourDataEDT dayCourses={groupedCourses.Vendredi}/>
            </div>
            <div className="containerColumn column7">
                <div className="headerEdt">
                    <h1 className="h1-edtItem">Saturday</h1>
                </div>
            </div>
            <div className="containerColumn column8">
                <div className="headerEdt sunday">
                    <h1 className="h1-edtItem">Sunday</h1>
                </div>
            </div>
        </div>
    )
}
export default TableEDT;