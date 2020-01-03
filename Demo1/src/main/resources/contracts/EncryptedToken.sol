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
