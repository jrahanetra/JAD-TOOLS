import React from "react";
import IconButton from '@mui/material/IconButton';
import FilledInput from '@mui/material/FilledInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import FilterComponent from "../components/Student/Filtering/filter";
import TableData from "../components/Student/Table/StudentTable";


function Student(){
    const [showPassword, setShowPassword] = React.useState(false);
    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };
    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };
    
    return(
        <div className="container-attend">
            <div className="container-title">
                <h1>Student</h1>
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
                        type={showPassword ? 'text' : 'password'}
                        endAdornment={
                        <InputAdornment position="end">
                            <IconButton
                            aria-label="toggle password visibility"
                            onClick={handleClickShowPassword}
                            onMouseDown={handleMouseDownPassword}
                            edge="end"
                            >
                            {showPassword ? <VisibilityOff /> : <Visibility />}
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
                    <TableData />
                    <FilterComponent />
                </div>
            </div>
        </div>
    )
}
export default Student;