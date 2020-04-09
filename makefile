EXEC = doc compTest

all: $(EXEC)

doc:
	cd src;javadoc battleship -d ../docs

classes:
	mkdir classes

comp: classes
	cd src;javac battleship/Main.java -d ../classes;javac battleship/graphic/Graphic.java -d ../classes

compTest: comp
	javac -classpath lib/test-1.7.jar test/TestShip.java test/TestCell.java test/TestPosition.java test/TestSea.java

test: compTest
	java -jar lib/test-1.7.jar TestPosition; java -jar lib/test-1.7.jar TestShip; java -jar lib/test-1.7.jar TestCell; java -jar lib/test-1.7.jar TestSea

io:
	java -jar dist/IO.jar

battleship:
	java -jar dist/Battleship.jar

graphic:
	cd dist;java -classpath Graphic.jar battleship.graphic.Graphic

exe:comp
	cd classes;jar cvfm ../dist/IO.jar ../lib/manifest/manifest-IO battleship
	cd classes;jar cvfm ../dist/Battleship.jar ../lib/manifest/manifest-Battleship battleship
	cd classes;jar cvf ../dist/Graphic.jar battleship;

extract:
	cd dist;jar xvf Battleship.jar

clean:
	rm -rf classes docs test/*.class dist/META-INF dist/battleship

.PHONY: clean
