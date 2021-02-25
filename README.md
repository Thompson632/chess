# Chess Repository

The purpose of this source code repository is to hold all source and deliverables required for the SE576 at Drexel University Chess Validation library for Winter 2021.

## Description

The project is formatted to be built using Maven and allows for better dependency management. 

## Getting Started

### Dependencies

* Java 1.8
* Apache Maven 3.6.2


### Installing

* Clone or download the repository to your local disk. 
* Open Eclipse or another IDE -> Select Import -> Maven Project -> Location of Repository


### Executing Program

From Eclipse (or another IDE)
* Navigate to Main.java and select the Run option in the IDE.

From Command Line
* Navigate to the top-level repository and then execute the following commands:
* cd target/appassembler/bin
* ./RunChess.sh


### Executing Test Suite

From Eclipse (or another IDE)
* Select the src/test/java package, right-click, and select Run As -> JUnit Test
* Open up the JUnit perspective and verify that all unit tests have passed successfully

From Command Line
* Navigate to the top-level repository and then execute the following commands:
* mvn clean package site
* The above command will compile all source and test code as well as run all unit tests.
* If the build succeeds, all unit tests have passed successfully


### Executing Test Suite Code Coverage

From Eclipse (or another IDE)
* Select the src/test/java package, right-click, and select Coverage As -> JUnit Test
* Open up the Coverage perspective and verify the current view is filtered on Branches as opposed to Instructions.
* Once verified, select the src/main/java package and verify coverage is above 80%.

From Command Line
* Navigate to the top-level repository and then execute the following commands:
* mvn clean package site
* Once the build succeeds, cd target/site/jacoco/ and open the index.html
* Verify that the coverage is above 80%.


### Deliverables

Project reports and deliverables are located in the deliverables.tar.gz file. Run the following command to open the tar file:
* Navigate to the top-level repository and then execute the following command:
* tar -xvf deliverables.tar.gz


### Pre-Conditions / Not Implemented as of 02/22/2021

The project does not do the following:
* Source does not check to see if a piece is already in a set place upon custom board creation. If subsequent color pieces and/or different color pieces are to be placed on the same cell, the source will use the last piece as the real piece to be placed.
* Logic related to castling with a King and a Rook is not implemented.

## Authors

[Robert Thompson](rt598@drexel.edu)