FROM tomcat:9.0.44
RUN rm -rf /usr/local/tomcat/webapps/*
COPY build/libs/PetShop-Proxy-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
