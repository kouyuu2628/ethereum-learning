## 下载Golang
```
cd /usr/local
wget https://storage.googleapis.com/golang/go1.11.8.linux-amd64.tar.gz
```
## 解压
```
tar zxvf go1.11.8.linux-amd64.tar.gz
```
## 设置环境变量
```
mkdir gopath
vim /etc/profile
```
添加以下内容并保存
```
export GOROOT=/usr/local/go
export GOPATH=/usr/local/gopath
export PATH=$PATH:$GOPATH/bin
```
## 在`github`找到稳定的`geth`版本并下载
```
https://github.com/ethereum/go-ethereum/tags
```
## 解压
```
tar zxvf go-ethereum-x.xx.x.tar.gz
```
## 打包命令
```
cd go-ethereum-x.xx.x
make all
```
提示缺少安装包，安装缺失的安装包
```
go get github.com/Azure/azure-storage-blob-go/azblob
go get github.com/cespare/cp
go get golang.org/x/crypto/openpgp
go get golang.org/x/crypto/sha3
```
如果提示`Could not resolve host: github.com`
```
vi /etc/hosts
192.30.255.112  github.com
```
如果提示`time out`，则换一张方式下载
```
#'xxx'为需要的包，如'crypto'等,下载后解压，找到需要的子包，拷贝到报错的路径下
git clone https://github.com/golang/xxx.git
```
这里是个坑，下载的geth包，依赖了茫茫多的三方包，一个个去找这些三方包，没有止境。这个时候可能是下载的geth包不合适，重新去github找稳定的版本下载安装。
## 添加环境变量
```
vim /etc/profile
export PATH=$PATH:/usr/local/go-ethereum-1.8.22/build/bin
```
## 验证
```
geth version
```
