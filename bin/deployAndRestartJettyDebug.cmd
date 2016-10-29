@echo "redeploy started..."
cd ..
mvn clean package -Dmaven.test.skip=true -P dev
C:
cd jetty-9.3.9
java -Xdebug -agentlib:jdwp=transport=dt_socket,address=9999,server=y,suspend=n -jar start.jar
