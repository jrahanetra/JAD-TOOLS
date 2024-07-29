import React from "react";
import { DateField } from '@mui/x-date-pickers/DateField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { InputLabel } from "@mui/material";
import { Dayjs } from "dayjs";

type Props={
    value:Dayjs | null,
    onChange:(newValue:Dayjs | null) => void,
    onKeyPress: (event: React.KeyboardEvent<HTMLDivElement>) => void,
    inputRef: React.RefObject<HTMLDivElement>,
}
function DateFieldComponent({
    value, 
    onChange,
    onKeyPress,
    inputRef
}: Props){
    return(
        <div className="container-textField">
            <InputLabel htmlFor="birthday" sx={{margin:'5px 0 5px 0',color:'black'}}>
                BIRTHDAY
            </InputLabel>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DateField
                    id="birthday"
                    defaultValue={value}
                    onChange={onChange}
                    format="LL"
                    onKeyDown={onKeyPress}
                    inputRef={inputRef}
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
