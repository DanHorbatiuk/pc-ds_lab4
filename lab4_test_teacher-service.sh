echo "=============================="
echo "TEACHER SERVICE TEST START"
echo "=============================="

echo
echo "[1] CREATE TEACHER"
curl -X POST http://localhost:8082/teachers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Ivan Petrenko",
    "department": "Computer Science",
    "email": "ivan.petrenko@university.edu"
  }'

echo
echo
echo "[2] GET ALL TEACHERS"
curl http://localhost:8082/teachers

echo
echo
echo "[3] GET TEACHER BY ID = 1"
curl http://localhost:8082/teachers/1

echo
echo
echo "[4] UPDATE TEACHER ID = 1"
curl -X PUT http://localhost:8082/teachers/1 \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Ivan Petrenko Updated",
    "department": "Distributed Systems",
    "email": "ivan.updated@university.edu"
  }'

echo
echo
echo "[5] GET UPDATED TEACHER BY ID = 1"
curl http://localhost:8082/teachers/1

echo
echo
echo "[6] DELETE TEACHER ID = 1"
curl -X DELETE http://localhost:8082/teachers/1

echo
echo
echo "[7] GET ALL TEACHERS AFTER DELETE"
curl http://localhost:8082/teachers

echo
