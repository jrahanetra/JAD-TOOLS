import React from "react";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import { faCheck } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Select, { SelectChangeEvent } from '@mui/material/Select';

type Props = {
    nameLabel: string,
    widthSelect: number,
    valuesPossible: string[],
    value: string,
    handleChange: (event: SelectChangeEvent<string>) => void,
    onKeyPress: (event: React.KeyboardEvent<HTMLInputElement>) => void,
    inputRef: React.RefObject<HTMLDivElement>,
    isValid: boolean
}
function SelectInputComponent({
    nameLabel,
    widthSelect,
    valuesPossible,
    value,
    handleChange,
    onKeyPress,
    inputRef,
    isValid
} : Props){
    const valuePossible = valuesPossible

    return(
        <div className="container-textField">
            <InputLabel htmlFor="demo-simple-select-filled-label" sx={{margin:'5px 0 5px 0',color:'black'}}>
                {nameLabel} : 
                <span className={ isValid? "valid" : "hide"}>
                    <FontAwesomeIcon 
                        icon={faCheck}
                        style={
                            {
                                margin: "0 0 0 10px",
                                color: "#1fd61b",
                                fontSize: "1.5rem"
                            }
                        }/>
                </span>
            </InputLabel>
            <FormControl>
                <Select
                    value={value}
                    onChange={handleChange}
                    inputRef={inputRef}
                    onKeyDownCapture={onKeyPress}
                    displayEmpty
                    sx={
                        { 
                            width: `${widthSelect}%`,
                            borderBottom: '1.90px solid #8b8b8b', 
                        }
                    }
                >
                    {valuePossible.map( sexValue => (
                        <MenuItem key={sexValue} value={sexValue}>{sexValue}</MenuItem>
                    ))}
                </Select>
            </FormControl>
        </div>
    )
}

export default SelectInputComponent;