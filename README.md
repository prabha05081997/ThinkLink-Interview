# ThinkLink-Interview
Assignment project for interview

1. git clone https://github.com/prabha05081997/ThinkLink-Interview.git
2. switch to master branch
3. set the application.properties with appropriate values like (bitcoin.price.max, bitcoin.price.min, mail.to)

There are two ways we can start the project after this

i) run startup.sh script file, it'll take care of everything, basically it builds and runs docker image

OR

ii)
1. Install maven and Java 11
2. run mvn clean package
3. go to target folder inside root directory and run the jar file with below command "java -jar interview-0.0.1-SNAPSHOT.jar"