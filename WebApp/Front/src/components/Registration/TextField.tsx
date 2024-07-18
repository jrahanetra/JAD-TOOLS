import React, { useState } from "react";
import TextField from '@mui/material/TextField';

type Props = {
  placeholderTextField:string,
  id:string,
  widthTextField:number
}
function TextFieldComponent({placeholderTextField, id, widthTextField}: Props){
    const [value, setValue] = useState('');

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      setValue(event.target.value);
    };
  
    return(
        <div className="container-textField">
            <label htmlFor={id}>{placeholderTextField}</label>
            <TextField
                id= {id}
                label= {placeholderTextField}
                placeholder= {placeholderTextField}
                multiline
                variant="filled"
                value={value}
                onChange={handleChange}
                sx={
                    {
                      margin: '1% 0 0px 0',
                      width: `${widthTextField}%`, // Change the width as needed
                      '& .MuiInputBase-root': {
                        fontSize: '1.2rem', // Change the font size of the text
                      },
                      '& .MuiInputLabel-root': {
                        fontSize: '1.2rem', // Change the font size of the label
                      },
                      backgroundColor: '#FFFFFF'
                    }
                }
            />
        </div>
    )
}
export default TextFieldComponent;