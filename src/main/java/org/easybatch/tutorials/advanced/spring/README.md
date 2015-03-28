# Spring Tutorial

## Description

This tutorial is a show case of how to configure Easy Batch using Spring framework using the provided `org.easybatch.integration.spring.BatchFactoryBean`.

## Pre-requisite

* JDK 1.6+
* Maven
* Git (optional)
* Your favorite IDE (optional)

## Get source code

### Using git

`https://github.com/EasyBatch/easybatch-tutorials.git`

### Downloading a zip file

Download the [zip file](https://github.com/EasyBatch/easybatch-tutorials/archive/master.zip) containing the source code and extract it.

## Run the tutorial

### From the command line

Open a terminal in the directory where you have extracted the source code of the project, then proceed as follows:

```
$>cd easybatch-tutorials
$>mvn install
$>mvn exec:java -PrunSpringTutorial
```

### From Your IDE

* Import the `easybatch-tutorials` project in your IDE
* Resolve maven dependencies
* Navigate to the `org.easybatch.tutorials.advanced.spring` package
* Run the `org.easybatch.tutorials.advanced.spring.Launcher` class without any argument