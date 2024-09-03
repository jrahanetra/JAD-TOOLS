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
  const [validValuesLevel, setValidValuesLevel] = useState<boolean>(false);
  const [allEDTGroupedByMonday, setToMyMap] = useState<Map<string, DayEDT[]>>(
    new Map()
  );
  const [level, setLevel] = useState<string>("L1");

  const [levelToFetch, setLevelToFetch] = useState<string>("1");
  const handleChangeValueLevel = () => (event: SelectChangeEvent<string>) => {
    switch (event.target.value) {
      case "L1":
        setLevel("L1");
        setLevelToFetch("1");
        break;
      case "L2":
        setLevel("L2");
        setLevelToFetch("2");
        break;
      case "L3":
        setLevel("L3");
        setLevelToFetch("3");
        break;
      default:
        setLevel("");
    }
  };

  const fetchEDTData = async (date: string) => {
    let weekEDT: DayEDT[] = [];
    const postData = {
      beginDate: date,
      levelId: levelToFetch, // NIVEAU
      majorId: "1", // PARCOURS
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
      beginDate: "26-08-2024", // NIVEAU
      endDate: "02-09-2024", // PARCOURS
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
    }
  }, [allMonday, dateSelected, levelToFetch]);
  useEffect(() => {
    const result = dateSelected !== "";
    setValidValuesDate(result);
  }, [validValuesDate]);
  useEffect(() => {
    const result = level !== "";
    setValidValuesLevel(result);
  }, [validValuesLevel]);
  const selectedDate = useRef<HTMLDivElement>(null);
  const selectedLevel = useRef<HTMLDivElement>(null);
  const handleChangeValueDate = () => (event: SelectChangeEvent<string>) => {
    setDateSelected(event.target.value);
  };
  
  return (
    <div className="container-attend">
      <div className="container-title">
        <div className="container-filter">
          <SelectFilterComponent
            nameLabel="Semaine"
            widthSelect={90}
            valuesPossible={[...allMonday, ""]} // WEEK POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={dateSelected}
            handleChange={handleChangeValueDate()}
            onKeyPress={() => {}}
            inputRef={selectedDate}
            isValid={validValuesDate}
            isWithLabel={false}
          />
          <SelectFilterComponent
            nameLabel="Level"
            widthSelect={90}
            valuesPossible={["L1", "L2"]} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={level}
            handleChange={handleChangeValueLevel()}
            onKeyPress={() => {}}
            inputRef={selectedLevel}
            isValid={validValuesLevel}
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
        <div className="div-container-EDT">
          {Array.from(sortDateMap(allEDTGroupedByMonday)).map(
            ([key, value]) => (
              <div key={key} className="container-edt">
                <h1 className="dateOfWeek">
                  {level} : Schedule of {formatDate(key)}
                </h1>
                <TableEDT edt={value} />
              </div>
            )
          )}
        </div>
      </div>
    </div>
  );
}
export default WorkingTime;
