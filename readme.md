[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10488123&assignment_repo_type=AssignmentRepo)
# Project eCourse

## 1. Description of the Project

*To Do*

## 2. Planning and Technical Documentation

[Planning and Technical Documentation](docs/readme.md)

## 3. How to Build

To build the project, run the files:

    rebuild-all.bat
    or
    rebuild-all.sh

## 4. How to Execute Tests

If you want to run tests for all modules just run in command line:

    mvn test

## 5. How to Run

Assuming the build was done, we can run the application through the files:


eCourse - BackOffice
    
    run-backoffice.bat
    or
    run-backoffice.sh

## 6. How to Install/Deploy into Another Machine (or Virtual Machine)

For example if you want to deploy into another machine eCourse - BackOffice you need to:

    rebuild-all.bat
    run-backoffice.bat
    or
    rebuild-all.sh
    run-backoffice.sh    

## 7. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for linux/unix/macos):

    ./generate-plantuml-diagrams.sh


