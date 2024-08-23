import React, { useEffect, useRef, useState } from "react";
import { SelectChangeEvent } from "@mui/material";
import TableData from "../components/Student/Table/StudentTable";
import InputSearch from "../components/common/inputSearch";
import SelectFilterComponent from "../components/common/selectInputForFilter";

type ValuesFilter = {
  level: string;
  name: string;
  attend: string;
};
type ValidationValuesFilter = {
  validLevel: boolean;
  validName: boolean;
  validAttend: boolean;
};
function Student() {
  const [searchFilterValue, setSearchFilterValue] = useState<string>("");

  const [valuesFilter, setValuesFilter] = useState<ValuesFilter>({
    level: "",
    name: "",
    attend: "",
  });
  const [validValuesFilter, setValidValuesFilter] =
    useState<ValidationValuesFilter>({
      validLevel: false,
      validName: false,
      validAttend: false,
    });

  const handleChangeValue =
    (selectFilter: string) => (event: SelectChangeEvent<string>) => {
      setValuesFilter({
        ...valuesFilter,
        [selectFilter]: event.target.value,
      });
    };

  const handleChangeValueSearchInput =
    () => (event: React.ChangeEvent<HTMLInputElement>) => {
      setSearchFilterValue(event.target.value);
    };

  const fieldLevel = useRef<HTMLDivElement>(null);
  const fieldName = useRef<HTMLDivElement>(null);
  const fieldAttend = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const result = valuesFilter.attend !== "";
    setValidValuesFilter({
      ...validValuesFilter,
      validAttend: result,
    });
  }, [valuesFilter.attend]);
  useEffect(() => {
    const result = valuesFilter.level !== "";
    setValidValuesFilter({
      ...validValuesFilter,
      validLevel: result,
    });
  }, [valuesFilter.attend]);
  useEffect(() => {
    const result = valuesFilter.name !== "";
    setValidValuesFilter({
      ...validValuesFilter,
      validName: result,
    });
  }, [valuesFilter.attend]);
  return (
    <div className="container-attend">
      <div className="container-title">
        <div className="container-filter">
          <SelectFilterComponent
            nameLabel="Level"
            widthSelect={90}
            valuesPossible={["L2", "L1"]} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={valuesFilter.level}
            handleChange={handleChangeValue("level")}
            onKeyPress={() => {}}
            inputRef={fieldLevel}
            isValid={validValuesFilter.validLevel}
            isWithLabel={false}
          />
          <SelectFilterComponent
            nameLabel="Name"
            widthSelect={90}
            valuesPossible={["↓A", "↑Z"]} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
            value={valuesFilter.name}
            handleChange={handleChangeValue("name")}
            onKeyPress={() => {}}
            inputRef={fieldName}
            isValid={validValuesFilter.validName}
            isWithLabel={false}
          />
        </div>
        <InputSearch
          searchValue={searchFilterValue}
          handleChange={handleChangeValueSearchInput()}
        />
      </div>
      <div className="container-list">
        <div className="div-refresh">
          <a href="/student" className="a-refresh">
            Refresh
          </a>
        </div>
        <div className="div-container-tabFilter">
          <TableData
            filterByLevel={valuesFilter.level}
            filterByName={valuesFilter.name}
            keyWordToFilter={searchFilterValue}
          />
        </div>
      </div>
    </div>
  );
}
export default Student;
