import React, { RefObject, useReducer, useRef, useState } from "react";
import 'react-phone-number-input/style.css'
import PhoneInput from 'react-phone-number-input'
import { InputLabel, styled } from "@mui/material";

type Props = {
    valueNumber: string | undefined,
    handleChange: (value?:  string) => void,
    onKeyPress : (event : React.KeyboardEvent<HTMLInputElement>) => void,
    refElement: React.RefObject<HTMLInputElement>,
}

const StyledPhoneInput = styled(PhoneInput)(({ theme }) => ({
    width: '70%',
    height: '40%',
    border: '1px solid #cbcbcb',
    borderBottom: '2px solid #8b8b8b',     
    borderRadius: '5px',
    padding: '10px 10px',
    '& .PhoneInputInput': {
      padding: '16.5px 14px',
      borderRadius: '4px',
      border: `1px solid ${theme.palette.divider}`,
      fontSize: '16px',
      outline: 'none',
      '&:focus': {
        borderColor: theme.palette.primary.main,
      },
    },
    '& .PhoneInputCountrySelect': {
      marginRight: '10px',
    },
}));

function PhoneNumberInputComponent({
  valueNumber, 
  handleChange,
  onKeyPress,
  refElement
} : Props) 
{
  // `value` will be the parsed phone number in E.164 format.
  // Example: "+12133734253".
  return (
    <div className="container-textField">
        <InputLabel htmlFor="phoneNumber" sx={{margin:'5px 0 5px 0',color:'black'}}>PHONE_NUMBER</InputLabel>
        <StyledPhoneInput
            id="phoneNumber"
            value={valueNumber}
            onChange={handleChange}
            onKeyDown={onKeyPress}
            placeholder="Enter phone number"
            numberInputProps={{
              ref: refElement,
              name: 'phone',
              required: true,
            }}
        />
    </div>
  )
}
export default PhoneNumberInputComponent
