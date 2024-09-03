import React from "react";
import { 
    Chart,
    BarElement,
    CategoryScale, // x
    LinearScale, // y
    Tooltip,
    Legend
} from "chart.js";
import { Bar } from "react-chartjs-2";

type Props = {
    nbM: number
    nbF: number
}
Chart.register(
    BarElement,
    CategoryScale, // x
    LinearScale, // y
    Tooltip,
    Legend
);

function AttendancePerSex({nbM, nbF}: Props) {
    const data = {
      labels: ["M", "F"],
      datasets: [
        {
          label: "Attendance",
          data: [nbM, nbF],
          backgroundColor: ["#b44593", "#FF9933"],
          barThickness: 60,
          maxBarThickness: 60,
        },
      ],
    };

    const options = {
        layout: {
            padding: {
                left: 5,
                right: 240,
            }
        },
        scales: {
            x: {
                grid: {
                    display: false
                }
            },
            y: {
                grid: {
                    display: false
                },
                beginAtZero: true
            }
        },
        plugins: {
            legend: {
                display: true,
            }
        },
        maintainAspectRatio: false
    };

    return (
        <div className="container-bar-attendancePerSex">
            <div className="container-charts-graph">
                <Bar
                    data={data}
                    options={options}
                />
            </div>
            <h2>Attendance Per Sex</h2>
        </div>
    );
}

export default AttendancePerSex;
