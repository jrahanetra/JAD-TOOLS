import React from "react";
import ShowAllDash from "../components/Report/DashReport/AllDash";
import AttendanceGlobal from "../components/Report/ChartsReport/AttendanceGlobal";
import AttendancePerSex from "../components/Report/ChartsReport/AttendancePerSex";
import calculatePresence from "../utils/CalculFreqPresPerSex";
import calculatePresencePerLevel from "../utils/CalculFreqPresPerLevel";

type Props = {
  nbFreqM: number;
  nbFreqF: number;
  nbFreqL1: number;
  nbFreqL2: number;
};
function Graph({ nbFreqM, nbFreqF, nbFreqL1, nbFreqL2 }: Props) {
  return (
    <div className="container-graph">
      <AttendancePerSex nbM={nbFreqM} nbF={nbFreqF} />
      <AttendanceGlobal nbL1={nbFreqL1} nbL2={nbFreqL2} />
    </div>
  );
}

function DisplayReport() {
  const freqNbM = calculatePresence("Masculin") * 100;
  const freqNbF = calculatePresence("Feminin") * 100;
  const freqNbL1 = calculatePresencePerLevel("L1") * 100;
  const freqNbFL2 = calculatePresencePerLevel("L2") * 100;

  return (
    <div className="container-attend">
      <div className="container-title">
        <h1>Report</h1>
      </div>
      <div className="container-report">
        <ShowAllDash />
        <Graph nbFreqM={freqNbM} nbFreqF={freqNbF} nbFreqL1={freqNbL1} nbFreqL2={freqNbFL2} />
      </div>
    </div>
  );
}

export default DisplayReport;
