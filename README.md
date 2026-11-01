# Online Quiz Portal - 
Spring Boot & JUnit 5 (STQA Coursework)A robust, production-ready RESTful web service built with Java Spring Boot 3.x, designed for online quiz administration, auto-grading, and comprehensive unit testing. 
Built as part of Software Testing & Quality Assurance (STQA) and Object-Oriented Modelling & Design (OOMD) coursework.

# 🚀 Features
Quiz Management & Retrieval: Restful API endpoints for fetching active quizzes and individual question sets.
Automated Grading Engine: Instant evaluation of student submissions with percentage scoring and Pass/Fail status determination.
Data Seeding (DataLoader): Automatic pre-population of sample STQA quiz questions on startup via Spring Boot's CommandLineRunner.
CORS Enabled: Fully configured to accept cross-origin requests from frontend client applications.
High-Coverage Test Suite: 25+ comprehensive JUnit 5 test cases applying Boundary Value Analysis (BVA), Equivalence Partitioning, and Parameterized testing.

# 🛠️ Tech StackLanguage: 
Java 17Framework: Spring Boot 3.x (Spring Web, Spring Data JPA)
Database: H2 In-Memory Database
Testing Framework: JUnit 5 (Jupiter), @Nested tests, @ParameterizedTest
Build Tool: Apache Maven
Deployment Target: Render.com

# 📂 Project Architecture & Class DiagramThe application strictly adheres to the Layered Architectural Pattern (Separation of Concerns):
com.quiz
 ├── controller
 │    └── QuizController.java      # REST Endpoints for client interaction
 ├── model
 │    ├── Quiz.java                # Quiz Entity (1:N with Question)
 │    └── Question.java            # Question Entity with choices & correct option
 ├── repository
 │    └── QuizRepository.java      # Spring Data JPA Repository interface
 ├── service
 │    └── QuizService.java         # Core Business Logic & Grading logic
 ├── DataLoader.java               # Database Seeder (CommandLineRunner)
 └── QuizApplication.java          # Spring Boot Main Entry Point
🧪 STQA Testing Suite Highlights
The test suite located in src/test/java/com/quiz/QuizServiceTest.java contains 25 test cases (executing across 27 total test runs) divided into 5 distinct categories:
Score & Percentage Calculations: Standard calculation, perfect score, zero score, precision rounding, division by zero guard.
Pass/Fail Thresholds & BVA: Boundary testing at 50.0% (Pass threshold) vs 49.9% (Fail boundary), upper (100%) & lower (0%) boundaries.
Input & Data Validation: Range checks ($0 \le score \le 100$), negative score checks, parameterized non-empty title verification (@ParameterizedTest).
Formatting Logic: String trimming, certificate ID generation, result string formatting.
Business Rules & Entity Logic: Time limit exhaustion, duration verification, option bounds checks.

# ⚙️ How to Run the Application
Prerequisites
JDK 17 or higherApache Maven (or use embedded mvnw wrapper)
Running the AppOpen terminal in the project root directory.
Execute:mvn spring-boot:run
The server will start at http://localhost:3000.
Running the Test SuiteTo run all 25+ JUnit 5 test cases and view test coverage reports:
mvn test

# 🔗 REST API EndpointsHTTP MethodEndpointDescription
GET/api/quizzesFetch all available active quizzesGET/api/quizzes/{id}
Fetch quiz details by IDPOST/api/quizzes/{id}/submit
Submit quiz answers and return score summary
Sample Submission Request (POST /api/quizzes/1/submit){
  "1": "A",
  "2": "B"
}
Sample Result Response{
  "quizId": 1,
  "totalQuestions": 2,
  "correctAnswers": 2,
  "percentage": 100.0,
  "status": "PASSED"
}
