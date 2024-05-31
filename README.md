# ScalaTaskManager

ScalaTaskManager is a powerful and flexible task management application built with Scala. It allows users to create, manage, and prioritize tasks seamlessly, featuring both simple and recurring tasks.

## Features

- **Task Management**:
  - Add, remove, and complete tasks.
  - Support for both simple and recurring tasks.
- **Prioritization**:
  - Assign priority levels to tasks.
  - Sort tasks by priority.
- **Due Dates**:
  - Set and manage due dates for tasks.
  - Automatically calculate next due date for recurring tasks.
- **Filtering**:
  - Filter tasks by completion status.

## Technologies Used

- **Scala**: The application is written in Scala.
- **Standard Library**: Utilizes Scala's standard library for collections and I/O operations.

<img width="1910" alt="Screenshot 2024-05-31 at 1 43 23 PM" src="https://github.com/shuddha2021/ScalaTaskManager/assets/81951239/3124d240-a165-458a-920d-050cc5fd2d62">

<img width="961" alt="Screenshot 2024-05-31 at 1 43 46 PM" src="https://github.com/shuddha2021/ScalaTaskManager/assets/81951239/9ad42c92-869e-4150-b2b1-e96a6d093c5e">


## Core Logic

- **Add Task**: Allows users to add simple or recurring tasks with optional due dates.
- **Remove Task**: Enables users to remove tasks by index.
- **Complete Task**: Marks tasks as completed and calculates the next due date for recurring tasks.
- **View Tasks**: Displays all tasks with their details and statuses.
- **Sort Tasks**: Sorts tasks based on their priority levels.
- **Filter Tasks**: Filters tasks based on their completion status.

## Project Structure

The project consists of the following main files:

- `main.scala`: Contains the implementation of the task management logic and the user interface for interacting with tasks.
- `build.sbt`: Defines the project's dependencies and build settings.

## Getting Started

To get started with this project:

1. Clone the repository.
2. Navigate to the project directory.
3. Compile the project using `sbt compile`.
4. Run the application with `sbt run`.

## Why This Project Is Useful

This project serves as a practical example of implementing a task management system in Scala. It demonstrates various concepts such as trait-based design, case classes, and functional programming principles in a real-world scenario.

## Contributing

Contributions to this project are welcome. Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
