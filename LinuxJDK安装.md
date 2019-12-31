#### centos JDK安装
1. 搜索jdk安装包
```
yum search java|grep jdk
```
2. 下载jdk1.8，下载之后默认的目录为： `/usr/lib/jvm/`
```
yum install java-1.8.0-openjdk
```
3. 验证
```
java -version
```
