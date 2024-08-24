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
import Etudiant from "../../../models/Etudiant";
import StudentCustomizeDataCourse from "../../../models/StudentCustomizeDataCourses";
import formatToDate from "../../../utils/FormateToDate";

type Props = {
  etudiant: Etudiant;
  filterByDate: string;
  filterByAttend: string;
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

function StundentRow({ etudiant, filterByDate, filterByAttend }: Props) {
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
  const fetchData = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/studentcourses/s?${params}`
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      setCourses(data);
    } catch (error) {
      toast.error(`Error : ${error}`);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    let sortedData = [...coursesOfStudent];

    if (filterByDate !== "" || filterByAttend !== "") {
      switch (filterByDate) {
        case "↓Recent":
          sortedData.sort(
            (a, b) =>
              formatToDate(b.courseDto.courseDate).getTime() -
              formatToDate(a.courseDto.courseDate).getTime()
          );
          break;
        case "↑Once":
          sortedData.sort(
            (a, b) =>
              formatToDate(a.courseDto.courseDate).getTime() -
              formatToDate(b.courseDto.courseDate).getTime()
          );
          break;
        default:
          break;
      }

      switch (filterByAttend) {
        case "YES":
          sortedData = sortedData.filter((item) => item.attending === true);
          break;
        case "NO":
          sortedData = sortedData.filter((item) => item.attending === false);
          break;
        default:
          break;
      }
    } else {
      sortedData.sort(
        (a, b) => a.studentDto.studentId - b.studentDto.studentId
      );
    }

    setFilteredData(sortedData);
  }, [filterByDate, filterByAttend, coursesOfStudent]);

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
                  {filteredData.map((course) => (
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

export default StundentRow;
