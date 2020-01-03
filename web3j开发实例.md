# Springboot web3j 实例演示
## 创建Maven项目
```
<groupId>com.web3jlabs.eth</groupId>
<artifactId>Demo1</artifactId>
<version>1.0-SNAPSHOT</version>
```
## 添加依赖
- web3j springboot
```
<dependency>
    <groupId>org.web3j</groupId>
    <artifactId>web3j-spring-boot-starter</artifactId>
    <version>1.6.0</version>
</dependency>
```
- web3j core
```
<dependency>
    <groupId>org.web3j</groupId>
    <artifactId>core</artifactId>
    <version>4.2.0</version>
</dependency>
```
## 编写合约
```
#EncryptedToken.sol
pragma solidity >=0.4.21 <0.7.0;

// 简易加密代币合约
contract EncryptedToken {
  uint256 INITIAL_SUPPLY = 666666;
  mapping(address => uint256) balances;

  constructor() public {
    // 代币发行量为666666
    balances[msg.sender] = INITIAL_SUPPLY;
  }

  // 转账到一个指定的地址
  function transfer(address _to, uint256 _amount) public {
    assert(balances[msg.sender] > _amount);
    balances[msg.sender] -= _amount;
    balances[_to] += _amount;
  }

  // 查看指定地址的余额
  function balanceOf(address _owner) public view returns (uint256) {
    return balances[_owner];
  }
}
```
合约文件放到`/resources/constracts`目录下
## 通过geth控制台发布合约，并将合约实例的相关信息记录在配置文件
![配置文件](https://github.com/kouyuu2628/ethereum-learning/blob/master/img/%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6.png)