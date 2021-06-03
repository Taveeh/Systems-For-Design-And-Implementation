FROM tomcat:9.0.44
LABEL maintainer="taveeh"
ADD web/build/libs/web-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
