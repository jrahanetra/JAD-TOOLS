import React, { useState } from "react";
import BoxDash from "./Common";
import VectorYear from "../../../assets/vectorYear";
import VectorStudent from "../../../assets/vectorStudent";
import VectorWoman from "../../../assets/vectorWoman";
import VectorMan from "../../../assets/vectorMan";
import StudentCustomizeData from "../../../models/StudentCustomizeData";
import Level from "../../../models/Level";
import fecthRegistration from "../../../fecthAPI/FetchRegistrations";
import fecthLevel from "../../../fecthAPI/FetchLevels";

function ShowAllDash() {
  const [studentCustomizeData, setCustomizeData] = useState<
    StudentCustomizeData[]
  >([]);
  const [studentLevels, setLevels] = useState<Level[]>([]);
  const [nbF, setNbF] = useState<number>(0);
  const [nbM, setNbM] = useState<number>(0);

  fecthRegistration(studentCustomizeData, setCustomizeData);
  fecthLevel(studentLevels, setLevels);

  let tempNbF = 0;
  let tempNbM = 0;
  studentCustomizeData.forEach((student) => {
    if (student.studentDto.sex === "Female") {
      tempNbF += 1;
    } else {
      tempNbM += 1;
    }
  });
  return (
    <div className="div-dashs-container">
      <BoxDash
        name="Level"
        number={studentLevels.length}
        color="#22BBEA"
        vector={VectorYear}
      />
      <BoxDash
        name="Students"
        number={studentCustomizeData.length}
        color="#CF2E2E"
        vector={VectorStudent}
      />
      <BoxDash
        name="Female"
        number={tempNbF}
        color="#22BBEA"
        vector={VectorWoman}
      />
      <BoxDash
        name="Male"
        number={tempNbM}
        color="#FF9933"
        vector={VectorMan}
      />
    </div>
  );
}

export default ShowAllDash;
