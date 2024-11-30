## Compile and Run:

- Navigate to the root project directory in the terminal.
- Navigate to the root project directory in the terminal and Compile the entire project:
```shell
javac main/Main.java -d out
```
This compiles all .java files, preserving their package structure inside the out folder.
- Run the application:
```shell
java -cp out main.Main
```
Replace out with the folder where your compiled .class files are located, if different.
