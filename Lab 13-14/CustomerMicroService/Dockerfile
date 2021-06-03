FROM tomcat:jdk15-adoptopenjdk-openj9
ADD web/build/libs/web-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
