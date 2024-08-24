import DayEDT from "../models/DayEDT";

// Fonction pour trier le Map par les dates du plus récent au plus ancien
const sortDateMap = (map : Map<string, DayEDT[]>) => {
  const sortedEntries = Array.from(map.entries()).sort((a, b) => {
    const [dayA, monthA, yearA] = a[0].split("-").map(Number);
    const [dayB, monthB, yearB] = b[0].split("-").map(Number);

    const dateA = new Date(yearA, monthA - 1, dayA).getTime();
    const dateB = new Date(yearB, monthB - 1, dayB).getTime();

    // Trier du plus récent au plus ancien
    return dateA - dateB;
  });

  // Re-créer un Map avec les entrées triées
  return new Map(sortedEntries);
};

export default sortDateMap;