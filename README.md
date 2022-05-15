# ThinkLink-Interview
Assignment project for interview

1. **git clone https://github.com/prabha05081997/ThinkLink-Interview.git**
2. switch to **master** branch
3. set the application.properties with appropriate values like **(bitcoin.price.max, bitcoin.price.min, mail.to)**

There are two ways we can start the project after this

i)
1. Provide executable permission to startup.sh file **(sudo chmod +x startup.sh)**  
2. run startup.sh script file, it'll take care of everything, basically it builds and runs docker image

OR

ii)
1. Install **maven** and **Java 11**
2. run **mvn clean package**
3. go to target folder inside root directory and run the jar file with below command **"java -jar interview-0.0.1-SNAPSHOT.jar"**


##Note: Make sure to keep 8080 port free.

Once the application start running in 8080 port. cron service will start running for a interval of every
30 seconds

You can check the prices api with **http://localhost:8080/v1/prices?date=2022-05-15&offset=1&limit=10** URL.