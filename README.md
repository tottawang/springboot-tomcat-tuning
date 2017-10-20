# springboot-tomcat-tuning
Create a springboot sample application using Redis

### Run and build
Step-1 : 
./gradlew clean build
export server_tomcat_max_threads=350

Step-2 :
java -jar build/libs/sample-0.0.1-SNAPSHOT.jar

Step-3:
curl http://localhost:{server_port}/api/condition
