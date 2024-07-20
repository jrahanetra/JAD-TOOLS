import React, { useRef, useState } from "react";
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import { SelectChangeEvent   } from "@mui/material";
import dayjs, { Dayjs } from "dayjs";
import TextFieldComponent from "./FieldsAndSelect/TextField";
import SelectInputComponent from "./FieldsAndSelect/SelectInput";
import DateFieldComponent from "./FieldsAndSelect/DateField";

function ContainerRegistration(){
    const [file, setFile] = useState<string>(`${process.env.PUBLIC_URL}../pictures/pngtree-vector-business-men-icon-png-image_4186858.jpg`);

    const fileInputRef = useRef<HTMLInputElement>(null);

    function handleChange(e : React.ChangeEvent<HTMLInputElement>) {
        if (e.target.files && e.target.files.length > 0) {
            setFile(URL.createObjectURL(e.target.files[0]));
        }
    }
    const handleClick = () => {
        if (fileInputRef.current) {
          fileInputRef.current.click();
        }
    };
    // Values of inputs
    const [values, setValue] = useState({fieldName:'', 
                                        fieldFirstNames:'', 
                                        fieldSex:'',
                                        fieldBirthday:'', 
                                        fieldLevel:'',
                                        fieldAddress:'',
                                        fieldPhone:'',
                                        fieldEmail:''});

    const [valueBirthday, setValueBirthday] = React.useState<Dayjs | null>(dayjs('2022-04-17'));


    // HANDLES CHANGES TEXTFIELD InputLabel,
    const handleChangeValue = (field: string) => (event: React.ChangeEvent<HTMLInputElement> | SelectChangeEvent<string>) => {
        setValue({
            ...values,
            [field]: event.target.value,
        });
    };

    const handleChangeDate = (newValue: Dayjs | null) => {
        setValueBirthday(newValue)
    }

    // References for all inputs
    const fieldName = useRef<HTMLInputElement>(null)
    const fieldFirstNames = useRef<HTMLInputElement>(null)
    const fieldSex = useRef<HTMLDivElement>(null)
    const fieldBirthday = useRef<HTMLInputElement>(null)
    const fieldLevel = useRef<HTMLInputElement>(null)
    const fieldAddress = useRef<HTMLInputElement>(null)
    const fieldPhone = useRef<HTMLInputElement>(null)
    const fieldEmail = useRef<HTMLInputElement>(null)

    // To pass to the next input
    const handleKeyPress = (nextFieldRef: React.RefObject<HTMLElement>) => (event: React.KeyboardEvent<HTMLElement>) => {
        if (event.key === 'ArrowRight' || event.key === "Enter") {
            console.log("ENTER PRESSED")
          nextFieldRef.current?.focus();
        }
    };

    // To submit all values input
    const submitTheRegistration= () => {
        console.log(valueBirthday?.toDate())
        console.log(values.fieldName);
    }

    return( 
        <div className="container-attend">
            <div className="container-title">
                <h1>Registration Student</h1>
            </div>
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>
                <div className="container-textField-profilePicture">
                    <div className="container-profilePicture">
                        <div className="profile-container1"> 
                            <img src={file} alt="" className="profile-picture"/>
                        </div>
                        <div className="container-IconEdit">
                            <IconButton 
                                onClick={handleClick} 
                                title="Edit Profile Picture"
                                sx={{
                                    color: 'black', // Change to desired color
                                    fontSize: '4rem', // Change size as needed
                                    '&:hover': {
                                    color: '#257DE4', // Change hover color
                                    },
                                }}
                                >
                                <EditIcon 
                                    sx={
                                        { fontSize: 'inherit' }
                                    }/>
                            </IconButton>
                            <input
                                type="file"
                                ref={fileInputRef}
                                style={{ display: 'none' }}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div className="container-textFields">
                        <TextFieldComponent 
                            placeholderTextField="NAME" 
                            id="name" 
                            widthTextField={85} 
                            value={values.fieldName} 
                            handleChange={handleChangeValue('fieldName')} 
                            onKeyPress={handleKeyPress(fieldFirstNames)}
                            inputRef={fieldName}/>
                        <TextFieldComponent 
                            placeholderTextField="FIRSTNAMES" 
                            id="firstnames" 
                            widthTextField={85} 
                            value={values.fieldFirstNames} 
                            handleChange={handleChangeValue('fieldFirstNames')}
                            onKeyPress={handleKeyPress(fieldSex)}
                            inputRef={fieldFirstNames}/>
                        <div className="container-textFields1">
                            <SelectInputComponent 
                                value={values.fieldSex}
                                handleChange={handleChangeValue('fieldSex')}
                                onKeyPress={handleKeyPress(fieldBirthday)}
                                inputRef={fieldSex}
                                nextInputRef={fieldBirthday}/>
                            <DateFieldComponent 
                                value={valueBirthday}
                                onChange={handleChangeDate}/>
                           
                        </div>
                        <TextFieldComponent 
                            placeholderTextField="LEVEL"
                            id="level" 
                            widthTextField={85} 
                            value={values.fieldLevel} 
                            handleChange={handleChangeValue('fieldLevel')}
                            onKeyPress={handleKeyPress(fieldAddress)}
                            inputRef={fieldLevel}/>               
                    </div>
                </div>
                <div className="container-otherTextFields">
                    <div className="container-addressTextField">
                        <TextFieldComponent 
                            placeholderTextField="ADDRESS" 
                            id="address" 
                            widthTextField={100} 
                            value={values.fieldAddress} 
                            handleChange={handleChangeValue('fieldAddress')}
                            onKeyPress={handleKeyPress(fieldPhone)}
                            inputRef={fieldAddress}/>
                    </div>
                    <div className="container-phoneAndEmailTextField">
                        <TextFieldComponent 
                            placeholderTextField="PHONE" 
                            id="phone" 
                            widthTextField={60} 
                            value={values.fieldPhone} 
                            handleChange={handleChangeValue('fieldPhone')}
                            onKeyPress={handleKeyPress(fieldEmail)}
                            inputRef={fieldPhone}/>
                        <TextFieldComponent 
                            placeholderTextField="EMAIL" 
                            id="email" 
                            widthTextField={100} 
                            value={values.fieldEmail} 
                            handleChange={handleChangeValue('fieldEmail')}
                            onKeyPress={() => {}} 
                            inputRef={fieldEmail}/>
                    </div>
                    <div className="container-button">
                        <Button 
                            variant="contained" 
                            endIcon={<SendIcon />} 
                            sx={
                                {
                                    backgroundColor: '#1FD61B',
                                    width: '250px',
                                    height: '55px',
                                    fontSize: '19px',
                                    '&:hover': {
                                        backgroundColor: '#257DE4', // Background color on hover
                                    },
                                }
                            }
                            onClick={submitTheRegistration}
                        >
                            Send
                        </Button>
                    </div>
                </div>              
            </div>
        </div>
    )
}
export default ContainerRegistration;