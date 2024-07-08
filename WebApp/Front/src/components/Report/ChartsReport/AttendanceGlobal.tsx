import React from 'react'
import {
    Chart,
    ArcElement,
    Tooltip,
    Legend,
} from 'chart.js'

import { Doughnut } from 'react-chartjs-2'

Chart.register(
    ArcElement,
    Tooltip,
    Legend,
)

function AttendanceGlobal(){
    const data = {
        labels: ['L1-1','L1-2','L2'],
        datasets: [{
            label: 'Poll',
            data: [3,6,2],
            backgroundColor : ['#DEE1F4' ,'#22BBEA' ,'#009DE1'],
            borderWidth: 0, // Thickness of the border
        }]
    }
    const options ={
        plugins: {
            legend: {
                display: true,
            },
            tooltip: {
                enabled: true,
            },
        },
        cutout: '70%', // Adjust the size of the cutout (hole in the middle)
        responsive: true,
        maintainAspectRatio: false,
        elements: {
            arc: {
                borderWidth: 10, // Adjust the border width for all arcs
            }
        }
    }
    return(
        <div className='container-doughnut'>
            <div className="container-bar-attendanceGlobal">
                <Doughnut
                    data={data}
                    options={options} />
                </div>
            <h2>Attendance Per </h2>
        </div>
    )
}
export default AttendanceGlobal;