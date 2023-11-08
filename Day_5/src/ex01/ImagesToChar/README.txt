mkdir target

find src/java/edu.school21.printer -name "*.java" | xargs javac -d target

cp -R src/resources target

jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .

java -jar target/images-to-chars-printer.jar - +