#If you have a non-Linux OS, this is useless to you. However, if you use Linux, this will help a lot with testing/running.

#enter "make" in the terminal to execute the following commands.
make: Ball.java CueBall.java Physics.java Pool.java
	javac *.java
	java Pool

#enter "make c" to execute the following.
c: Ball.java CueBall.java Physics.java Pool.java
	javac *.java

#enter "make clean" to execute the following.
clean: Ball.class CueBall.class Physics.class Pool.class
	rm *.class