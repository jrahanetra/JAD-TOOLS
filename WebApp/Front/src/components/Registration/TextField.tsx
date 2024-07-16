import React, { useState } from "react";
import TextField from '@mui/material/TextField';

function TextFieldComponent(){
    const [value, setValue] = useState('');

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      setValue(event.target.value);
    };
  
    return(
        <div>
            <TextField 
                id="standard-basic" 
                label="Standard" 
                variant="standard" 
                value={value}
                onChange={handleChange}
            />
        </div>
    )
}
export default TextFieldComponent;