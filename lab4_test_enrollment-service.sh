echo "=============================="
echo "ENROLLMENT SERVICE TEST START"
echo "=============================="

echo
echo "[0] CREATE STUDENT"
curl -X POST http://localhost:8081/students \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Danylo Horbatiuk",
    "groupName": "OI-21SP",
    "email": "danylo@student.edu"
  }'

echo
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
echo "[2] CREATE COURSE"
curl -X POST http://localhost:8083/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Parallel Computing",
    "credits": 5,
    "teacherId": 1
  }'

echo
echo
echo "[3] CREATE ENROLLMENT"
curl -X POST http://localhost:8084/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1
  }'

echo
echo
echo "[4] GET ALL ENROLLMENTS"
curl http://localhost:8084/enrollments

echo
echo
echo "[5] GET ENROLLMENT BY ID = 1"
curl http://localhost:8084/enrollments/1

echo
echo
echo "[6] NEGATIVE TEST: DUPLICATE ENROLLMENT"
curl -X POST http://localhost:8084/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1
  }'

echo
echo
echo "[7] NEGATIVE TEST: INVALID STUDENT"
curl -X POST http://localhost:8084/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 99,
    "courseId": 1
  }'

echo
echo
echo "[8] NEGATIVE TEST: INVALID COURSE"
curl -X POST http://localhost:8084/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 99
  }'

echo
echo
echo "[9] UPDATE ENROLLMENT ID = 1"
curl -X PUT http://localhost:8084/enrollments/1 \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1
  }'

echo
echo
echo "[10] DELETE ENROLLMENT ID = 1"
curl -X DELETE http://localhost:8084/enrollments/1

echo
echo
echo "[11] GET ALL ENROLLMENTS AFTER DELETE"
curl http://localhost:8084/enrollments

echo
