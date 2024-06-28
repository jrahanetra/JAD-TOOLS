export default class Etudiant{
     // 1. Typage des propiétés d'un étudiant.
        id: number;
        address: string;
        email: string;
        firstname: string;
        lastname: string;
        phoneNumber: string;
        
        // 2. Définition des valeurs par défaut des propriétés d'un pokémon.
        constructor(
            id: number,
            address: string = 'addresse',
            email: string = 'name@gmail.com',
            firstname: string = 'firstname',
            lastname: string = 'lastname',
            phoneNumber: string = '034 11 185 11'
        ) {
            // 3. Initialisation des propiétés d'un pokémons.
            this.id = id;
            this.address = address;
            this.email = email;
            this.firstname = firstname;
            this.lastname = lastname,
            this.phoneNumber = phoneNumber;
        }
}