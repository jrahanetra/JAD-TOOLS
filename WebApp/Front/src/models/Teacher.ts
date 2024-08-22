export default class Teacher{
    teacherID: number;
    firstname: string;
    lastname: string;

    constructor(
        id: number,
        firstname: string = "",
        lastname: string = ""
    ){
        this.teacherID = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}