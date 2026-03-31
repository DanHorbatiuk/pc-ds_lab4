echo "=============================="
echo "ARCHIVE SERVICE TEST START"
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
echo "[3] CREATE ARCHIVE RECORD"
curl -X POST http://localhost:8085/archive \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1,
    "teacherId": 1,
    "grade": 95
  }'

echo
echo
echo "[4] GET ALL ARCHIVE RECORDS"
curl http://localhost:8085/archive

echo
echo
echo "[5] GET ARCHIVE RECORD BY ID = 1"
curl http://localhost:8085/archive/1

echo
echo
echo "[6] UPDATE ARCHIVE RECORD ID = 1"
curl -X PUT http://localhost:8085/archive/1 \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1,
    "teacherId": 1,
    "grade": 100
  }'

echo
echo
echo "[7] NEGATIVE TEST: INVALID GRADE"
curl -X POST http://localhost:8085/archive \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1,
    "teacherId": 1,
    "grade": 150
  }'

echo
echo
echo "[8] NEGATIVE TEST: INVALID STUDENT"
curl -X POST http://localhost:8085/archive \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 99,
    "courseId": 1,
    "teacherId": 1,
    "grade": 90
  }'

echo
echo
echo "[9] NEGATIVE TEST: INVALID TEACHER"
curl -X POST http://localhost:8085/archive \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1,
    "teacherId": 99,
    "grade": 90
  }'

echo
echo
echo "[10] NEGATIVE TEST: INVALID COURSE"
curl -X POST http://localhost:8085/archive \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 99,
    "teacherId": 1,
    "grade": 90
  }'

echo
echo
echo "[11] DELETE ARCHIVE RECORD ID = 1"
curl -X DELETE http://localhost:8085/archive/1

echo
echo
echo "[12] GET ALL ARCHIVE AFTER DELETE"
curl http://localhost:8085/archive

echo
