FROM tomcat:9
MAINTAINER vs-sales-management
COPY build/libs/SalesManagement-* /usr/local/tomcat/webapps/sales-management.war