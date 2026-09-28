# Dictionary📚

A simple portfolio project for managing, filtering and looking up words in a dictionary.

## Description✨

This mini project is designed as a console application that implements dictionary functionality using CRUD operations plus additional features. It communicates with the user through a menu-driven interface, accepting keyboard input for various actions such as creating, reading, updating, and deleting words, searching by fragment, or filtering the collection. To ensure that it works as intended, it uses multiple input validation checks for every option that can be selected or entered by the user, providing feedback for invalid input through descriptive messages. At the same time, it allows the user to correct only the invalid input without restarting the current action through various loops.

## Features✅

- Add words and descriptions
- Remove words
- Modify word descriptions
- Find words
- Display the dictionary
- Filter the dictionary by word length
- Search the dictionary by fragment, ignoring case
- Repeat the same action after a successful operation

## Technologies🛠️

- Java 23
- Maven
- Java Collections Framework (`TreeMap`)
- Streams & Lambda Expressions
- Regular Expressions (Regex)
- Exception Handling
- Console Input with `Scanner`

## Key Concepts Demonstrated🔑

- Automatic word sorting using `TreeMap` with case-insensitive ordering
- Input validation with user-friendly error handling
- Streams and Regular Expressions for word filtering and searching

## Project Structure📂

- `MainDict.java` - Application entry point
- `DictClass.java` - Dictionary operations, menu handling and input validation

## How to Run🚀

### Prerequisites
- JDK 23
- IntelliJ IDEA

### Clone the repository

```bash
git clone https://github.com/razvanR26/Dictionary.git
```

### Open the project

Open the cloned `Dictionary` folder as a Maven project in IntelliJ IDEA.

### Run the application

Run the `MainDict` class from IntelliJ IDEA.
