import React, { useEffect, useState } from "react";
import Paper from "@mui/material/Paper";
import { ToastContainer } from "react-toastify";
import {
  styled,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import StudentCustomizeDataCourse from "../../models/StudentCustomizeDataCourses";
import fetchStudentCourses from "../../fecthAPI/FetchStudentCourses";

type Props = {
  idStudent: number;
  setNbYes: React.Dispatch<React.SetStateAction<number>>;
  setNbNo: React.Dispatch<React.SetStateAction<number>>;
};

const CustomTableCell = styled(TableCell)(() => ({
  fontSize: "1.25rem",
}));

// Créez des composants stylés pour TableCell
const CustomTableCell1 = styled(TableCell)(() => ({
  fontSize: "1rem",
}));

function InfoTable({ idStudent, setNbYes, setNbNo }: Props) {
  const [coursesOfStudent, setCourses] = useState<StudentCustomizeDataCourse[]>(
    []
  );
  let yes = 0;
  let no = 0;
  const postData = {
    studentId: idStudent.toString(),
  };
  const params = new URLSearchParams(postData).toString();
  fetchStudentCourses(coursesOfStudent, setCourses, params)
  useEffect(() => {
    coursesOfStudent.forEach((course) => {
      if (course.attending === false) {
        no += 1;
      } else {
        yes += 1;
      }
    });
    setNbNo(no);
    setNbYes(yes);
  }, [coursesOfStudent])
  return (
    <div>
      <ToastContainer />
      <TableContainer
        component={Paper}
        style={{ width: "1400px", maxHeight: 700 }}
      >
        <Table aria-label="collapsible table">
          <TableHead>
            <TableRow>
              <CustomTableCell>Course</CustomTableCell>
              <CustomTableCell align="left">Date</CustomTableCell>
              <CustomTableCell align="left">BeginTime</CustomTableCell>
              <CustomTableCell align="left">EndTime</CustomTableCell>
              <CustomTableCell align="left">Attend</CustomTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {coursesOfStudent.map((data) => (
              <TableRow key={data.courseDto.courseId}>
                <CustomTableCell1 component="th" scope="row">
                  {data.courseDto.subjectDto.subjectName}
                </CustomTableCell1>
                <CustomTableCell1 align="left">
                  {data.courseDto.courseDate}
                </CustomTableCell1>
                <CustomTableCell1 align="left">
                  {data.courseDto.courseBeginTime}
                </CustomTableCell1>
                <CustomTableCell1 align="left">
                  {data.courseDto.courseEndTime}
                </CustomTableCell1>
                <CustomTableCell1 align="left">{`${data.attending}`}</CustomTableCell1>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}
export default InfoTable;
