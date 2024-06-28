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

Chart.register(
    BarElement,
    CategoryScale, // x
    LinearScale, // y
    Tooltip,
    Legend
);

function AttendancePerSex() {
    const data = {
        labels: ['M', 'F'],
        datasets: [{
            label: 'Attendance',
            data: [95, 80],
            backgroundColor: ['#257DE4', '#FBB7F4'],
            barThickness: 60,
            maxBarThickness: 60,
        }]
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
