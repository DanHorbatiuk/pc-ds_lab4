echo "=============================="
echo "COURSE SERVICE TEST START"
echo "=============================="

echo
echo "[0] CREATE TEACHER IN TEACHER SERVICE"
curl -X POST http://localhost:8082/teachers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Ivan Petrenko",
    "department": "Computer Science",
    "email": "ivan.petrenko@university.edu"
  }'

echo
echo
echo "[1] CREATE COURSE"
curl -X POST http://localhost:8083/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Parallel Computing",
    "credits": 5,
    "teacherId": 1
  }'

echo
echo
echo "[2] GET ALL COURSES"
curl http://localhost:8083/courses

echo
echo
echo "[3] GET COURSE BY ID = 1"
curl http://localhost:8083/courses/1

echo
echo
echo "[4] UPDATE COURSE ID = 1"
curl -X PUT http://localhost:8083/courses/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Parallel Computing Advanced",
    "credits": 6,
    "teacherId": 1
  }'

echo
echo
echo "[5] GET UPDATED COURSE BY ID = 1"
curl http://localhost:8083/courses/1

echo
echo
echo "[6] NEGATIVE TEST: INVALID TEACHER"
curl -X POST http://localhost:8083/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Invalid Course",
    "credits": 3,
    "teacherId": 99
  }'

echo
echo
echo "[7] DELETE COURSE ID = 1"
curl -X DELETE http://localhost:8083/courses/1

echo
echo
echo "[8] GET ALL COURSES AFTER DELETE"
curl http://localhost:8083/courses

echo
