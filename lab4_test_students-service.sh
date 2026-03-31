echo "=============================="
echo "STUDENT SERVICE TEST START"
echo "=============================="

echo
echo "[1] CREATE STUDENT"
curl -X POST http://localhost:8081/students \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Danylo Horbatiuk",
    "groupName": "OI-21SP",
    "email": "danylo@student.edu"
  }'

echo
echo
echo "[2] GET ALL STUDENTS"
curl http://localhost:8081/students

echo
echo
echo "[3] GET STUDENT BY ID = 1"
curl http://localhost:8081/students/1

echo
echo
echo "[4] UPDATE STUDENT ID = 1"
curl -X PUT http://localhost:8081/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Danylo Horbatiuk Updated",
    "groupName": "OI-22SP",
    "email": "danylo.updated@student.edu"
  }'

echo
echo
echo "[5] GET UPDATED STUDENT BY ID = 1"
curl http://localhost:8081/students/1

echo
echo
echo "[6] DELETE STUDENT ID = 1"
curl -X DELETE http://localhost:8081/students/1

echo
echo
echo "[7] GET ALL STUDENTS AFTER DELETE"
curl http://localhost:8081/students
