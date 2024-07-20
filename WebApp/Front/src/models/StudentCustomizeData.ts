import Etudiant from "./Etudiant";
import Level from "./Level";
import Parcours from "./Parcours";

export default class StudentCustomizeData{
    studentDto : Etudiant;
    majorDto: Parcours;
    levelDto: Level;
    year: Number;
    constructor(
        studentDto: Etudiant,
        majorDto: Parcours,
        levelDto: Level,
        year: Number
    ){
        this.studentDto = studentDto,
        this.majorDto = majorDto,
        this.levelDto = levelDto,
        this.year = year
    }
}