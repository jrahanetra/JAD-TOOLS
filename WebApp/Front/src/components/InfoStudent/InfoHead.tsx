import React, { useEffect, useState } from "react";
import { Chart, ArcElement, Tooltip, Legend } from "chart.js";
import ChartDataLabels from "chartjs-plugin-datalabels";
import { Doughnut } from "react-chartjs-2";
import Etudiant from "../../models/Etudiant";
import InfoTable from "./InfoTable";
import formatDate from "../../utils/FormateDate";

Chart.register(ArcElement, Tooltip, Legend, ChartDataLabels);

type Props = {
  std: Etudiant | undefined;
};

function InfoStudent({ std }: Props) {
  const [imageStudent, setImageStudent] = useState<string>();
  const [nbYes, setNbYes] = useState<number>(0);
  const [nbNo, setNbNo] = useState<number>(0);
  const data = {
    labels: ["Attend", "Absence"],
    datasets: [
      {
        label: "Poll",
        data: [nbYes, nbNo],
        backgroundColor: ["#ee7724", "#b44593"],
        borderWidth: 0, // Thickness of the border
      },
    ],
  };
  const options = {
    plugins: {
      legend: {
        display: true,
      },
      tooltip: {
        enabled: true,
      },
      doughnutlabel: {
        labels: [
          {
            text: "550",
            color: "black",
            font: {
              size: 10,
              weight: "600",
            },
          },
          {
            text: "total",
          },
        ],
      },
    },
    cutout: "70%", // Adjust the size of the cutout (hole in the middle)
    responsive: true,
    maintainAspectRatio: false,
    elements: {
      arc: {
        borderWidth: 10, // Adjust the border width for all arcs
      },
    },
  };
  useEffect(() => {
    if (std?.firstname === "Dihary") {
      setImageStudent(`${process.env.PUBLIC_URL}../pictures/Dihary.jpg`);
    } else if (std?.firstname === "Antsa") {
      setImageStudent(`${process.env.PUBLIC_URL}../pictures/Antsa.jpg`);
    } else if(std?.firstname === "Jason") {
      setImageStudent(`${process.env.PUBLIC_URL}../pictures/jason.jpg`);
    }else {
      setImageStudent(`${process.env.PUBLIC_URL}../pictures/etudiant.jpeg`);
    }
  }, [std]);

  return (
    <div className="container-list">
      <div className="container-head">
        <div className="container-picture">
          <div className="student-picture">
            <img src={imageStudent} alt="" className="profile-picture" />
          </div>
        </div>
        <div className="container-student-info">
          <div className="student-info">
            <div className="container-nomPrenom">
              <h2>
                <span className="nom">{std?.lastname}</span>
                <span className="nom">{std?.firstname}</span>
              </h2>
            </div>
            <p
              style={{ width: "100%" }}
              className="container-birthdayAndAddress"
            >
              {std && formatDate(std.birthday)}
            </p>
            <p className="container-Address">{std?.address}</p>
          </div>
        </div>
        <div className="doughnut">
          <Doughnut data={data} options={options} />
        </div>
      </div>
      <div className="div-container-tab">
        <InfoTable
          idStudent={std?.studentId}
          setNbYes={setNbYes}
          setNbNo={setNbNo}
        />
      </div>
    </div>
  );
}

export default InfoStudent;
