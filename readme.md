# Setting up the Jupyter Kernel for Java in VSCode
python -m venv venv
.\venv\Scripts\activate
pip install jupyter  

download/extract ijava-1.3.0.zip from https://github.com/SpencerPark/IJava/releases/tag/v1.3.0
in lib/ijava-1.3.0 folder run:
python install.py
python -m jupyter kernelspec list
reload vscode
select java kernel in snake.ipynb

# setup Java Project with Maven
setup java environment: link 
install maven from https://maven.apache.org/download.cgi
add maven/bin to PATH environment variable

#run the following commands in terminal to run the project:
mvn clean compile exec:java -D"exec.mainClass="com.example.snake.App""