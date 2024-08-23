import React, { useEffect, useRef, useState } from "react";
import { toast } from "react-toastify";
import { SelectChangeEvent } from "@mui/material/Select";
import TableEDT from "../components/WorkingTime/Table/TableEdt";
import DayEDT from "../models/DayEDT";
import formatDate from "../utils/FormateDate";
import sortDateMap from "../utils/SortList";
import SelectFilterComponent from "../components/common/selectInputForFilter";

function WorkingTime() {
  const [allMonday, setAllMonday] = useState<string[]>([]);
  const [dateSelected, setDateSelected] = useState<string>("");
  const [validValuesDate, setValidValuesDate] = useState<boolean>(false);
  const [allEDTGroupedByMonday, setToMyMap] = useState<Map<string, DayEDT[]>>(
    new Map()
  );

  const fetchEDTData = async (date: string) => {
    let weekEDT: DayEDT[] = [];
    const postData = {
      beginDate: date,
      levelId: "1", // NIVEAU
      majorId: "2", // PARCOURS
    };
    const params = new URLSearchParams(postData).toString();
    try {
      const response = await fetch(`http://localhost:8080/courses/s?${params}`);
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      weekEDT = data;
      setToMyMap((prevMap) => new Map(prevMap).set(date, weekEDT));
    } catch (error) {
      toast.error(`Error: ${error}`);
    }
  };

  const fetchDate = async () => {
    const postLundi = {
      beginDate: "19-08-2024", // NIVEAU
      endDate: "26-08-2024", // PARCOURS
    };
    const params1 = new URLSearchParams(postLundi).toString();
    try {
      const response = await fetch(`http://localhost:8080/dates?${params1}`);
      if (!response.ok) {
        throw new Error(`Network response was not ok`);
      }
      const data = await response.json();
      setAllMonday(data);
    } catch (error) {
      toast.error(`Error: ${error}`);
    }
  };
  // Fetch les dates seulement au début
  useEffect(() => {
    fetchDate();
  }, []);
  // Fetch les EDT quand les dates sont disponibles ou quand dateSelected change
  useEffect(() => {
    if (dateSelected === "") {
      allMonday?.forEach((monday) => {
        fetchEDTData(monday);
      });
    } else {
      setToMyMap(new Map());
      fetchEDTData(dateSelected);
      console.log("hello");
    }
  }, [allMonday, dateSelected]);
  useEffect(() => {
    const result = dateSelected !== "";
    setValidValuesDate(result);
  }, [validValuesDate]);
  const selectedDate = useRef<HTMLDivElement>(null);
  const handleChangeValue = () => (event: SelectChangeEvent<string>) => {
    setDateSelected(event.target.value);
  };
  console.log(allEDTGroupedByMonday);
  console.log(dateSelected);
  return (
    <div className="container-attend">
      <div className="container-title">
        <div className="container-filter">
          <SelectFilterComponent
            nameLabel="Semaine"
            widthSelect={60}
            valuesPossible={[...allMonday, ""]} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={dateSelected}
            handleChange={handleChangeValue()}
            onKeyPress={() => {}}
            inputRef={selectedDate}
            isValid={validValuesDate}
            isWithLabel={false}
          />
        </div>
      </div>
      <div className="container-list">
        <div className="div-refresh">
          <a href="/workingTime" className="a-refresh">
            Refresh
          </a>
        </div>
        {Array.from(sortDateMap(allEDTGroupedByMonday)).map(([key, value]) => (
          <div key={key} className="container-edt">
            <h1 className="dateOfWeek">Schedule of {formatDate(key)}</h1>
            <TableEDT edt={value} />
          </div>
        ))}
      </div>
    </div>
  );
}
export default WorkingTime;
