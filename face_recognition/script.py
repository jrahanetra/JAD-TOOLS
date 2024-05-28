import csv
import cv2
import face_recognition as fr
import datetime as dt
import numpy as np
import threading

# URL de la caméra ESP32-CAM
esp32_cam_url = 'http://192.168.88.28:81/stream'
video_capture = cv2.VideoCapture(esp32_cam_url)

# Chargement des images de référence et encodage des visages
jason_image = fr.load_image_file("image/Jason.jpg")
jason_encoding = fr.face_encodings(jason_image)[0]

antsa_image = fr.load_image_file("image/Antsa.jpg")
antsa_encoding = fr.face_encodings(antsa_image)[0]

known_faces_encoding = [jason_encoding, antsa_encoding]
known_faces_names = ["Jason", "Antsa"]

# Liste pour suivre les présences
soccer = known_faces_names.copy()

# Initialisation des variables pour la détection de visages
face_locations = []
face_encodings = []
face_names = []
process_this_frame = True

# Date et fichier CSV pour enregistrer les présences
now = dt.datetime.now()
current_date = now.strftime("%Y-%m-%d")

# Utilisation d'un gestionnaire de contexte pour le fichier CSV
with open(current_date + ".csv", 'w+', newline='') as f:
    lnwriter = csv.writer(f)

    def process_frame():
        global face_locations, face_encodings, face_names, process_this_frame, soccer

        while True:
            ret, frame = video_capture.read()
            if not ret:
                print("Failed to grab frame")
                break

            # Redimensionnement de l'image pour accélérer le traitement
            small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
            rgb_small_frame = np.ascontiguousarray(small_frame[:, :, ::-1])

            if process_this_frame:
                face_locations = fr.face_locations(rgb_small_frame)
                face_encodings = fr.face_encodings(rgb_small_frame, face_locations)
                face_names = []

                for face_encoding in face_encodings:
                    matches = fr.compare_faces(known_faces_encoding, face_encoding)
                    name = ""
                    face_distance = fr.face_distance(known_faces_encoding, face_encoding)
                    best_match_index = np.argmin(face_distance)
                    if matches[best_match_index]:
                        name = known_faces_names[best_match_index]

                    face_names.append(name)

                    if name in known_faces_names and name in soccer:
                        soccer.remove(name)
                        print(soccer)
                        current_time = dt.datetime.now().strftime("%H-%M-%S")
                        lnwriter.writerow([name, current_time])

            process_this_frame = not process_this_frame

            # Affichage des résultats
            for (top, right, bottom, left), name in zip(face_locations, face_names):
                top *= 4
                right *= 4
                bottom *= 4
                left *= 4

                # Dessiner un cadre autour du visage
                cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)

                # Dessiner un label avec le nom du visage
                cv2.rectangle(frame, (left, bottom - 35), (right, bottom), (0, 0, 255), cv2.FILLED)
                font = cv2.FONT_HERSHEY_DUPLEX
                cv2.putText(frame, name, (left + 6, bottom - 6), font, 1.0, (255, 255, 255), 1)

            # Affichage de l'image
            cv2.imshow("Attendance System", frame)

            # Sortir de la boucle avec 'q'
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break

    # Démarrer le traitement dans un thread séparé
    processing_thread = threading.Thread(target=process_frame)
    processing_thread.start()
    processing_thread.join()

# Libération des ressources
video_capture.release()
cv2.destroyAllWindows()
