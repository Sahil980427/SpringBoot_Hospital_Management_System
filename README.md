# SpringBoot_Hospital_Management_System
# 🏥 Hospital Management System (HMS)

A full-stack **Hospital Management System (HMS)** built with **Spring Boot**, **JWT Authentication**, and **Role-Based Access Control (RBAC)** to manage patients, doctors, appointments, consultations, and prescriptions.

---

## 🔐 Security Features

- ✅ **JWT Token Authentication**
- ✅ **Role-Based Access Control** (Admin, Doctor, Patient)
- ✅ **Password Encryption** using BCrypt
- ✅ Protected APIs with Spring Security
- ✅ Stateless session management with filters

---

## 📦 Tech Stack

- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Database**: MySQL
- **Security**: JWT, BCrypt
- **Frontend**: React.js + Bootstrap

---

## 👥 User Roles

| Role   | Access |
|--------|--------|
| Admin  | Manage doctors, patients, appointments, prescriptions |
| Doctor | View consultations, prescribe meds |
| Patient| Book appointments, view prescriptions |

---

## 📌 Endpoints Overview

| Method | Endpoint                  | Access  |
|--------|---------------------------|---------|
| POST   | `/auth/login`             | Public  |
| POST   | `/auth/register`          | Public  |
| GET    | `/api/admins/**`          | Admin   |
| GET    | `/api/doctors/**`         | Doctor  |
| GET    | `/api/patients/**`        | Patient |
| GET    | `/api/users`              | Admin   |
| CRUD   | `/api/appointments/**`    | Patient, Admin |
| CRUD   | `/api/consultations/**`   | Doctor, Admin |
| CRUD   | `/api/prescriptions/**`   | Doctor, Admin |

---

## 🛠 How to Run

### 1. Clone the repo
```bash
git clone https://github.com/your-username/hospitalmanagementsystem.git
```

### 2. Configure MySQL Database
Update the `application.properties` file:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the Spring Boot App
```bash
./mvnw spring-boot:run
```
Or use IntelliJ/Eclipse to run `HospitalmanagementsystemApplication.java`.

---

## 🎯 React Frontend (Optional)

### 1. Navigate to the `frontend` directory:
```bash
cd frontend
```

### 2. Install Dependencies:
```bash
npm install
```

### 3. Run the React App:
```bash
npm start
```

> Make sure the backend is running on port `8081` and update proxy in `package.json` accordingly.

---

## 📫 Postman API Testing

### 🔐 Login to receive token:
```http
POST http://localhost:8081/auth/login
Content-Type: application/json

{
  "email": "admin@gmail.com",
  "password": "admin123"
}
```

Copy the token from the response and use it in the Authorization header for subsequent requests:
```
Authorization: Bearer <your_token_here>
```

### 📝 Example: Get all doctors (admin access)
```http
GET http://localhost:8081/api/doctors
Authorization: Bearer <your_token>
```

---

## 📷 Screenshots

- ✅ Admin Dashboard
- ✅ Appointment Booking Form
- ✅ Doctor Consultation Table
- ✅ JWT Login Form (React + Bootstrap)

> Screenshots coming soon!

---

## ⚙️ Folder Structure (Backend)
```
src/main/java/
  └── com/minorproject/hospitalmanagementsystem/
        ├── controller
        ├── entity
        ├── repository
        ├── service
        ├── config
        ├── security
```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 🙌 Acknowledgments
- Spring Boot Docs
- React Bootstrap Docs
- JWT.io

---

> Feel free to fork, enhance and contribute to this project 🙏
