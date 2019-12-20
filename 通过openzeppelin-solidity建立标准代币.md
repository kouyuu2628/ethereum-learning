### 建立标准代币【书城币】
全称`Book Mall Coin`  
简称`BMC`

### 开发流程
1. 创建工程目录
```
cd /usr/local/ethereumdemo/demo1219
mkdir BMC
cd BMC
```

2. truffle init
```
git clone https://github.com/truffle-box/bare-box
```

3. 初始化配置文件
```
cd bare-box
npm init
```
生成`package.json`
```
{
  "name": "BMC",
  "version": "1.0.0",
  "description": "Book Mall Coin",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "huangyong",
  "license": "ISC"
}
```
###### 为什么要使用npm init初始化项目  
在node开发中使用`npm init`会生成一个`pakeage.json`文件，这个文件主要是用来记录这个项目的详细信息的，它会将我们在项目开发中所要用到的包，以及项目的详细信息等记录在这个项目中。方便在以后的版本迭代和项目移植的时候会更加的方便。也是防止在后期的项目维护中误删除了一个包导致的项目不能够正常运行。  
使用`npm init`初始化项目还有一个好处就是在进行项目传递的时候不需要将项目依赖包一起发送给对方，对方在接受到你的项目之后再执行`npm install`就可以将项目依赖全部下载到项目里。

4. 安装`openzeppelin-solidity`模块  
```
npm install @openzeppelin/contracts
```
###### OpenZeppelin-solidity
OpenZeppelin-solidity是一个用于安全智能合约开发的库。它提供了诸如ERC20和ERC721等标准的实现，您可以按原样部署或扩展这些标准以满足您的需要，还提供了用于构建自定义合同和更复杂的分散系统的可靠组件。

5. `/contracts`下编写代币合约`BookMallCoin.sol`  

```
pragma solidity ^0.5.0;
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20Detailed.sol";

contract BookMallCoin is ERC20, ERC20Detailed {
  constructor() public ERC20Detailed("BookMallCoin", "BMC", 18) {
    _mint(
      msg.sender,
      // 发布书城币
      10000 * (10 ** unit256(decimals()))
    );
  }
}
```

6. `/migrations`下编写部署脚本`2_deploy_BookMallCoin.js`

```
const BookMallCoin = artifacts.require("BookMallCoin");

module.exports = function(deployer) {
  deployer.deploy(BookMallCoin);
};
```
7. 启动truffle
```
truffle develop
```
8. 编译合约
```
compile
```
9. 部署合约
```
migrate
```
10. 获取合约实例
```
let contract;
contract = BookMallCoin.deployed().then(instance => contract = instance);
```
