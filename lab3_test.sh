#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=================================================="
echo "LAB TEST START: RESTful web service"
echo "=================================================="

echo
echo "1) CREATE TEACHER #1"
curl -s -X POST "$BASE_URL/teachers" \
-H "Content-Type: application/json" \
-d '{
  "fullName":"Ivan Petrenko",
  "department":"Computer Science",
  "email":"ivan.petrenko@university.edu"
}'
echo -e "\n"

echo "2) CREATE TEACHER #2"
curl -s -X POST "$BASE_URL/teachers" \
-H "Content-Type: application/json" \
-d '{
  "fullName":"Olena Shevchenko",
  "department":"Software Engineering",
  "email":"olena.shevchenko@university.edu"
}'
echo -e "\n"

echo "3) GET ALL TEACHERS"
curl -s "$BASE_URL/teachers"
echo -e "\n"

echo "4) GET TEACHER BY ID = 1"
curl -s "$BASE_URL/teachers/1"
echo -e "\n"

echo "5) UPDATE TEACHER ID = 1"
curl -s -X PUT "$BASE_URL/teachers/1" \
-H "Content-Type: application/json" \
-d '{
  "fullName":"Ivan Petrenko Updated",
  "department":"Distributed Systems",
  "email":"ivan.petrenko.updated@university.edu"
}'
echo -e "\n"

echo "6) GET UPDATED TEACHER ID = 1"
curl -s "$BASE_URL/teachers/1"
echo -e "\n"

echo "7) CREATE STUDENT #1"
curl -s -X POST "$BASE_URL/students" \
-H "Content-Type: application/json" \
-d '{
  "fullName":"Danylo Horbatiuk",
  "groupName":"OI-21SP",
  "email":"danylo@student.edu"
}'
echo -e "\n"

echo "8) CREATE STUDENT #2"
curl -s -X POST "$BASE_URL/students" \
-H "Content-Type: application/json" \
-d '{
  "fullName":"Andrii Melnyk",
  "groupName":"OI-21SP",
  "email":"andrii@student.edu"
}'
echo -e "\n"

echo "9) GET ALL STUDENTS"
curl -s "$BASE_URL/students"
echo -e "\n"

echo "10) GET STUDENT BY ID = 1"
curl -s "$BASE_URL/students/1"
echo -e "\n"

echo "11) UPDATE STUDENT ID = 1"
curl -s -X PUT "$BASE_URL/students/1" \
-H "Content-Type: application/json" \
-d '{
  "fullName":"Danylo Horbatiuk Updated",
  "groupName":"OI-22SP",
  "email":"danylo.updated@student.edu"
}'
echo -e "\n"

echo "12) GET UPDATED STUDENT ID = 1"
curl -s "$BASE_URL/students/1"
echo -e "\n"

echo "13) CREATE COURSE #1"
curl -s -X POST "$BASE_URL/courses" \
-H "Content-Type: application/json" \
-d '{
  "title":"Parallel Computing",
  "credits":5,
  "teacherId":1
}'
echo -e "\n"

echo "14) CREATE COURSE #2"
curl -s -X POST "$BASE_URL/courses" \
-H "Content-Type: application/json" \
-d '{
  "title":"Distributed Systems",
  "credits":4,
  "teacherId":2
}'
echo -e "\n"

echo "15) GET ALL COURSES"
curl -s "$BASE_URL/courses"
echo -e "\n"

echo "16) GET COURSE BY ID = 1"
curl -s "$BASE_URL/courses/1"
echo -e "\n"

echo "17) UPDATE COURSE ID = 1"
curl -s -X PUT "$BASE_URL/courses/1" \
-H "Content-Type: application/json" \
-d '{
  "title":"Parallel Computing Advanced",
  "credits":6,
  "teacherId":1
}'
echo -e "\n"

echo "18) GET UPDATED COURSE ID = 1"
curl -s "$BASE_URL/courses/1"
echo -e "\n"

echo "19) NEGATIVE TEST: CREATE COURSE WITH INVALID TEACHER"
curl -s -X POST "$BASE_URL/courses" \
-H "Content-Type: application/json" \
-d '{
  "title":"Invalid Course",
  "credits":3,
  "teacherId":99
}'
echo -e "\n"

echo "20) CREATE ENROLLMENT #1"
curl -s -X POST "$BASE_URL/enrollments" \
-H "Content-Type: application/json" \
-d '{
  "studentId":1,
  "courseId":1
}'
echo -e "\n"

echo "21) CREATE ENROLLMENT #2"
curl -s -X POST "$BASE_URL/enrollments" \
-H "Content-Type: application/json" \
-d '{
  "studentId":2,
  "courseId":2
}'
echo -e "\n"

echo "22) GET ALL ENROLLMENTS"
curl -s "$BASE_URL/enrollments"
echo -e "\n"

