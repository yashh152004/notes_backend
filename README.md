# 📝 Notes Backend

A simple **Spring Boot backend API** for managing notes.  
This backend provides RESTful APIs to **create, read, update, and delete (CRUD)** notes.  
It can be used as the backend for a Notes web or mobile application.

---

## 🚀 Features
- Create a new note
- Get all notes
- Get a note by ID
- Update a note
- Delete a note
- Connected to a relational database (H2/MySQL/PostgreSQL – configurable)

---

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Web (REST APIs)**
- **Maven**
- **Database:** H2 (default), can switch to MySQL/PostgreSQL

---

## 📂 Project Structure

backend_notes/
├── src/main/java/com/example/backend_notes/
│ ├── controller/ # REST controllers (API endpoints)
│ ├── model/ # Note entity
│ ├── repository/ # Spring Data JPA repositories
│ 
├── src/main/resources/
│ ├── application.properties # DB & project config
├── pom.xml # Maven dependencies
