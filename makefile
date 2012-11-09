JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	NTExec.java \
	production.java \
	codeGenerator.java \
	executionTree.java \
	eval.java \
	token.java \
	types.java \
	scanner.java \
	parse.java \
	test.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
