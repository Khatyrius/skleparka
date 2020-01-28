# skleparka
UZ project for online shop.


To make it work you just have to clone/fork it, create database from file starterpack.sql in database folder. 

Then in the project itself you need to add into /WEB-INF/lib files:

mysql connector j 8 - https://dev.mysql.com/downloads/file/?id=492426

jstl 1.2 - http://www.java2s.com/Code/JarDownload/jstl/jstl-1.2.jar.zip

And add them to build path. (Right click on file in the project -> Build Path -> Add to buildpath(in Eclipse))

You also have to have tomcat(preferably tomcat 9) and if there are any errors add to buildpath these files from tomcat server directory(folder called lib):

catalina.jar,

jsp-api.jar,

servlet-api.jar.

Yeah, I know I could have used maven or gradle for this but we have to make this project with basic of the basics(no maven/spring etc).

For test to work you need to download and add to build path the following files:


junit-4.13.jar - https://search.maven.org/remotecontent?filepath=junit/junit/4.13/junit-4.13.jar

hamcrest-core-1.3.jar - https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

or you can just unpack the file neededJars.7z in the same folder :)
