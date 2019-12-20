
## centos安装npm

1. 没有目录就自己创建一个
```
cd /usr/local/node/
```
2. 下载安装包
```
wget https://npm.taobao.org/mirrors/node/v13.4.0/node-v13.4.0-linux-x64.tar.gz
```
3. 解压安装包
```
tar -zxvf node-v13.4.0-linux-x64.tar.gz
```
4. 移除安装包
```
rm -rf node-v13.4.0-linux-x64.tar.gz
```
5. 建立软连接
```
ln -s /usr/local/node/node-v13.4.0-linux-x64/bin/npm /usr/local/bin/npm
ln -s /usr/local/node/node-v13.4.0-linux-x64/bin/node /usr/local/bin/node
```
6. 查看npm版本
```
npm -v
```
7. npm升级`如果需要`，@后面是版本号
```
npm i -g npm@6.12.1
```

> 其他
* 修改环境变量
```
vi /etc/profile
export PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin
```
* 查看相关的文件信息
```
sudo whereis npm
sudo whereis node
```
