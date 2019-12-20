1. 打开智能合约开发的目录
```
/usr/local/ethereumdemo/demo1218
```
2. 使用truffle初始化项目
```
truffle init
```
3. 该过程可能会失败，因为会从国外网站拉取工程  
解决方法可以是直接git代码取代`truffle init`命令。
```
git clone https://github.com/truffle-box/bare-box
```
4. 在`/bare-box/contracts`目录下开发智能合约`Demo1.sol`
```
cd bare-box/migrations/
```  

`Demo1.sol`
```
pragma solidity >=0.4.21 <0.7.0;

contract Demo1 {
  uint height;
  // 160位数字
  address owner;  

  // 构造函数，实例化合约时调用
  constructor() public {
    height = 180;
    owner = msg.sender;
  }

  function set(uint h) public {
    height = h;
  }

  // constant代表方法只读，不消耗gas
  function get() public view returns (uint) {
    return height;
  }
}
```  

5. 在`/bare-box/migrations`目录下编写智能合约的发布脚本文件

`2_depoly_demo1.js`
```
const Demo1 = artifacts.require("Demo1");

module.exports = function(deployer) {
  deployer.deploy(Demo1);
};
```
6. 启动truffle服务  
启动后会自动分配10个测试账号，每个测试账号有100个以太币。
```
truffle develop
```
7. 编译智能合约  
因为这个版本的truffle没有包含solic，所以会先下载solic，国外的网站，过程有点长。（可以考虑事先单独安装solic，再进行编译。不过此处并未尝试。）
```
truffle compile
```
8. 部署合约
```
migrate
```
如果是重新编译了合约，直接部署，会提示过期，需要重置
```
migrate --reset
```
9. 获取合约实例
```
let contract;
contract = Demo1.deployed().then(instance => contract = instance);
```
10. 调用合约实例的方法(此处的get方法为Demo1.sol中的get方法)
```
contract.get();
```
也可以用`call`方式
```
contract.get.cal();
```
