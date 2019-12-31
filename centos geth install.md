下载Golang
```
cd /usr/local
wget https://storage.googleapis.com/golang/go1.11.8.linux-amd64.tar.gz
```
解压
```
tar zxvf go1.11.8.linux-amd64.tar.gz
```
设置环境变量
```
mkdir gopath
vim /etc/profile
```
```
export GOROOT=/usr/local/go
export GOPATH=/usr/local/gopath
export PATH=$PATH:$GOPATH/bin
保存
```
在`github`找到稳定的`geth`版本并下载，这里下载`v1.9.9`
```
https://github.com/ethereum/go-ethereum/tags
```
解压
```
tar zxvf go-ethereum-1.9.9.tar.gz.gz
```
目录更名为`geth`
```
mv /usr/local/go-ethereum-1.9.9 /usr/local/geth
```
打包命令
```
cd geth
make geth
```
提示确实安装包，安装缺失的安装包
```
go get github.com/Azure/azure-storage-blob-go/azblob
go get github.com/cespare/cp
go get golang.org/x/crypto/openpgp
go get golang.org/x/crypto/sha3
```
如果提示`Could not resolve host: github.com`
```
vi /etc/hosts
#添加一行
192.30.255.112  github.com
保存
```
如果提示`time out`，则换一张方式下载
```
#'xxx'为需要的包，如'crypto'等,下载后解压，找到需要的子包，拷贝到报错的路径下
git clone https://github.com/golang/xxx.git
```