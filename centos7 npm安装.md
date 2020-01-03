# centos7安装npm

## 下载安装包
```
cd /usr/local/node/
wget https://npm.taobao.org/mirrors/node/v13.4.0/node-v13.4.0-linux-x64.tar.gz
```
## 解压安装包
```
tar -zxvf node-v13.4.0-linux-x64.tar.gz
```
## 移除安装包
```
rm -rf node-v13.4.0-linux-x64.tar.gz
```
## 建立软连接
```
ln -s /usr/local/node/node-v13.4.0-linux-x64/bin/npm /usr/local/bin/npm
ln -s /usr/local/node/node-v13.4.0-linux-x64/bin/node /usr/local/bin/node
```
## 查看npm版本
```
npm -v
```
## npm升级`如果需要`，@后面是版本号
```
npm i -g npm@6.12.1
```
## 查看相关的文件信息
```
sudo whereis npm
sudo whereis node
```
