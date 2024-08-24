function formatToDate(dateString: string) {
  // Extraire le jour, le mois et l'année de la chaîne de caractères
  const [day, month, year] = dateString.split("-").map(Number);

  // Créer un objet Date
  const date = new Date(year, month - 1, day); // Le mois commence à 0 en JS

  // Formater la date en une date complète
  return date;
}

export default formatToDate;
