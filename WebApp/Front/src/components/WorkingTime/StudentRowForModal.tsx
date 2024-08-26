import React, { useEffect, useState } from "react";
import { styled } from "@mui/material/styles";
import { toast } from "react-toastify";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import Typography from "@mui/material/Typography";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import {
  Box,
  Collapse,
  IconButton,
  Table,
  TableBody,
  TableHead,
} from "@mui/material";
import StudentCustomizeDataCourse from "../../models/StudentCustomizeDataCourses";
import fecthStudentCourses from "../../fecthAPI/FetchStudentCourses";
import Etudiant from "../../models/Etudiant";

type Props = {
  etudiant: Etudiant;
};

const StyledTableRow = styled(TableRow)(() => ({
  "& > *": {
    borderBottom: "unset",
  },
}));

const CustomTableCell = styled(TableCell)(({ theme }) => ({
  fontSize: "1.25rem",
}));

// Créez des composants stylés pour TableCell
const CustomTableCell1 = styled(TableCell)(({ theme }) => ({
  fontSize: "1rem",
}));

function StudentRowForModal({ etudiant }: Props) {
  const [coursesOfStudent, setCourses] = useState<StudentCustomizeDataCourse[]>(
    []
  );
  const [filteredData, setFilteredData] = useState<
    StudentCustomizeDataCourse[]
  >([]);
  const [open, setOpen] = useState(false);
  const postData = {
    studentId: etudiant.studentId.toString(),
  };
  const params = new URLSearchParams(postData).toString();
  fecthStudentCourses(coursesOfStudent, setCourses, params);
  return (
    <>
      <StyledTableRow>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <CustomTableCell component="th" scope="row">
          {etudiant.studentId}
        </CustomTableCell>
        <CustomTableCell>{etudiant.lastname}</CustomTableCell>
        <CustomTableCell>{etudiant.firstname}</CustomTableCell>
        <CustomTableCell>{etudiant.address}</CustomTableCell>
      </StyledTableRow>
      <TableRow>
        <CustomTableCell
          style={{ paddingBottom: 0, paddingTop: 0 }}
          colSpan={6}
        >
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box margin={1}>
              <Typography variant="h6" gutterBottom component="div">
                Courses
              </Typography>
              <Table size="small" aria-label="purchases">
                <TableHead>
                  <TableRow>
                    <CustomTableCell1>NameCourse</CustomTableCell1>
                    <CustomTableCell1 align="right">Date</CustomTableCell1>
                    <CustomTableCell1 align="right">Begin</CustomTableCell1>
                    <CustomTableCell1 align="right">End</CustomTableCell1>
                    <CustomTableCell1 align="right">Attend</CustomTableCell1>
                    <CustomTableCell1 align="right">
                      JustificatedState
                    </CustomTableCell1>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {coursesOfStudent.map((course) => (
                    <TableRow key={course.courseDto.courseId}>
                      <CustomTableCell1 component="th" scope="row">
                        {course.courseDto.subjectDto.subjectName}
                      </CustomTableCell1>
                      <CustomTableCell1 align="right">
                        {course.courseDto.courseDate}
                      </CustomTableCell1>
                      <CustomTableCell1 align="right">
                        {course.courseDto.courseBeginTime}
                      </CustomTableCell1>
                      <CustomTableCell1 align="right">
                        {course.courseDto.courseEndTime}
                      </CustomTableCell1>
                      <CustomTableCell1 align="right">{`${course.attending}`}</CustomTableCell1>
                      <CustomTableCell1 align="right">{`${course.justificated}`}</CustomTableCell1>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </CustomTableCell>
      </TableRow>
    </>
  );
}

export default StudentRowForModal;
