# Student mentoring- app using Spring Boot and JWT

This repo hosts the source code for student assesment app.

It has three roles- Student, Mentor and Admin
* Student: Can submit an assessment to particular mentor.
* Mentor: Can grade an assessment.
* Admin: Can add/edit/delete, assessment, users, submissions and grades.
* Mentor/Admin can see all submissions
* Student can see only their submission

## Requirements

JRE 1.8, Gradle 6.8.1+, PostgreSQL 10.0+

## Geting Started
You have to authenticate first to access other api. You have to use "/authenticate" endpoint is to generate jwt token.
For this run the following curl command using "/authenticate" endpoint. You will get
a token.
```
curl --request POST -H "Content-Type: application/json" --data '{"userId":"admin","password":"asdf12"}' --url "http://localhost:8080/authenticate"

{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1MDIzMDY3OSwiaWF0IjoxNjUwMjEyNjc5fQ.oxHC8rkdfIg_JTDp6MSAIQ_Ba-5f4TmA2RwBrQd4g9irKA63FqHc2O2nL5AqIVVBMQxeFixCJkLJnL8g6BIHag"
}

```
You have to use token with the Authorization Bearer for other api call.

To get Assesment list you have to use the curl on terminal or import the curl into your Postman

```
curl --request GET \
  --url 'http://localhost:8080/v1/assesment/list' \
  --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1MDIzMDY3OSwiaWF0IjoxNjUwMjEyNjc5fQ.oxHC8rkdfIg_JTDp6MSAIQ_Ba-5f4TmA2RwBrQd4g9irKA63FqHc2O2nL5AqIVVBMQxeFixCJkLJnL8g6BIHag' \
  
```
You will get the following response
```
[
    {
        "oid": "1111",
        "title": "JavaScript Basic Problem solving",
        "description": "1. Problem 1, 2. Problem 2, 3. Problem 3",
        "mentorId": "99",
        "deadline": "2022-05-16T06:27:56.987+00:00",
        "createdBy": "Admin",
        "createdOn": "2022-04-17T05:31:09.676+00:00",
        "updatedBy": null,
        "updatedOn": null
    }
]
```
Use the following curl to get Assesment submission

```
curl --request GET \
  --url 'http://localhost:8080/v1/assesmentsubmission/' \
  --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1MDIzMDY3OSwiaWF0IjoxNjUwMjEyNjc5fQ.oxHC8rkdfIg_JTDp6MSAIQ_Ba-5f4TmA2RwBrQd4g9irKA63FqHc2O2nL5AqIVVBMQxeFixCJkLJnL8g6BIHag' \
  
```
You will get the following response
```
{
    "data": [
        {
            "assesmentOid": "1111",
            "assesmentTitle": "JavaScript Basic Problem solving",
            "description": "1. Problem 1, 2. Problem 2, 3. Problem 3",
            "mentorName": "Kayn Rich",
            "mentorId": "99",
            "deadline": "2022-05-16T06:27:56.987+00:00",
            "studentId": "100",
            "studentName": "Istiyak Ahamed Milon",
            "submissionOid": "3333",
            "filePath": "abc.pdf",
            "submissionDate": "2022-04-16T06:27:05.000+00:00"
        }
    ]
}

```
