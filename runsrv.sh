# ! /bin/sh

echo "Alfresco JLAN Server starting, enter 'x' to shutdown server, 'r' to restart server ..."
java -Djava.library.path=./jni -cp ./dist/LiferayCIFS.jar:./libs/cryptix-jce-provider.jar:./libs/wrapper.jar org.alfresco.jlan.app.JLANServer jlanConfig.xml

