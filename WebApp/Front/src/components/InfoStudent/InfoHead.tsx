import React from "react";
import { Chart, ArcElement, Tooltip, Legend } from "chart.js";
import ChartDataLabels from "chartjs-plugin-datalabels";
import { Doughnut } from "react-chartjs-2";
import Etudiant from "../../models/Etudiant";
import InfoTable from "./InfoTable";

Chart.register(ArcElement, Tooltip, Legend, ChartDataLabels);

type Props = {
  std: Etudiant;
};

const data = {
  labels: ["Yes", "No"],
  datasets: [
    {
      label: "Poll",
      data: [80, 20],
      backgroundColor: ["#257DE4", "black"],
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
            weight: "bold",
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

function InfoStudent({ std }: Props) {
  return (
    <div className="container-list">
      <div className="container-head">
        <div className="container-picture">
          <div className="student-picture">
            <img
              src={`${process.env.PUBLIC_URL}../pictures/jason.jpg`}
              alt=""
              className="profile-picture"
            />
          </div>
        </div>
        <div className="container-student-info">
          <div className="student-info">
            <div className="container-nomPrenom">
              <h2>
                <span className="nom">{std.lastname}</span>
                <span className="prenom">{std.firstname}</span>
              </h2>
            </div>
            <p
              className="container-birthdayAndAddress"
              style={{ width: "100%" }}
            >
              Birthday <br />
              {std.address}
            </p>
          </div>
        </div>
        <div className="doughnut">
          <Doughnut data={data} options={options} />
        </div>
      </div>
      <InfoTable />
    </div>
  );
}

export default InfoStudent;
