import React from "react";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import { faCheck } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Select, { SelectChangeEvent } from "@mui/material/Select";

type Props = {
  nameLabel: string;
  widthSelect: number;
  valuesPossible: string[];
  value: string;
  handleChange: (event: SelectChangeEvent<string>) => void;
  onKeyPress: (event: React.KeyboardEvent<HTMLInputElement>) => void;
  inputRef: React.RefObject<HTMLDivElement>;
  isValid: boolean;
  isWithLabel: boolean;
};
function SelectFilterComponent({
  nameLabel,
  widthSelect,
  valuesPossible,
  value,
  handleChange,
  onKeyPress,
  inputRef,
  isValid,
  isWithLabel,
}: Props) {
  return (
    <div className="container-textField">
      {isWithLabel && (
        <InputLabel
          htmlFor="demo-simple-select-filled-label"
          sx={{ margin: "5px 0 5px 0", color: "black" }}
        >
          {nameLabel} :
          <span className={isValid ? "valid" : "hide"}>
            <FontAwesomeIcon
              icon={faCheck}
              style={{
                margin: "0 0 0 10px",
                color: "#1fd61b",
                fontSize: "1.5rem",
              }}
            />
          </span>
        </InputLabel>
      )}
      <FormControl
        variant="standard"
        sx={{
          m: 1,
        }}
      >
        <InputLabel id="demo-simple-select-label" sx={{
          color: "white",
        }}>
          {nameLabel}
        </InputLabel>
        <Select
          labelId="demo-simple-select-standard-label"
          id="demo-simple-select-standard"
          value={value}
          onChange={handleChange}
          inputRef={inputRef}
          onKeyDownCapture={onKeyPress}
          label={nameLabel}
          sx={{
            width: `${widthSelect}%`,
            color: "white",
            borderBottom: "2px solid white",
          }}
        >
          {valuesPossible.map((sexValue) => (
            <MenuItem key={sexValue} value={sexValue}>
              {sexValue}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
}

export default SelectFilterComponent;
