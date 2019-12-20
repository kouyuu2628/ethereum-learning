### 什么是Truffle  
Truffle是一个世界级的开发环境，测试框架，以太坊的资源管理通道，致力于让以太坊上的开发变得简单，Truffle提供：
* 内置的智能合约编译，链接，部署和二进制文件的管理。
* 针对快速迭代开发的自动化合约测试。
* 可脚本化，可扩展的部署与迁移框架。
* 用于部署到任意数量的公网或私网的网络环境管理。
* 使用EthPM&NPM提供的包管理，使用ERC190标准。
* 与合约直接通信的交互控制台。
* 可配的构建流程，支持紧密集成。
* 在Truffle环境里支持执行外部的脚本。

### Linux安装Truffle
* 官方安装命令
```
sudo npm install -g truffle
```

* 访问国外网站速度很慢，而且可能会失败，换成淘宝镜像
```
npm install -g truffle --registry=https://registry.npm.taobao.org
```

* 使用root登录的话，进行全局安装，最后可能会出现权限问题导致失败，因此需要创建一个新用户来安装。见`Linux用户操作.md`。

* 使用新用户登录后，开始安装。还是继续报错。  
新用户会存在访问权限的问题，需要授权。
```
sudo chown -R $USER:$GROUP ~/.npm
sudo chown -R $USER:$GROUP ~/.config
sudo chown -R $USER:$GROUP /usr/local/node/node-v13.4.0-linux-x64/lib/node_modules
```

* 安装完后，创建软连接，使其可以全局使用：
```
sudo ln -s /usr/local/node/node-v13.4.0-linux-x64/bin/truffle /usr/local/bin/truffle
```

* 查看是否安装成功
```
truffle version
```
出现如下信息：
```
Truffle v5.1.4 (core: 5.1.4)
Solidity v0.5.12 (solc-js)
Node v13.4.0
Web3.js v1.2.1
```
