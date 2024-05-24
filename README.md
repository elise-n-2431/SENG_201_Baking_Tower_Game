# SENG201 Project Overview
This project is an abstract baking simulator game developed using Java and JavaFX.

During gameplay, the player maintains a set of resource towers (ingredient stations) which are used to fill carts (baking bowls) which move along a conveyor belt during a round. The player must fill each cart before it reaches the oven at the end of the track. Win the game by filling every bowl in each round. Along the way, you can buy new towers, apply upgrades, and switch towers between the player's active and reserve slots if needed. Random events may cause towers to break; these towers must be repaired using a repair kit item before they can be used again in gameplay.

## Authors
- SENG201 Teaching team
- Elise Newman
- Hannah Grace

## Prerequisites
- JDK >= 17 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/18/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)


## What's Included
This project comes with some basic examples of the following (including dependencies in the build.gradle file):
- JavaFX
- Junit 5

We have also included a basic setup of the Gradle project and Tasks required for the course including:
- Required dependencies for the functionality above
- Test Coverage with JaCoCo
- Build plugins:
    - JavaFX Gradle plugin for working with (and packaging) JavaFX applications easily


## Importing Project (Using IntelliJ)
IntelliJ has built-in support for Gradle. To import your project:

- Launch IntelliJ and choose `Open` from the start-up window.
- Select the project and click open
- At this point in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load

**Note:** *If you run into dependency issues when running the app or the Gradle pop up doesn't appear then open the Gradle sidebar and click the Refresh icon.*

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app.
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/seng201_team48-1.0-SNAPSHOT.jar
2. Navigate to the 'build/libs/' directory (you can do this with `cd build/libs`)
3. Run the command `java -jar hgr82_ene41_team-48.jar` to open the application.

## Run Tests
1. Open a command line interface inside the project directory and run `./gradlew test` to run the tests.
2. Test results should be printed to the command line

## Image Credit
Images by Elise Newman and Hannah Grace.

## Background Audio Credit
The background audio used in this project is:

- **Track Name**: Groovy Ambient Funk
- **Artist**: moodmode
- **Source**: [Pixabay](https://pixabay.com/)
- **License**: Royalty-Free Music from Pixabay
