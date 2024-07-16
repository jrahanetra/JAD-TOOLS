import React from "react";
import IconButton from '@mui/material/IconButton';
import FilledInput from '@mui/material/FilledInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import SearchIcon from '@mui/icons-material/Search';
import TableData from "../components/Attendance/Table/AttendTable";
import FilterComponent from "../components/Attendance/Filtering/filter";

function Attend(){
    const [showPassword, setShowPassword] = React.useState(false);
    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };
    return(
        <div className="container-attend">
            <div className="container-title">
                <h1>Attendance List</h1>
                <FormControl 
                    sx={{ 
                        m: 1, 
                        width: '28ch', 
                        backgroundColor: '#FFFFFF'  // Change this to your desired background color
                    }} 
                    variant="filled"
                >
                    <InputLabel htmlFor="filled-adornment-password">Enter keywords</InputLabel>
                        <FilledInput
                            id="filled-adornment-password"
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
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>
                <div className="div-container-tabFilter">
                    <FilterComponent />
                    <TableData />
                </div>
            </div>
        </div>
    )
}
export default Attend;