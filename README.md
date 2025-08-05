# 📚 Course Management System (Java GUI + RMI)

This is a **Course Management System** built using **Java GUI (Swing)** that utilizes **Java RMI (Remote Method Invocation)** to separate server and client logic. The system allows an administrator to manage students and instructors, while instructors can manage courses and student enrollments. Email notifications are sent to users for updates and confirmations.

---

## 🚀 Features

- ✅ Admin:
  - Register students and instructors
  - Assign students to instructors
- ✅ Instructor:
  - Create new courses with details
  - Assign students to their courses
- ✅ Email Notifications:
  - Sends notifications to instructors and admins using JavaMail
- ✅ Remote Architecture:
  - Java RMI used to separate server and client logic
- ✅ Database:
  - MySQL integration for data persistence

---

## 🛠️ Tech Stack

- Java Swing (GUI)
- Java RMI
- MySQL
- JavaMail (javax.mail + activation)
- IntelliJ IDEA (Project structure)

---

## 🗃️ Project Structure
CourseManagementSystem/
│
├── .idea/
│ └── libraries/
│ ├── activation.xml
│ ├── javax.mail.xml
│ └── mysql-connector-j-8.1.0.xml
│
├── src/
│ └── DriverPackage/
│ └── DBconnection.java
│
├── coursemanagementsystem.iml
└── README.md

---

## 📦 Dependencies

The project uses the following JAR files (located in `.idea/libraries/`):

- `activation.jar`
- `javax.mail.jar`
- `mysql-connector-j-8.1.0.jar`

Make sure you have these dependencies configured in your classpath or module settings in IntelliJ.

---

## ⚙️ Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/course-management-system.git
2. Open in IntelliJ IDEA

3. Set Up MySQL Database

    Create a database named coursemanagementsystem

    Adjust DB credentials if necessary in DBconnection.java
4. Run RMI Server

     Compile and run the server-side code
5. Run Client

      Run the main client GUI class from IntelliJ or your terminal
6. Test Functionality

      Register users, assign courses, and check email notifications

✉️ Email Notification Setup
    To use the email notification feature, ensure:
    
        You configure sender email credentials inside the JavaMail setup inside the src/EmailSender.java
        
        Allow "less secure app access" or generate an App Password if you're using Gmail or similar services

👤 Author
Amanuel Fentahun
BSc in Software Engineering, 2025 Graduate
