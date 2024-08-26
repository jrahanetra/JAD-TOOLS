import React, { useEffect, useRef, useState } from "react";
import IconButton from "@mui/material/IconButton";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import CameraAltIcon from "@mui/icons-material/CameraAlt";
import { SelectChangeEvent } from "@mui/material";
import { useNavigate } from "react-router-dom";
import dayjs, { Dayjs } from "dayjs";
import TextFieldComponent from "./FieldsAndSelect/TextField";
import SelectInputComponent from "./FieldsAndSelect/SelectInput";
import DateFieldComponent from "./FieldsAndSelect/DateField";
import PhoneNumberInputComponent from "./FieldsAndSelect/PhoneNumberInput";

interface FormValues {
  valueName: string;
  valueFirstNames: string;
  valueSex: string;
  valueBirthday: Dayjs | null;
  valueLevel: string;
  valueAddress: string;
  valuePhoneNumber: string | undefined;
  valueEmail: string;
  valueImageName: string;
}

function ContainerRegistration() {
  const [file, setFile] = useState<string>(
    `${process.env.PUBLIC_URL}../pictures/pngtree-vector-business-men-icon-png-image_4186858.jpg`
  );

  const fileInputRef = useRef<HTMLInputElement>(null);

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target.files && e.target.files.length > 0) {
      setFile(URL.createObjectURL(e.target.files[0]));
    }
  }
  const handleClick = () => {
    if (fileInputRef.current) {
      fileInputRef.current.click();
    }
  };

  const NAME_REGEX = /^[a-zA-Z][a-zA-Z0-9-_ ]{3,23}$/;
  const ADDRESS_REGEX =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[ !@#$%]).{4,24}$/;
  const EMAIL_REGEX = /^[a-zA-Z][a-zA-z0-9-_]{4,24}@gmail.com$/;

  // Values of inputs
  const [values, setValue] = useState<FormValues>({
    valueName: "",
    valueFirstNames: "",
    valueSex: "",
    valueBirthday: null,
    valueLevel: "",
    valueAddress: "",
    valuePhoneNumber: "",
    valueEmail: "",
    valueImageName: "",
  });

  // STATES VALIDATION OF SOME FIELDS
  const [validName, setValidName] = useState(false);
  const [validFirstNames, setValidFirstNames] = useState(false);
  const [validSex, setValidSex] = useState(false);
  const [validLevel, setValidLevel] = useState(false);
  const [validAddress, setValidAddress] = useState(false);
  const [validEmail, setValidEmail] = useState(false);
  const [validImageName, setValidImageName] = useState(false);

  // TEST VALIDATION OF FIELDS
  useEffect(() => {
    const result = NAME_REGEX.test(values.valueName);
    setValidName(result);
  }, [values.valueName]);

  useEffect(() => {
    const result = NAME_REGEX.test(values.valueFirstNames);
    setValidFirstNames(result);
  }, [values.valueFirstNames]);

  useEffect(() => {
    const result = values.valueSex !== "";
    setValidSex(result);
  }, [values.valueSex]);

  useEffect(() => {
    const result = values.valueLevel !== "";
    setValidLevel(result);
  }, [values.valueLevel]);

  useEffect(() => {
    const result = ADDRESS_REGEX.test(values.valueAddress);
    setValidAddress(result);
  }, [values.valueAddress]);

  useEffect(() => {
    const result = EMAIL_REGEX.test(values.valueEmail);
    setValidEmail(result);
  }, [values.valueEmail]);

  useEffect(() => {
    const result = values.valueImageName !== "";
    setValidImageName(result);
  }, [values.valueImageName]);

  // HANDLES CHANGES TEXTFIELD InputLabel,
  const handleChangeValue =
    (field: string) =>
    (
      eventOrValue:
        | React.ChangeEvent<HTMLInputElement>
        | SelectChangeEvent<string>
        | Dayjs
        | null
        | string
        | undefined
    ) => {
      if (dayjs.isDayjs(eventOrValue)) {
        setValue({
          ...values,
          [field]: eventOrValue,
        });
      } else if (typeof eventOrValue === "string") {
        setValue({
          ...values,
          [field]: eventOrValue,
        });
      } else {
        setValue({
          ...values,
          [field]: eventOrValue?.target.value,
        });
      }
    };

  // References for all inputs
  const fieldName = useRef<HTMLInputElement>(null);
  const fieldFirstNames = useRef<HTMLInputElement>(null);
  const fieldSex = useRef<HTMLDivElement>(null);
  const fieldLevel = useRef<HTMLDivElement>(null);
  const fieldBirthday = useRef<HTMLDivElement>(null);
  const fieldAddress = useRef<HTMLInputElement>(null);
  const fieldPhone = useRef<HTMLInputElement>(null);
  const fieldEmail = useRef<HTMLInputElement>(null);
  const fieldImageName = useRef<HTMLInputElement>(null);

  // To pass to the next input
  const handleKeyPress =
    (
      nextFieldRef: React.RefObject<HTMLElement>,
      previousFieldRef: React.RefObject<HTMLElement>
    ) =>
    (
      event:
        | React.KeyboardEvent<HTMLElement>
        | React.KeyboardEvent<HTMLInputElement>
    ) => {
      // FOCUSING TO THE NEXTFIELD
      if (event.key === "ArrowRight" || event.key === "Enter") {
        nextFieldRef.current?.focus();
      }
      // FOCUSING TO THE PREVIOUSFIELD
      else if (event.key === "ArrowLeft") {
        previousFieldRef.current?.focus();
      }
    };

  function areValuesValid(valuesParams: FormValues) {
    return (
      Object.values(valuesParams).every(
        (value) => value !== null && value !== ""
      ) && values.valueBirthday !== null
    );
  }
  function checkNullOrEmptyKeys<T extends Record<string, any>>(
    obj: T
  ): (keyof T)[] {
    return (Object.keys(obj) as (keyof T)[]).filter(
      (key) => obj[key] === null || obj[key] === ""
    );
  }
  const navigate = useNavigate();
  const colorBordureFocus = "257DE4";
  // To submit all values input
  const submitTheRegistration = async () => {
    if (areValuesValid(values)) {
      // Construire l'objet des données à envoyer
      const studentData = {
        firstname: values.valueFirstNames,
        lastname: values.valueName,
        sex: values.valueSex,
        birthday: values.valueBirthday?.format("DD-MM-YYYY"),
        address: values.valueAddress,
        phoneNumber: values.valuePhoneNumber,
        email: values.valueEmail,
        imageName: values.valueImageName,
      };

      try {
        // Faire une requête POST au serveur
        const response = await fetch("http://localhost:8080/students", {
          method: "POST",
          headers: {
            "Content-Type": "application/json", // Indique que le corps de la requête est en JSON
          },
          body: JSON.stringify(studentData),
        });

        if (!response.ok) {
          throw new Error("Erreur lors de l'envoi des données");
        }

        const student = await response.json(); // Utilisez await pour obtenir la réponse JSON

        let levelID = 1;
        switch (values.valueLevel) {
          case "L1":
            levelID = 1;
            break
          case "L2":
            levelID = 2;
            break
          case "L3":
            levelID = 3;
            break
          default:
            break
        }
        // Construire l'objet des données de registration
        const registrationData = {
          studentId: student.studentId,
          majorId: 1,
          levelId: levelID,
          year: 2024,
        };

        console.log("Student : ", student);
        console.log("registrationData : ", registrationData);

        // Envoyer les données de registration
        const registrationResponse = await fetch(
          "http://localhost:8080/registrations",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json", // Indique que le corps de la requête est en JSON
            },
            body: JSON.stringify(registrationData),
          }
        );

        if (!registrationResponse.ok) {
          throw new Error("Erreur lors de l'envoi des données de registration");
        }

        const dataRegistration = await registrationResponse.json();
        console.log("Données envoyées avec succès:", dataRegistration);
        navigate('/student')
      } catch (error) {
        console.error("Erreur lors de l'envoi des données:", error);
        navigate("/student");
      }
    } else {
      switch (checkNullOrEmptyKeys(values)[0]) {
        case "valueName":
          fieldName.current?.focus();
          break;
        case "valueFirstNames":
          fieldFirstNames.current?.focus();
          break;
        case "valueSex":
          fieldSex.current?.focus();
          break;
        case "valueBirthday":
          fieldBirthday.current?.focus();
          break;
        case "valueLevel":
          fieldLevel.current?.focus();
          break;
        case "valueAddress":
          fieldAddress.current?.focus();
          break;
        case "valueImageName":
          fieldImageName.current?.focus();
          break;
        case "valuePhoneNumber":
          fieldPhone.current?.focus();
          break;
        case "valueEmail":
          fieldEmail.current?.focus();
          break;
        default:
          break;
      }
    }
  };

  return (
    <div className="container-attend">
      <div className="container-title">
        <h1>Registration Student</h1>
      </div>
      <div className="container-list">
        <div className="div-refresh">
          <a href="/" className="a-refresh">
            Refresh
          </a>
        </div>
        <div className="container-textField-profilePicture">
          <div className="container-profilePicture">
            <div className="profile-container1">
              <img src={file} alt="" className="profile-picture" />
            </div>
            <div className="container-IconCam">
              <IconButton
                onClick={handleClick}
                title="Edit Profile Picture"
                sx={{
                  color: "black", // Change to desired color
                  fontSize: "4rem", // Change size as needed
                  "&:hover": {
                    color: "#257DE4", // Change hover color
                  },
                }}
              >
                <CameraAltIcon sx={{ fontSize: "inherit" }} />
              </IconButton>
              <input
                type="file"
                ref={fileInputRef}
                style={{ display: "none" }}
                onChange={handleChange}
              />
            </div>
          </div>
          <div className="container-textFields">
            <TextFieldComponent
              placeholderTextField="NAME"
              id="name"
              widthTextField={85}
              value={values.valueName}
              handleChange={handleChangeValue("valueName")}
              onKeyPress={handleKeyPress(fieldFirstNames, fieldEmail)}
              inputRef={fieldName}
              colorBorder={colorBordureFocus}
              isValid={validName}
            />
            <TextFieldComponent
              placeholderTextField="FIRSTNAMES"
              id="firstnames"
              widthTextField={85}
              value={values.valueFirstNames}
              handleChange={handleChangeValue("valueFirstNames")}
              onKeyPress={handleKeyPress(fieldSex, fieldName)}
              inputRef={fieldFirstNames}
              colorBorder={colorBordureFocus}
              isValid={validFirstNames}
            />
            <div className="container-textFields1">
              <SelectInputComponent
                nameLabel="SEX"
                widthSelect={70}
                valuesPossible={["Male", "Female"]}
                value={values.valueSex}
                handleChange={handleChangeValue("valueSex")}
                onKeyPress={handleKeyPress(fieldBirthday, fieldFirstNames)}
                inputRef={fieldSex}
                isValid={validSex}
                isWithLabel
              />
              <DateFieldComponent
                value={values.valueBirthday}
                onChange={handleChangeValue("valueBirthday")}
                onKeyPress={handleKeyPress(fieldLevel, fieldSex)}
                inputRef={fieldBirthday}
              />
            </div>
            <SelectInputComponent
              nameLabel="LEVEL"
              widthSelect={85}
              valuesPossible={["L1", "L2"]}
              value={values.valueLevel}
              handleChange={handleChangeValue("valueLevel")}
              onKeyPress={handleKeyPress(fieldAddress, fieldBirthday)}
              inputRef={fieldLevel}
              isValid={validLevel}
              isWithLabel
            />
          </div>
        </div>
        <div className="container-otherTextFields">
          <div className="container-addressTextField">
            <TextFieldComponent
              placeholderTextField="LOT VK 77 ITAOSY"
              id="address"
              widthTextField={88}
              value={values.valueAddress}
              handleChange={handleChangeValue("valueAddress")}
              onKeyPress={handleKeyPress(fieldImageName, fieldLevel)}
              inputRef={fieldAddress}
              colorBorder={colorBordureFocus}
              isValid={validAddress}
            />
            <TextFieldComponent
              placeholderTextField="ImageName"
              id="imageName"
              widthTextField={80}
              value={values.valueImageName}
              handleChange={handleChangeValue("valueImageName")}
              onKeyPress={handleKeyPress(fieldPhone, fieldAddress)}
              inputRef={fieldImageName}
              colorBorder={colorBordureFocus}
              isValid={validImageName}
            />
          </div>
          <div className="container-phoneAndEmailTextField">
            <PhoneNumberInputComponent
              valueNumber={values.valuePhoneNumber}
              handleChange={handleChangeValue("valuePhoneNumber")}
              onKeyPress={handleKeyPress(fieldEmail, fieldImageName)}
              refElement={fieldPhone}
            />
            <TextFieldComponent
              placeholderTextField="EMAIL"
              id="email"
              widthTextField={100}
              value={values.valueEmail}
              handleChange={handleChangeValue("valueEmail")}
              onKeyPress={handleKeyPress(fieldName, fieldPhone)}
              inputRef={fieldEmail}
              colorBorder={colorBordureFocus}
              isValid={validEmail}
            />
          </div>
          <div className="container-button">
            <Button
              variant="contained"
              endIcon={<SendIcon />}
              sx={{
                backgroundColor: "#1FD61B",
                width: "250px",
                height: "55px",
                fontSize: "19px",
                "&:hover": {
                  backgroundColor: "#257DE4", // Background color on hover
                },
              }}
              onClick={submitTheRegistration}
            >
              Send
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default ContainerRegistration;
