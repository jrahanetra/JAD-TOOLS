import csv
import cv2
import face_recognition as fr
import datetime as dt

import numpy as np

esp32_cam_url = 'http://192.168.167.95'
video_capture = cv2.VideoCapture(esp32_cam_url)

jason_image = fr.load_image_file("image/Jason.jpg")
jason_encoding = fr.face_encodings(jason_image)[0]

antsa_image = fr.load_image_file("image/Antsa.jpg")
antsa_encoding = fr.face_encodings(antsa_image)[0]

known_faces_encoding = [
    jason_encoding,
    antsa_encoding,
]

known_faces_names = [
    "jason",
    "antsa",
]

soccer = known_faces_names.copy()

face_locations = []
face_encodings = []
face_names = []
s = True

now = dt.datetime.now()
current_date = now.strftime("%Y-%m-%d")

f = open(current_date+ ".csv", 'w+',newline= '')
lnwriter = csv.writer(f)

while True :
    newNow = dt.datetime.now()
    ret, frame = video_capture.read()
    if not ret:
        print("Failed to grab frame")
        break

    small_frame = cv2.resize(frame,(0,0), fx=0.25, fy=0.25)
    rgb_small_frame = np.ascontiguousarray(small_frame[:, :, ::-1])
    if s:
        face_locations = fr.face_locations(rgb_small_frame)
        face_encodings = fr.face_encodings(rgb_small_frame,face_locations)
        face_names = []
        for face_encoding in face_encodings :
            matches = fr.compare_faces(known_faces_encoding, face_encoding)
            name = ""
            face_distance = fr.face_distance(known_faces_encoding, face_encoding)
            best_match_index = np.argmin(face_distance)
            if matches[best_match_index]:
                name = known_faces_names[best_match_index]

            face_names.append(name)
            if name in known_faces_names:
                if name in soccer:
                    soccer.remove(name)
                    print(soccer)
                    current_time = newNow.strftime("%H-%M-%S")
                    lnwriter.writerow([name,current_time])
    cv2.imshow("attendance system", frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

video_capture.release()
cv2.destroyAllWindows()
f.close()