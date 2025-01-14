# MultiFloorHospital

An object oriented programming university project that simulates the behavior of a multi floor hospital monitoring application.

## Table of Contents
1. [Introduction](#introduction)
2. [Overview](#overview)
3. [Set-up](#setup)
4. [Usage](#usage)
5. [Technologies Used](#technologies-used)


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
To launch to application the source code located in `src` foulder should be compiled and run.

This could be made manualy, or using `make` rules (only for Windows).

First, `make compile` command should be executed to compile the project.<BR>
Then by running `make run` the application could be started.