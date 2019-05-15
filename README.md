# package dll in jar
this project demonstrate how to package a dll in a jar and let other java class to call

there are two programs in this demo

1. [jnaTest](jnaTest/src/main/java/com/localtest/jnatest/HelloJNA.java) as a wrapper to call c styple dll. Because of the limitation of JNA, you can run 32 or 64 bit dll depends on the JRE version. That is, you can call 64 bit dll from 64 bit JRE, call 32 bit dll from 32 bit JRE, but you cannot call 32 bit dll form 64 bit JRE. For the detail document about how to use it, please refer to [JNA github](https://github.com/java-native-access/jna), [JNA getting start](https://github.com/java-native-access/jna/blob/master/www/GettingStarted.md)

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