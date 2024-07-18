import React, {useState} from "react";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';

function SelectInputComponent(){
    const [sex, setSex] = useState<string>();
    const handleChangeAge = (event: SelectChangeEvent) => {
        setSex(event.target.value);
    };
    const valuePossible = ['Masculin','Féminin']
    return(
        <div className="container-textField">
            <InputLabel htmlFor="demo-simple-select-filled-label" sx={{margin:'5px 0 0 0',color:'black'}}>SEX</InputLabel>
            <FormControl variant="filled">
                <InputLabel htmlFor="demo-simple-select-filled-label">SEX</InputLabel>
                <Select
                    labelId="demo-simple-select-filled-label"
                    id="demo-simple-select-filled"
                    value={sex}
                    onChange={handleChangeAge}
                    sx={
                        {
                            width: '80%',
                        }
                    }
                >
                    {valuePossible.map( value => (
                        <MenuItem key="" value={value}>{value}</MenuItem>
                    ))}
                </Select>
            </FormControl>
        </div>
    )
}

export default SelectInputComponent;