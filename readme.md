
# Setting up the Jupyter Kernel for Java in VSCode
install notebook 
python install.py --sys-prefix
python -m jupyter kernelspec list
reload vscode

cd demo
javac -d out src/*.java ; java -cp out Main 
javac -d out -cp "lib/jline-3.21.0.jar" src/*.java ; java -cp "out;lib/jline-3.21.0.jar" Main

mvn clean compile exec:java -D"exec.mainClass="com.example.snake.App""