import React from "react";
import TextField from '@mui/material/TextField';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { faCheck, faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

type Props = {
  placeholderTextField:string,
  id:string,
  widthTextField:number,
  value: string,
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void,
  onKeyPress: (event: React.KeyboardEvent<HTMLInputElement>) => void,
  inputRef: React.RefObject<HTMLDivElement>,
  colorBorder: string,
  isValid: boolean // TO KNOW IF VALUES IS VALID OR NOT 
}

const theme = createTheme();

function TextFieldComponent({
  placeholderTextField, 
  id, 
  widthTextField, 
  value, 
  handleChange, 
  onKeyPress, 
  inputRef,
  colorBorder,
  isValid}: Props)
  {
    return(
        <div className="container-textField">
            <label htmlFor={id}>
              {placeholderTextField} : 
              <span className={isValid? "valid" : "hide"}>
                <FontAwesomeIcon 
                  icon={faCheck}
                  style={
                    {
                      margin: "0 0 0 10px",
                      color: "#1fd61b",
                      fontSize: "1.5rem"
                    }
                  } />
              </span>
              <span className={isValid || !value ? "hide" : "invalid"}>
                <FontAwesomeIcon 
                  icon={faTimes}
                  style={
                    {
                      margin: "0 0 0 10px",
                      color: "#EE2449",
                      fontSize: "1.5rem"
                    }
                  } />
              </span>

            </label>
            <ThemeProvider theme={theme}>
            <TextField
                id= {id}
                placeholder={placeholderTextField}
                multiline
                variant="outlined"
                value={value}
                onChange={handleChange}
                onKeyDown={onKeyPress}
                inputRef={inputRef}
                className={isValid? "borderBlue" : "borderRed"}
                sx={
                    {
                      margin: '1% 0 0px 0',
                      width: `${widthTextField}%`, // Change the width as needed
                      borderBottom: '1.90px solid #8b8b8b', 
                      '& .MuiOutlinedInput-root': {
                        '&.Mui-focused fieldset': {
                          borderColor: `#${colorBorder}`, // Border color on focus
                        },
                      },
                      backgroundColor: '#FFFFFF'
                      
                    }
                }
            />
          </ThemeProvider>
        </div>
    )
}
export default TextFieldComponent;