echo "23) GET ENROLLMENT BY ID = 1"
curl -s "$BASE_URL/enrollments/1"
echo -e "\n"

echo "24) UPDATE ENROLLMENT ID = 1"
curl -s -X PUT "$BASE_URL/enrollments/1" \
-H "Content-Type: application/json" \
-d '{
  "studentId":1,
  "courseId":2
}'
echo -e "\n"

echo "25) GET UPDATED ENROLLMENT ID = 1"
curl -s "$BASE_URL/enrollments/1"
echo -e "\n"

echo "26) NEGATIVE TEST: DUPLICATE ENROLLMENT"
curl -s -X POST "$BASE_URL/enrollments" \
-H "Content-Type: application/json" \
-d '{
  "studentId":1,
  "courseId":2
}'
echo -e "\n"

echo "27) NEGATIVE TEST: ENROLLMENT WITH INVALID STUDENT"
curl -s -X POST "$BASE_URL/enrollments" \
-H "Content-Type: application/json" \
-d '{
  "studentId":99,
  "courseId":1
}'
echo -e "\n"

echo "28) NEGATIVE TEST: ENROLLMENT WITH INVALID COURSE"
curl -s -X POST "$BASE_URL/enrollments" \
-H "Content-Type: application/json" \
-d '{
  "studentId":1,
  "courseId":99
}'
echo -e "\n"

echo "29) CREATE ARCHIVE RECORD #1"
curl -s -X POST "$BASE_URL/archive" \
-H "Content-Type: application/json" \
-d '{
  "studentId":1,
  "courseId":1,
  "teacherId":1,
  "grade":95
}'
echo -e "\n"

echo "30) CREATE ARCHIVE RECORD #2"
curl -s -X POST "$BASE_URL/archive" \
-H "Content-Type: application/json" \
-d '{
  "studentId":2,
  "courseId":2,
  "teacherId":2,
  "grade":88
}'
echo -e "\n"

echo "31) GET ALL ARCHIVE RECORDS"
curl -s "$BASE_URL/archive"
echo -e "\n"

echo "32) GET ARCHIVE RECORD BY ID = 1"
curl -s "$BASE_URL/archive/1"
echo -e "\n"

echo "33) UPDATE ARCHIVE RECORD ID = 1"
curl -s -X PUT "$BASE_URL/archive/1" \
-H "Content-Type: application/json" \
-d '{
  "studentId":1,
  "courseId":1,
  "teacherId":1,
  "grade":100
}'
echo -e "\n"

echo "34) GET UPDATED ARCHIVE RECORD ID = 1"
curl -s "$BASE_URL/archive/1"
echo -e "\n"

echo "35) NEGATIVE TEST: ARCHIVE INVALID GRADE"
curl -s -X POST "$BASE_URL/archive" \
-H "Content-Type: application/json" \
-d '{
  "studentId":1,
  "courseId":1,
  "teacherId":1,
  "grade":150
}'
echo -e "\n"

echo "36) NEGATIVE TEST: ARCHIVE INVALID STUDENT"
curl -s -X POST "$BASE_URL/archive" \
-H "Content-Type: application/json" \
-d '{
  "studentId":99,
  "courseId":1,
  "teacherId":1,
  "grade":90
}'
echo -e "\n"

echo "37) DELETE ENROLLMENT ID = 2"
curl -s -X DELETE "$BASE_URL/enrollments/2" -w "\nHTTP STATUS: %{http_code}\n"
echo

echo "38) DELETE ARCHIVE RECORD ID = 2"
curl -s -X DELETE "$BASE_URL/archive/2" -w "\nHTTP STATUS: %{http_code}\n"
echo

echo "39) DELETE COURSE ID = 2"
curl -s -X DELETE "$BASE_URL/courses/2" -w "\nHTTP STATUS: %{http_code}\n"
echo

echo "40) DELETE STUDENT ID = 2"
curl -s -X DELETE "$BASE_URL/students/2" -w "\nHTTP STATUS: %{http_code}\n"
echo

echo "41) DELETE TEACHER ID = 2"
curl -s -X DELETE "$BASE_URL/teachers/2" -w "\nHTTP STATUS: %{http_code}\n"
echo

echo "42) FINAL GET ALL TEACHERS"
curl -s "$BASE_URL/teachers"
echo -e "\n"

echo "43) FINAL GET ALL STUDENTS"
curl -s "$BASE_URL/students"
echo -e "\n"

echo "44) FINAL GET ALL COURSES"
curl -s "$BASE_URL/courses"
echo -e "\n"

echo "45) FINAL GET ALL ENROLLMENTS"
curl -s "$BASE_URL/enrollments"
echo -e "\n"

echo "46) FINAL GET ALL ARCHIVE RECORDS"
curl -s "$BASE_URL/archive"
echo -e "\n"
