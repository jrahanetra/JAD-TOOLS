import React, { useEffect, useState } from "react";
import { ToastContainer } from "react-toastify";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import styled from "@emotion/styled";
import Paper from "@mui/material/Paper";
import Etudiant from "../../../models/Etudiant";
import StundentRow from "./StudentData";
import fecthStudent from "../../../fecthAPI/FetchStudents";

// Créez des composants stylés pour TableCell
const CustomTableCell = styled(TableCell)(() => ({
  fontSize: "1.1rem",
}));

type Props = {
  keyWordToFilter: string;
  filterByDate: string;
  filterByName: string;
  filterByAttend: string;
};
function TableData({
  keyWordToFilter,
  filterByDate,
  filterByName,
  filterByAttend,
}: Props) {
  const [etudiantList, setEtudiant] = useState<Etudiant[]>([]);
  const [filteredData, setFilteredData] = useState<Etudiant[]>([]);
  fecthStudent(etudiantList, setEtudiant);
  useEffect(() => {
    const sortedData = [...etudiantList];
    if (filterByName !== "" || filterByAttend !== "") {
      switch (filterByName) {
        case "↓By A":
          sortedData.sort((a, b) => a.lastname.localeCompare(b.lastname));
          break;
        case "↑By Z":
          sortedData.sort((a, b) => b.lastname.localeCompare(a.lastname));
          break;
        default:
          break;
      }
    } else {
      sortedData.sort((a, b) => a.studentId - b.studentId);
    }

    setFilteredData(
      sortedData.filter((item) =>
        keyWordToFilter.toLowerCase() === ""
          ? true
          : item.firstname
              .toLowerCase()
              .includes(keyWordToFilter.trim().toLowerCase()) ||
            item.lastname
              .toLowerCase()
              .includes(keyWordToFilter.trim().toLowerCase()) ||
            item.studentId === Number(keyWordToFilter)
      )
    );
  }, [filterByName, filterByAttend, keyWordToFilter, etudiantList]);
  return (
    <div className="container-tab">
      <ToastContainer />
      <TableContainer
        component={Paper}
        style={{ width: "1400px", maxHeight: 700 }}
      >
        <Table aria-label="collapsible table">
          <TableHead>
            <TableRow>
              <TableCell />
              <CustomTableCell>Nº</CustomTableCell>
              <CustomTableCell>Lastname</CustomTableCell>
              <CustomTableCell>Firstname</CustomTableCell>
              <CustomTableCell>Address</CustomTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredData.map((etudiant) => (
              <StundentRow
                key={etudiant.studentId}
                etudiant={etudiant}
                filterByDate={filterByDate}
                filterByAttend={filterByAttend}
              />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}
export default TableData;
