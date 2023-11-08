mkdir target

find src/java/edu.school21.printer -name "*.java" | xargs javac -d target

java -cp target edu.school21.printer.app.Program . 0 it.bmp