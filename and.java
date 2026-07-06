````markdown
# Auto Loan Application

This project is a **JavaFX-based Auto Loan Application** developed as part of a workshop at **Seneca College**. The application manages user authentication, customer information, vehicle details, loan calculations, and generates amortization schedules using modern software design patterns.

---

## Features

- **User Management**
  - Includes signup and login systems with in-memory storage.

- **Loan Management**
  - Handles customer and vehicle information.
  - Performs loan calculations including:
    - Down payment
    - Interest rate
    - Loan duration
    - Payment frequency
  - Provides real-time input validation.

- **Data Handling**
  - Utilizes the **Repository Pattern** for in-memory data management.

- **Amortization**
  - Generates and displays detailed amortization schedules.

---

## Architecture

The application follows the **Model-View-Controller (MVC)** design pattern and incorporates:

- Manual Dependency Injection (DI)
- Repository Pattern

These design patterns provide a clean separation of concerns, improve maintainability, and support scalable application development.

### Class Diagram

The following UML diagram illustrates the system architecture:

```markdown
![UML Diagram](UML Diagram.png)
```

---

## Technologies Used

- **JavaFX**
  - Builds the graphical user interface and handles application events.

- **Java Properties & Bindings**
  - Enables efficient synchronization between the UI and application data.

- **Object-Oriented Programming (OOP)**
  - Applies principles such as:
    - Inheritance
    - Interfaces
    - Encapsulation

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <your-repo-url>
```

### 2. Requirements

- Java JDK (with JavaFX libraries configured)
- An IDE such as:
  - IntelliJ IDEA
  - Eclipse

### 3. Run the Application

Compile and execute the main application class.

Once launched, the application is fully functional.

---

## Workshop Deliverables

This workshop includes:

- **UI Implementation**
  - JavaFX layouts including:
    - HBox
    - GridPane
    - AnchorPane

- **Business Logic**
  - Implementation of:
    - `LoanCalculation` interface
    - `FixedRateLoan` class

- **Documentation**
  - `reflect.txt` reflection document
  - Video walkthrough demonstrating:
    - Core code structure
    - Application functionality

---

## Developed For

**Seneca College**  
**School of Computer Studies**  
**Workshop 3**
````
