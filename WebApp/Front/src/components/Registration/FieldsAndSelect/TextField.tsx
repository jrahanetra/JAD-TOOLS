import React from "react";
import TextField from '@mui/material/TextField';

type Props = {
  placeholderTextField:string,
  id:string,
  widthTextField:number,
  value: string,
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void,
  onKeyPress: (event: React.KeyboardEvent<HTMLInputElement>) => void,
  inputRef: React.RefObject<HTMLInputElement>,
}
function TextFieldComponent({
  placeholderTextField, 
  id, 
  widthTextField, 
  value, 
  handleChange, 
  onKeyPress, 
  inputRef}: Props)
  {
    return(
        <div className="container-textField">
            <label htmlFor={id}>{placeholderTextField}</label>
            <TextField
                id= {id}
                placeholder= {placeholderTextField}
                multiline
                variant="outlined"
                value={value}
                onChange={handleChange}
                onKeyDown={onKeyPress}
                inputRef={inputRef}
                sx={
                    {
                      margin: '1% 0 0px 0',
                      width: `${widthTextField}%`, // Change the width as needed
                      '& .MuiInputBase-root': {
                        fontSize: '1.2rem', // Change the font size of the text
                      },
                      borderBottom: '1.90px solid #8b8b8b', 
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