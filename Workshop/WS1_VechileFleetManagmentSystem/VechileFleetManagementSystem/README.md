# Vehicle Fleet Management System

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Data Model and Class Hierarchy](#data-model-and-class-hierarchy)
- [System Design / Architecture](#system-design--architecture)
  - [MVC Overview](#mvc-overview)
  - [Class Relationships](#class-relationships)
  - [Program Flow](#program-flow)
- [Usage](#usage)
- [Limitations](#limitations)
- [Reflection](#reflection)

---

## Overview

This lab implements a **Vehicle Fleet Management System** using Java and the **Model-View-Controller (MVC)** design pattern. The system allows users to create a fleet of vehicles across different categories—Passenger, Commercial, and Specialized vehicles—and provides functionality to display, sort, and manage vehicle maintenance information. The main goal is to practice **class hierarchies, inheritance, interfaces, and object comparison** in Java while applying the MVC pattern.

---

## Features

- Create vehicles of various types (Passenger, Commercial, Specialized)  
- Display vehicles by category, purchase price, or maintenance urgency  
- Sort fleet by purchase price or by mileage / distance to next service  
- Calculate remaining mileage until next scheduled maintenance  

---

## Data Model and Class Hierarchy

The lab’s data model was provided, with minor enhancements:  

- **Updated `getCurrentMileage()`**: Changed return type from `void` to `int` in the abstract `Vehicle` class to allow calculations  
- **Added `mileageToNextService()` helper method**: Computes distance remaining until the next service  
- **Modified `toString()`**: Displays vehicle name and purchase price; detailed displays are handled in the `VehicleView` class  
- **Category methods**: `getCategory()` in abstract classes provides general category information  

The system retains the original class hierarchy, including abstract and concrete classes for different vehicle types.

---

## System Design / Architecture

### MVC Overview
- **Model**: Represents the vehicle objects and stores fleet data  
- **View**: Handles user interface, displays information, and receives input  
- **Controller**: Manages program flow, communicates between Model and View, creates vehicles, updates fleet, and handles sorting  

### Class Relationships
- Most operations are handled in the **Controller**, including creating vehicles, sorting, and determining maintenance priorities  
- **View** accesses the fleet only for display purposes  

### Program Flow
1. Initialize `VehicleView` and `VehicleController` in the `App` class  
2. Populate the fleet with predefined vehicle objects  
3. Display next vehicle for maintenance  
4. Sort fleet by purchase price and display results  
5. Sort fleet by mileage and proximity to next service and display results  
6. Display vehicles by category  

---

## Usage

1. Open the project in your Java IDE (e.g., VS Code, Eclipse)  
2. Run the `App` class  
3. The system will automatically:  
   - Populate the fleet  
   - Display maintenance information  
   - Sort and display vehicles by purchase price and mileage  
   - Group vehicles by category  

> Note: The program runs entirely in the `main()` method without an interactive menu.

---

## Limitations

- **Mileage input**: Entering non-numeric characters will crash the program; better input validation could be implemented using `String` input and `Integer.parseInt()`  
- **Maintenance priority**: Vehicles are prioritized by overall mileage, which may not always reflect real-world urgency (e.g., a vehicle slightly over its service interval may be listed lower)  
- **No interactive menu**: Program runs sequentially in `main()`. Adding a menu would improve usability  
- **Controller modularity**: All program flow is managed by a single Controller class; breaking it into sub-controllers could improve modularity  

---

## Reflection

Coming from a front-end development background with Angular and React, this lab was initially challenging. I struggled with how the Controller could act as the central point of control while interacting with both the Model and the View. Questions arose about where the fleet array should be created, how concrete classes interact with abstract classes, and how to implement and apply interfaces effectively.

Structuring the Model, View, and Controller without a prearranged framework required manually modularizing the system. While I achieved a functional program, the Controller still manages the entire flow, and additional sub-controllers could improve modularity.

Despite limitations, this lab strengthened my understanding of object-oriented programming principles, class hierarchies, interfaces, and the MVC design pattern. It also emphasized the importance of modular design and clear separation of responsibilities in creating maintainable software.
