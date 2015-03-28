# Content-Based record dispatcher Tutorial 2

## Description

This tutorial is a show case of the `ContentBasedRecordDispatcher` to dispatch fruits based on their type.
Fruits are processed in parallel using multiple queues.

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
$>mvn exec:java -PrunFruitsParallelProcessingTutorial
```

### From Your IDE

* Import the `easybatch-tutorials` project in your IDE
* Resolve maven dependencies
* Navigate to the `org.easybatch.tutorials.advanced.cbrd.fruits` package
* Run the `org.easybatch.tutorials.advanced.cbrd.fruits.FruitsParallelProcessingTutorial` class without any argument
