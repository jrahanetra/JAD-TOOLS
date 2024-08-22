import Etudiant from "./Etudiant";
import Level from "./Level";
import Parcours from "./Parcours";

export default class StudentCustomizeData{
    studentDto: Etudiant;
    majorDto: Parcours;
    levelDto: Level;
    year: number;

    constructor(
        studentDto: Etudiant,
        majorDto: Parcours,
        levelDto: Level,
        year: number
    ){
        this.studentDto = studentDto,
        this.majorDto = majorDto,
        this.levelDto = levelDto,
        this.year = year
    }
}