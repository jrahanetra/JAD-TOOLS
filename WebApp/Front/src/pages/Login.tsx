import {
  IconButton,
  InputAdornment,
  InputLabel,
  OutlinedInput,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import { Typewriter } from "react-simple-typewriter";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import { useNavigate } from "react-router-dom";

function Login() {
  const [colorBackGround, setColorBackGround] = useState<string>();
  const [errorMess, setErrorMess] = useState<string>(" ");

  const userPseudo = "Admin2024";
  const password = "jasonfanasina";

  const [valuePseudo, setValuePseudo] = useState<string>("");
  const [valuePassword, setValuePassword] = useState<string>("");

  const [showPassword, setShowPassword] = React.useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);
  const handleMouseDownPassword = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    event.preventDefault();
  };
  const handleChangeValuePseudo =
    () => (event: React.ChangeEvent<HTMLInputElement>) => {
      setValuePseudo(event.target.value);
    };
  const handleChangeValuePassword =
    () => (event: React.ChangeEvent<HTMLInputElement>) => {
      setValuePassword(event.target.value);
    };
  
  const navigate = useNavigate()
  // Fonction de gestion du clic
  const handleLoginClick = () => {
    if(valuePseudo === userPseudo && valuePassword === password){
      console.log("USERADMIN")
      navigate("/report")
    }
    else{
      console.log("NONUSER")
      setErrorMess("Identifiant ou mot de passe incorrect")
    }
    console.log("Bouton Login cliqué");
    // Ajouter ici la logique de gestion du clic
  };

  return (
    <div className="body-login" style={{ backgroundColor: colorBackGround }}>
      <div className="div-title">
        <svg
          className="logoForLogin"
          width="70"
          height="75"
          viewBox="0 0 70 75"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <g clipPath="url(#clip0_504_4)">
            <path
              d="M32.8407 73.5032L2.15519 56.6373C1.50032 56.2765 0.956508 55.7583 0.578251 55.1343C0.199992 54.5103 0.000580372 53.8027 9.79161e-07 53.0822V18.9185C-0.000507455 18.2005 0.196997 17.4948 0.572752 16.8723C0.948505 16.2499 1.48933 15.7323 2.14111 15.3714L29.8261 0L36.3059 3.52288L8.59259 18.9103C7.94122 19.271 7.40066 19.7882 7.02494 20.4102C6.64921 21.0322 6.45146 21.7372 6.45148 22.4548V49.5217C6.45206 50.2422 6.65147 50.9498 7.02973 51.5738C7.408 52.1978 7.95181 52.7162 8.60668 53.0768L32.8349 66.393C33.4903 66.753 34.2335 66.9425 34.9902 66.9425C35.7467 66.9425 36.49 66.753 37.1453 66.393L61.3735 53.0768C62.0284 52.7162 62.5723 52.1978 62.9505 51.5738C63.3288 50.9498 63.5281 50.2422 63.5289 49.5217V18.8675C63.529 18.6043 63.4562 18.3458 63.3182 18.1178C63.1799 17.89 62.9811 17.7008 62.7419 17.5695C62.5025 17.438 62.2309 17.369 61.9545 17.3693C61.6782 17.3698 61.407 17.4395 61.1679 17.5715L34.9958 32.0092L28.6176 28.4058L63.5203 9.17078C64.1758 8.80813 64.9203 8.61643 65.6784 8.61502C66.4365 8.6136 67.1816 8.80252 67.8386 9.16273C68.4957 9.52295 69.0414 10.0417 69.4209 10.6668C69.8003 11.2919 70 12.0012 70 12.7232V53.0768C69.9995 53.799 69.7993 54.5083 69.4195 55.1333C69.0396 55.7585 68.4936 56.2772 67.8363 56.6373L37.1509 73.5032C36.4955 73.8632 35.7523 74.0527 34.9958 74.0527C34.2393 74.0527 33.4959 73.8632 32.8407 73.5032Z"
              fill="url(#paint0_linear_504_4)"
            />
            <path
              d="M35.0305 60.5868L13.918 48.8912V41.7542L34.9599 53.4122L42.0875 49.2722L42.1383 56.4602L35.0305 60.5868Z"
              fill="url(#paint1_linear_504_4)"
            />
            <path
              d="M35.0305 46.3933L13.9264 34.7003L13.918 27.558L34.9599 39.216L42.0875 35.076L42.1383 42.264L35.0305 46.3933Z"
              fill="url(#paint2_linear_504_4)"
            />
            <path
              d="M56.0261 48.8215L49.5605 52.5563V31.0943L56.0261 27.558V48.8215Z"
              fill="url(#paint3_linear_504_4)"
            />
          </g>
          <defs>
            <linearGradient
              id="paint0_linear_504_4"
              x1="34.9958"
              y1="-5.09787"
              x2="34.9958"
              y2="77.1522"
              gradientUnits="userSpaceOnUse"
            >
              <stop stopColor="#BB016C" />
              <stop offset="1" stopColor="#FAA307" />
            </linearGradient>
            <linearGradient
              id="paint1_linear_504_4"
              x1="28.0296"
              y1="-5.09787"
              x2="28.0296"
              y2="77.1522"
              gradientUnits="userSpaceOnUse"
            >
              <stop stopColor="#BB016C" />
              <stop offset="1" stopColor="#FAA307" />
            </linearGradient>
            <linearGradient
              id="paint2_linear_504_4"
              x1="28.0296"
              y1="-5.09787"
              x2="28.0296"
              y2="77.1522"
              gradientUnits="userSpaceOnUse"
            >
              <stop stopColor="#BB016C" />
              <stop offset="1" stopColor="#FAA307" />
            </linearGradient>
            <linearGradient
              id="paint3_linear_504_4"
              x1="52.7947"
              y1="-5.09787"
              x2="52.7947"
              y2="77.1522"
              gradientUnits="userSpaceOnUse"
            >
              <stop stopColor="#BB016C" />
              <stop offset="1" stopColor="#FAA307" />
            </linearGradient>
            <clipPath id="clip0_504_4">
              <rect width="70" height="75" fill="white" />
            </clipPath>
          </defs>
        </svg>
        <h1 className="title">
          <Typewriter
            words={["Attendance Management System"]}
            loop={false}
            cursor
            cursorStyle="|"
            typeSpeed={120}
            deleteSpeed={60}
            delaySpeed={1000}
          />
        </h1>
      </div>
      <div className="container" style={{ backgroundColor: colorBackGround }}>
        <div className="div-login-section">
          <div className="container-login-section">
            <div className="div-input-id">
              <h3>Admin Pseudo</h3>
              <TextField
                id="outlined-basic"
                variant="outlined"
                value={valuePseudo}
                onChange={handleChangeValuePseudo()}
                className="input-id"
              />
            </div>
            <div className="div-input-password">
              <h3>Password</h3>
              <OutlinedInput
                className="input-password"
                id="outlined-adornment-password"
                type={showPassword ? "text" : "password"}
                value={valuePassword}
                onChange={handleChangeValuePassword()}
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
            </div>
            <div className="container-buttonLogin">
              <button type="button" onClick={handleLoginClick}>
                Login
              </button>
            </div>
            <div className="container-connexion-chess">
              <h5 className="h5-connexion-chess">
                {errorMess}
              </h5>
            </div>
            <div className="footer-login-section">
              <h5 className="h5-Admin">Admin?</h5>
              <h5 className="h5-notAdmin">
                If you are not user you are not welcome.
              </h5>
            </div>
          </div>
        </div>
        <div className="container-image">
          <img
            src={`${process.env.PUBLIC_URL}../pictures/etudiant.png`}
            alt="Student"
            className="image"
          />
        </div>
      </div>
    </div>
  );
}

export default Login;
