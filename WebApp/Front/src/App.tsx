import React from 'react';
import { BrowserRouter as Router,Route, Routes, useParams} from 'react-router-dom';
import Login from './pages/Login';
import Attend from './pages/Attend';
import Header from './components/common/header';
import Student from './pages/Student';
import DisplayStudent from './pages/InfoStudent';
import DisplayReport from './pages/Report';
import Registration from './pages/Registration';
import WorkingTime from './pages/WorkingTime';

function HeaderAndAttend(){
    return(
      <div>
          <Header />
          <Attend />
      </div>
    )
}
function HeaderAndStudent(){
  return(
    <div>
      <Header />
      <Student />
    </div>
  )
}
type Props={
  idStudent: string
}
function HeaderAndInfoStudent({ idStudent }:Props){
  return(
    <div>
      <Header />
      <DisplayStudent tofetch={idStudent}/>
    </div>
  )
}
function HeaderAndInfoStudentWrapper(){
  const {idStudent} = useParams<{ idStudent : string }>();

  return <HeaderAndInfoStudent idStudent={idStudent ??""} />
}

function HeaderAndReport(){
  return(
    <div>
      <Header />
      <DisplayReport />
    </div>
  )
}

function HeaderAndRegistration(){
  return(
    <div>
      <Header />
      <Registration />
    </div>
  )
}

function HeaderAndWorkingTime(){
  return(
    <div>
      <Header />
      <WorkingTime />
    </div>
  )
}
function App() {    
  return (
    <Router>
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />}/>
            <Route path="/report" element={<HeaderAndReport />} />
            <Route path="/attendance" element={<HeaderAndAttend/>} />
            <Route path="/student" element={<HeaderAndStudent/>} />
            <Route path="/student/:idStudent" element={<HeaderAndInfoStudentWrapper />} />
            <Route path="/registration" element={<HeaderAndRegistration />} />
            <Route path="/workingTime" element={<HeaderAndWorkingTime />} />

        </Routes>
    </Router>
  );
};

export default App;
