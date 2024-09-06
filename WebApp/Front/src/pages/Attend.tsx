import React, { useEffect, useRef, useState } from "react";
import { SelectChangeEvent } from "@mui/material";
import TableData from "../components/Attendance/Table/AttendTable";
import InputSearch from "../components/common/inputSearch";
import SelectFilterComponent from "../components/common/selectInputForFilter";

type FormValuesFilter = {
  byDate: string;
  byName: string;
  byAttend: string;
};

type ValidationFormValuesFilters = {
  byDate: boolean;
  byName: boolean;
  byAttend: boolean;
};
function Attend() {
  const [valueSearchInput, setValueSearchInput] = useState<string>("");

  const [valuesFilter, setValuesFilter] = useState<FormValuesFilter>({
    byDate: "",
    byName: "",
    byAttend: "",
  });

  const handleChangeValue =
    (forSelectInput: string) => (event: SelectChangeEvent<string>) => {
      setValuesFilter({
        ...valuesFilter,
        [forSelectInput]: event?.target.value,
      });
    };
  const handleChangeValueSearchInput =
    () => (event: React.ChangeEvent<HTMLInputElement>) => {
      setValueSearchInput(event?.target.value);
    };
  const [validValuesFilter, setValidValuesFilter] =
    useState<ValidationFormValuesFilters>({
      byDate: false,
      byName: false,
      byAttend: false,
    });

  const selectFilterDate = useRef<HTMLDivElement>(null);
  const selectFilterName = useRef<HTMLDivElement>(null);
  const selectFilterAttend = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const result = valuesFilter.byDate !== "";
    setValidValuesFilter({
      ...validValuesFilter,
      byDate: result,
    });
  }, [valuesFilter.byDate]);
  useEffect(() => {
    const result = valuesFilter.byName !== "";
    setValidValuesFilter({
      ...validValuesFilter,
      byName: result,
    });
  }, [valuesFilter.byName]);
  useEffect(() => {
    const result = valuesFilter.byAttend !== "";
    setValidValuesFilter({
      ...validValuesFilter,
      byAttend: result,
    });
  }, [valuesFilter.byAttend]);

  return (
    <div className="container-attend">
      <div className="container-title">
        <div className="container-filter">
          <SelectFilterComponent
            nameLabel="↓↑Date"
            widthSelect={90}
            valuesPossible={["↓Recent date", "↑Old date"]} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={valuesFilter.byDate}
            handleChange={handleChangeValue("byDate")}
            onKeyPress={() => {}}
            inputRef={selectFilterDate}
            isValid={validValuesFilter.byDate}
            isWithLabel={false}
          />
          <SelectFilterComponent
            nameLabel="↓↑Name"
            widthSelect={90}
            valuesPossible={["↓By A", "↑By Z"]} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={valuesFilter.byName}
            handleChange={handleChangeValue("byName")}
            onKeyPress={() => {}}
            inputRef={selectFilterName}
            isValid={validValuesFilter.byName}
            isWithLabel={false}
          />
          <SelectFilterComponent
            nameLabel="Attendance"
            widthSelect={90}
            valuesPossible={["Present", "Absent"]} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={valuesFilter.byAttend}
            handleChange={handleChangeValue("byAttend")}
            onKeyPress={() => {}}
            inputRef={selectFilterAttend}
            isValid={validValuesFilter.byAttend}
            isWithLabel={false}
          />
        </div>
        <InputSearch
          searchValue={valueSearchInput}
          handleChange={handleChangeValueSearchInput()}
        />
      </div>
      <div className="container-list">
        <div className="div-refresh">
          <a href="/attendance" className="a-refresh">
            Refresh
          </a>
        </div>
        <div className="div-container-Student">
          <TableData
            keyWordToFilter={valueSearchInput}
            filterByDate={valuesFilter.byDate}
            filterByName={valuesFilter.byName}
            filterByAttend={valuesFilter.byAttend}
          />
        </div>
      </div>
    </div>
  );
}
export default Attend;
