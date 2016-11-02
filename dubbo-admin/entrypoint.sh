#!/bin/sh

if [ $DUBBO_REGISTRY ]; then
  sed -i "s/dubbo.registry.address=zookeeper:\/\/127.0.0.1:2181/dubbo.registry.address=$DUBBO_REGISTRY/g" /usr/local/tomcat/webapps/ROOT/WEB-INF/dubbo.properties
fi

if [ $DUBBO_ROOT_PASSWORD ]; then
  sed -i "s/dubbo.admin.root.password=root/dubbo.admin.root.password=$DUBBO_ROOT_PASSWORD/g" /usr/local/tomcat/webapps/ROOT/WEB-INF/dubbo.properties
fi

if [ $DUBBO_GUEST_PASSWORD ]; then
  sed -i "s/dubbo.admin.guest.password=guest/dubbo.admin.guest.password=$DUBBO_GUEST_PASSWORD/g" /usr/local/tomcat/webapps/ROOT/WEB-INF/dubbo.properties
fi

cat /usr/local/tomcat/webapps/ROOT/WEB-INF/dubbo.properties

catalina.sh run
