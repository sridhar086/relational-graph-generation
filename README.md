# Compile
This project uses gradle wrapper to build a project. 
The gradle wrapper standardizes gradle for each project.
The JVM used in this project is JDK 17. Please make sure 
Java 17 is installed.

```
./gradlew clean build
```

The build will generate test coverage that can be found in 
`build/reports/jacoco`

The build will also generate shadow full jar in `build/libs`

# Run

To run the code, there are a couple of ways to execute the code.

## Using JVM
The jar can be directly run using the following command.

```
java -jar graph-0.0.1.jar <JSONL file>
```

There is sample dataset found under test-dataset
which can be run as follows,
```
java -jar build/libs/graph-0.0.1.jar test-dataset/bigdataformattedwithnull.jsonl
```

## GraalVM
To create a GraalVM, please install graal vm with java 17 version.
To generate the executable using GraalVM run the following script
For this, Java Home must be set to the GraalVM Java 17 version.
```
./make-script.sh
```

After running the above script, the executable can run the code 
with the JSONL file name
```
./applecart-assessment-executable-v0.0.1 <JSONL file>
```
This is method uses GraalVM to convert the jar to a 
executable file that can be run on as a executable.
**Caveat: The executable is built for Mac M3 architecture AArch64.**