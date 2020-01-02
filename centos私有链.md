windows上已经创建了2个节点，现在在Linux上创建第三个节点

1. 创世区块的配置文件`genesis.json`拷贝到`/usr/local/ethereumdemo/`目录下
2. 使用创世区块的配置文件初始化第三个节点
```
cd /usr/local/ethereumdemo
geth --datadir selfchain03 init /usr/local/ethereumdemo/genesis.json
```
3. 启动私有链网络
```
geth --networkid 123 --datadir /usr/local/ethereumdemo/selfchain03 --rpc --rpcaddr 192.168.11.237 --rpcport 8547 --port 3002 --rpcapi personal,db,eth,net,web3 --allow-insecure-unlock --ipcdisable --nodiscover console 2>>selfchain03.log
```
4. 连接第二个节点
```
admin.addPeer("enode://51376004d1d97b640bd4bd8816c249ac342e90e3a5659669dd846825a468e274e7dfe336a85a36183e932928d2c112263d7323427b97abfae9abbfca2d5635aa@192.168.11.46:3001?discport=0")
```
5. 连接是连接上了，但是同步节点信息一直报错，试过很多方法，都不能成功。猜测是geth版本与windows不一致导致的。
6. 重新从`https://geth.ethereum.org/downloads/`下载编译好的geth文件，放到`/usr/local/bin`目录
7. 删掉原版本环境变量
```
vim /etc/profile
#删除这行
export PATH=$PATH:/usr/local/go-ethereum-1.8.22/build/bin
source /etc/profile
```
8. 重启系统，查看版本
```
geth version
```
如果提示没有权限，修改文件权限
```
chmod 777 /usr/local/bin/geth
```
9. 重复步骤1~4
10. 节点连接成功，但是同步节点信息任然一直报错。
开启防火墙，打开8087和30303端口（geth用到的端口）
以及第一个和第二个节点的端口。成功！法克。
```
systemctl start firewalld
firewall-cmd --zone=public --add-port=8087/tcp --permanent
firewall-cmd --zone=public --add-port=30303/tcp --permanent
firewall-cmd --zone=public --add-port=3000/tcp --permanent
firewall-cmd --zone=public --add-port=3001/tcp --permanent
firewall-cmd --reload
```
