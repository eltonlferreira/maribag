maribag: Assortment of technologies including Arquillian
========================
Author: Rogerio A. Bueno
Level: BASIC
Technologies: CDI, JPA, EJB, JPA, JAX-RS, BV, AngularJs and Bootstrap.
Summary: An example that incorporates multiple technologies
Target Project: WildFly

        Fork from Source: <https://github.com/wildfly/quickstart/>

What is it?
-----------

This is your project! It is a sample, deployable Maven 3 project to help you get your foot in the door developing with Java EE 7 on JBoss WildFly.

System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.7) or better, Maven 3.1 or better.
The application this project produces is designed to be run on JBoss WildFly.

Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy
-------------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package wildfly:deploy

4. This will deploy `target/maribag.war` to the running instance of the server.
 
Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/maribag/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Run the Arquillian Tests 
-------------------------

This quickstart provides Arquillian tests. By default, these tests are configured to be skipped as Arquillian tests require the use of a container. 

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Configure your JBOSS_HOME or set the file arquillian.xml in resorces.
4. Type the following command to run the test goal with the following profile activated:
 
       mvn clean test -Parq-wildfly-managed
