# Simple Snake Game (Java) & Java Basics Notebook

## 1. Java Basics Notebook

[`java-basic.ipynb`](java-basic.ipynb) helps you learn Java step by step with short examples:

- Variables, data types, operators
- Control statements: if/else, loops, switch
- Object-Oriented Programming: classes, objects, methods
- Exception handling (try/catch)
- Collections: List, Set, Map
- Streams and lambdas

To use the notebook:
1. Set up Jupyter and the Java kernel (see below).
2. Open `java-basic.ipynb` in VSCode and select the Java kernel.

## 2. Snake Game (Java, Maven)

1. Install Java (JDK) and [Maven](https://maven.apache.org/download.cgi).
2. Add Maven's `bin` folder to your PATH.
3. In the project folder, run:
   ```
   mvn clean compile exec:java -D"exec.mainClass=com.example.snake.App"
   ```

## 3. Jupyter Java Kernel Setup

1. Create a Python virtual environment:
   ```
   python -m venv venv
   .\venv\Scripts\activate
   pip install jupyter
   ```
2. Download and extract `ijava-1.3.0.zip` from [IJava Releases](https://github.com/SpencerPark/IJava/releases/tag/v1.3.0).
3. In the extracted `ijava-1.3.0` folder, run:
   ```
   python install.py
   python -m jupyter kernelspec list
   ```
4. Restart VSCode, open the notebook, and select the Java kernel.

## Assets

Game images are in the `assets/` folder.
