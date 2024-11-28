build:
	javac *.java

run: build
	java -ea RsaTester

clean:
	rm *.class
