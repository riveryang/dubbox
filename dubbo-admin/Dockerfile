# Dubbo Admin for Tomcat 9-alpine
# Version 3.0.0

FROM tomcat:9-alpine

MAINTAINER RiverYang comicme_yanghe@icloud.com

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/dubbo-admin-3.0.0.war /usr/local/tomcat/webapps/

RUN cd /usr/local/tomcat/webapps && mkdir ROOT && unzip dubbo-admin-3.0.0.war -d ROOT && rm -rf dubbo-admin-3.0.0.war

ADD entrypoint.sh /

EXPOSE 8080

CMD ["/entrypoint.sh"]
