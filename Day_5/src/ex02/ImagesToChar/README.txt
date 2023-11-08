mkdir target

jar xf lib/jcommander-1.78.jar

jar xf lib/JColor-5.5.1.jar

mv com target

rm -rf META-INF

cp -R src/resources target

find src/java/edu.school21.printer -name "*.java" | xargs javac \
-cp .:lib/JColor-5.5.1.jar:lib/jcommander-1.78.jar -d target

jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .

java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN