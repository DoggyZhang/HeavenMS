@echo off
@title HeavenMS
set PATH=C:\SDK\Java\jdk1.8.0_192\bin;%PATH%
set CLASSPATH=.;dist\*
java -Xmx2048m -Dwzpath=wz\ net.server.Server
pause