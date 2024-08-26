import React, { useEffect, useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import styled from "@emotion/styled";
import Paper from "@mui/material/Paper";
import Etudiant from "../../models/Etudiant";
import fecthStudent from "../../fecthAPI/FetchStudents";
import StudentRowForModal from "./StudentRowForModal";

// Créez des composants stylés pour TableCell
const CustomTableCell = styled(TableCell)(({ theme }) => ({
  fontSize: "1.1rem",
}));

function TableDataModal() {
  const [etudiantList, setEtudiant] = useState<Etudiant[]>([]);
  const [filteredData, setFilteredData] = useState<Etudiant[]>([]);
  fecthStudent(etudiantList, setEtudiant);
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
              <CustomTableCell>Roll no</CustomTableCell>
              <CustomTableCell>Lastname</CustomTableCell>
              <CustomTableCell>Firstname</CustomTableCell>
              <CustomTableCell>Address</CustomTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {etudiantList.map((etudiant) => (
              <StudentRowForModal
                key={etudiant.studentId}
                etudiant={etudiant}
              />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}
export default TableDataModal;
