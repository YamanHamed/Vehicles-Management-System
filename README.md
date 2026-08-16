# Vehicle Management System (Java Console Application)

## Project Overview
This project is a console-based Vehicle Rental System designed to manage vehicle inventory, client records, and rental contracts. Developed using Java, the system implements Object-Oriented Programming (OOP) principles to provide a structured, scalable backend logic for a rental business.

## Core Architecture
The system is built on a modular class-based architecture:

- **Core Entities:** 
    - `Vehicle` (with subclasses: `Car`, `Motorcycle`, `Truck`)
    - `Client` (with subclasses: `Person`, `Company`)
    - `RentalContract` and `Invoice` to manage rental data, including dates and billing information.

- **Management Logic:**
    - `VehiclesManager`: Handles vehicle CRUD operations (Add, Remove, View) and rental status tracking.
    - `ClientsManager`: Manages client records, including editing and viewing details.
    - `RentalManager`: Oversees the rental process, including returning vehicles, contract management, and data retrieval.

## Technical Implementation
- **Data Structures:** The system utilizes `ArrayList` from the `java.util` package for dynamic data management, allowing for flexible scaling of vehicle, client, and contract records.
- **Algorithms:**
    - **Linear Search:** Used for data retrieval and search operations, providing efficient performance for moderate dataset sizes.
    - **Bubble Sort:** Implemented in the management modules to sort vehicles by rental frequency, enabling the system to generate analytics reports (e.g., most popular vehicles).
- **OOP Principles:** The application strictly follows OOP design patterns, utilizing inheritance, encapsulation, and polymorphism to ensure clean, maintainable, and extensible code.
