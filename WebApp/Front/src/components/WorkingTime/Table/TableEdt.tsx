import React from "react";
import JourDataEDT from "./Jour";
import DayEDT from "../../../models/DayEDT";

type Props = {
  edt: DayEDT[] | undefined;
};

// Fonction pour obtenir le jour de la semaine à partir de la date
function getDayOfWeek(dateStr: string): string {
  const [day, month, year] = dateStr.split("-").map(Number);
  const date = new Date(year, month - 1, day);
  const days = [
    "Dimanche",
    "Lundi",
    "Mardi",
    "Mercredi",
    "Jeudi",
    "Vendredi",
    "Samedi",
  ];
  return days[date.getDay()];
}

type DayOfWeek =
  | "Lundi"
  | "Mardi"
  | "Mercredi"
  | "Jeudi"
  | "Vendredi"
  | "Samedi";

// Regrouper les cours par jour de la semaine
function groupByDayOfWeek(data: DayEDT[] | undefined): {
  [key in DayOfWeek]: DayEDT[];
} {
  // Initialiser l'objet avec tous les jours de la semaine
  const initialGroupedByDay: { [key in DayOfWeek]: DayEDT[] } = {
    "Lundi": [],
    "Mardi": [],
    "Mercredi": [],
    "Jeudi": [],
    "Vendredi": [],
    "Samedi": [],
  };

  if (!data) {
    return initialGroupedByDay;
  }

  return data.reduce((acc, course) => {
    const dayOfWeek = getDayOfWeek(course.courseDate) as DayOfWeek;
    acc[dayOfWeek].push(course);
    return acc;
  }, initialGroupedByDay);
}

function TableEDT({ edt }: Props) {
  const groupedCourses = groupByDayOfWeek(edt);
  const daysOfWeek: { key: DayOfWeek; label: string }[] = [
    { key: "Lundi", label: "Monday" },
    { key: "Mardi", label: "Tuesday" },
    { key: "Mercredi", label: "Wednesday" },
    { key: "Jeudi", label: "Thursday" },
    { key: "Vendredi", label: "Friday" },
    { key: "Samedi", label: "Saturday" },
  ];
  const listTime = [
            "08h-09h",
            "09h-10h",
            "10h-11h",
            "11h-12h",
            "12h-13h",
            "13h-14h",
            "14h-15h",
            "15h-16h",
            "16h-17h",
          ]

  return (
    <div className="container-tableEDT">
      <div className="containerColumn columnTime">
        <div className="headerEdt time">
          <h1 className="h1-edtItem">Time</h1>
        </div>
        <div className="containerColumn1">
          {listTime.map((timeSlot, index) => (
            <div key={timeSlot} className={`container-item item${index}`}>
              <h1 className="h1-edtItem">{timeSlot}</h1>
            </div>
          ))}
        </div>
      </div>
      {daysOfWeek.map(({ key, label }) => (
        <div key={key} className={`containerColumn column${key}`}>
          <div className="headerEdt">
            <h1 className="h1-edtItem">{label}</h1>
          </div>
          <JourDataEDT dayCourses={groupedCourses[key]} />
        </div>
      ))}
    </div>
  );
}
export default TableEDT;
