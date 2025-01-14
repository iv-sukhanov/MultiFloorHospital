# MultiFloorHospital

An object oriented programming university project that simulates the behavior of a multi floor hospital monitoring application.

## Table of Contents
1. [Introduction](#introduction)
2. [Overview](#overview)
3. [Set-up](#setup)
4. [Usage](#usage)
5. [UML Diagram](#diagram)


## Introduction

The main purpose of the project is to simulate the behavior of a multi floor hospital monitoring system. It allows to keep track of and manipulate different hospital entities like :

* patients
* hospital staff
* equipment
* pharmasy
* financial account
* hospital rooms

They are connected to eachother, for example deleting a patient makes staff assigned to them free etc. Furthermore there are options to save data locally and to load previously saved data.

The interaction with the system is 1 to 1, meaning it is intended to be used only locally on the terminal it was ran on, and does not provide any web api.

## Overview
The application allows the user to add/delete and observe patients, hospital staff members, 
equipment pieces, and pharmasy items. To perform all the actions there is a simple and 
intuitive GUI.

Additionally, the application allows to serialize the data and store it in a file.
The data could be load to the program later.

## Set-up
There already is a compiled version of the program as well as `MultiFloorHospital.jar`, which runs the appication, in `build` foulder. However, to make sure that the executable is really the compiled code from `scr` foulder, the code could be easily compiled once again (if you have **Windows** operating system and **GNU make** installed) by running:
```
make compile
```
It runs the `compile.bat` script, which compiles the code once again and produces a new .jar file.

Then, to run the application, there is another make rule:
```
make run
```
This will just execute the `MultiFloorHospital.jar`, running the application.

Thus, running `make compile` and then `make run` will definetely run the appication.

In case you do not have linux or mac, just run the application by running this command:
```
java -jar ./build/MultiFloorHospital.jar
```

Or execute the `MultiFloorHospital.jar` manually.

## Usage

![Main munu]()

## Diagram

The diagram demonstrates classes and their relations. For simplisity unnesessary fields (e.g. getters, setters, and some constructors if they are not important) are ommited in order not to overload the diagram. The aim was to focus on denoting the main logic. Additionally, GUI elements are not displayed on the diagram, as they are not a part of application logic and do not help understand how the application works. 

The diagram on the image could look confusing, so there is additionally `.drawio` file in the `doc` foulder. This should help to inspect it in better quality.

![Diagram](./doc/.png)