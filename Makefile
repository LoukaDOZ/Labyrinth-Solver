#Variables 

JFLAGS = -g -encoding UTF8 -implicit:none
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:	
			$(JC) $(JFLAGS) $*.java


#------------------------------------------

CLASSES = \
		Algorithm.java\
		AutomaticSimulation.java\
		GridActionManagement.java\
		JTextAreaManagement.java\
		Window.java\
		ManualManagement.java\
		ManualSimulation.java\
		MenuActionManagement.java\
		Panel.java\
		Tab.java\
		TimerManagement.java\
		Main.java\
 

#----------------------------------------------
MAIN = Main 					# Main file

build: $(CLASSES:.java=.class)

run:
		$(JVM) $(MAIN)
clean:
		$(RM) *.class