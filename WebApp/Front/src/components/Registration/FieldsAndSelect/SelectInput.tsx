import React from "react";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';

type Props = {
    value: string,
    handleChange: (event: SelectChangeEvent<string>) => void,
    onKeyPress: (event: React.KeyboardEvent<HTMLElement>) => void,
    inputRef: React.RefObject<HTMLDivElement>,
    nextInputRef: React.RefObject<HTMLInputElement>,
}
function SelectInputComponent({
    value,
    handleChange,
    onKeyPress,
    inputRef,
    nextInputRef
} : Props){
    const handleKeyDown = (event: React.KeyboardEvent<HTMLElement>) => {
        if (event.key === 'Enter' || event.key === 'ArrowRight') {
            event.preventDefault();
            nextInputRef.current?.focus(); // Focus sur l'input suivant
        }
        onKeyPress(event);
    };

    const valuePossible = ['Masculin','Féminin']
    return(
        <div className="container-textField">
            <InputLabel htmlFor="demo-simple-select-filled-label" sx={{margin:'5px 0 5px 0',color:'black'}}>SEX</InputLabel>
            <FormControl>
                <Select
                    value={value}
                    onChange={handleChange}
                    onKeyDown={handleKeyDown} // Utilisez handleKeyDown ici
                    inputRef={inputRef}
                    displayEmpty
                    sx={
                        { 
                            width: '70%',
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