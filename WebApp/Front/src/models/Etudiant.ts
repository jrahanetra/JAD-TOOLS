export default class Etudiant {
  // 1. Typage des propiétés d'un étudiant.
  studentId: number;
  address: string;
  email: string;
  firstname: string;
  lastname: string;
  phoneNumber: string;
  sex: string;
  birthday: string;
  imageName: string;

  // 2. Définition des valeurs par défaut .
  constructor(
    id: number,
    address: string = "addresse",
    email: string = "name@gmail.com",
    firstname: string = "firstname",
    lastname: string = "lastname",
    phoneNumber: string = "034 11 185 11",
    sex: string = "",
    birthday: string = "",
    imageName: string = ""
  ) {
    // 3. Initialisation des propiétés d'un pokémons.
    this.studentId = id;
    this.address = address;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.sex = sex;
    this.birthday = birthday;
    this.imageName = imageName;
  }
}
