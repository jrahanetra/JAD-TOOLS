import { FilledInput, FormControl, IconButton, InputAdornment, InputLabel } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import React from "react";

type Props = {
    searchValue: string;
    handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void,
}
function InputSearch({ 
    searchValue, 
    handleChange}: Props
) {

    return (
        <div>
            <FormControl
                sx={{
                    m: 1,
                    width: '28ch',
                    backgroundColor: '#FFFFFF'
                }}
                variant="filled"
            >
                <InputLabel htmlFor="filled-adornment-password">Enter keywords</InputLabel>
                <FilledInput
                    id="filled-adornment-password"
                    value={searchValue}
                    onChange={handleChange}
                    endAdornment={
                        <InputAdornment position="end">
                            <IconButton type="button" sx={{ p: '10px' }} aria-label="search">
                                <SearchIcon />
                            </IconButton>
                        </InputAdornment>
                    }
                />
            </FormControl>
        </div>
    )
}

export default InputSearch;