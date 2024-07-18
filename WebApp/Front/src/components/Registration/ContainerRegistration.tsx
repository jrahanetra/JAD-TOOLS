import React, { useRef, useState } from "react";
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import TextFieldComponent from "./TextField";
import SelectInputComponent from "./SelectInput";


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
                        <TextFieldComponent placeholderTextField="NAME" id="name" widthTextField={85} />
                        <TextFieldComponent placeholderTextField="FIRSTNAMES" id="firstnames" widthTextField={85} />
                        <div className="container-textFields1">
                            <SelectInputComponent />
                            <TextFieldComponent placeholderTextField="BIRTHDAY" id="birthday" widthTextField={70} />
                        </div>
                        <TextFieldComponent placeholderTextField="LEVEL"id="level" widthTextField={85} />               
                    </div>
                </div>
                <div className="container-otherTextFields">
                    <div className="container-addressTextField">
                        <TextFieldComponent placeholderTextField="ADDRESS" id="address" widthTextField={100} />
                    </div>
                    <div className="container-phoneAndEmailTextField">
                        <TextFieldComponent placeholderTextField="PHONE" id="address" widthTextField={60} />
                        <TextFieldComponent placeholderTextField="EMAIL" id="email" widthTextField={100} />
                    </div>
                    <div className="container-button">
                        <Button variant="contained" endIcon={<SendIcon />} sx={
                            {
                                backgroundColor: '#1FD61B',
                                width: '250px',
                                height: '55px',
                                fontSize: '19px',
                                '&:hover': {
                                    backgroundColor: '#257DE4', // Background color on hover
                                },
                            }
                        }>
                            Send
                        </Button>
                    </div>
                </div>              
            </div>
        </div>
    )
}
export default ContainerRegistration;