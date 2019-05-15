# package dll in jar
this project demonstrate how to package a dll in a jar and let other java class to call

there are two programs in this demo

1. [jnaTest](jnaTest/src/main/java/com/localtest/jnatest/HelloJNA.java) as a wrapper to call **C/C++** style dll. Because of the limitation of JNA, you can run 32 or 64 bit dll depends on the JRE version. That is, you can call 64 bit dll from 64 bit JRE, call 32 bit dll from 32 bit JRE, but you cannot call 32 bit dll form 64 bit JRE. For the detail document about how to use it, please refer to [JNA github](https://github.com/java-native-access/jna), [JNA getting start](https://github.com/java-native-access/jna/blob/master/www/GettingStarted.md)

2. [clientTest](clientTest/src/main/java/Main.java) just a simple java progarm to demonstrate that it can run jnaTest as a library.

## compile

```bash
cd jnaTest/
mvn clean compile package 
# this command will create two jar: jnatest-1.0-SNAPSHOT.jar (the real library) and jnatest-1.0-SNAPSHOT-jar-with-dependencies.jar (for you to directly test the program)
mvn install

cd ../clientTest/
mvn clean compile package
java -jar target/clientTest-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### JNA notes
If you compile and package program with maven, please put the dll in src/main/resources/win32-x86/ for 32 bit version and src/main/resources/win32-x86-64/ for 64 bit. 

JNA runtime will search the dll from windows "PATH" environment variable and local folder win32-x86/, win32-x86-64/

In this example, I use `com.sun.jna.Platform.is64Bit()` to check JRE version. And I tested this program on windows 10 with JRE 8 (both 32 bit and 64 bit)

following is the compile environment I used in maven. (the compiler is 64 bit jdk)
```
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-18T02:33:14+08:00)
Maven home: C:\Users\kit\.m2\wrapper\dists\apache-maven-3.5.4-bin\4lcg54ki11c6mp435njk296gm5\apache-maven-3.5.4
Java version: 1.8.0_211, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_211\jre
Default locale: en_US, platform encoding: MS950
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```