import React from "react";
import { DateField } from '@mui/x-date-pickers/DateField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { InputLabel } from "@mui/material";
import dayjs, { Dayjs } from "dayjs";

type Props={
    value:Dayjs | null,
    onChange:(newValue:Dayjs | null) => void
}
function DateFieldComponent({value, onChange}: Props){
    return(
        <div className="container-textField">
            <InputLabel htmlFor="birthday" sx={{margin:'5px 0 5px 0',color:'black'}}>BIRTHDAY</InputLabel>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DateField
                    id="birthday"
                    defaultValue={value}
                    onChange={onChange}
                    format="LL"
                    sx={
                        { 
                            width: '70%',
                            borderBottom: '1.90px solid #8b8b8b', 
                        }
                    }
                />
            </LocalizationProvider>
        </div>
    )
}
export default DateFieldComponent
