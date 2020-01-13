### `Geth`常用命令
|  单位  |  wei值  |
|  ---   |  ---  |
|  创建账户  |  `personal.newAccount()`  |
|  解锁账户  |  `personal.unlockAccount()`  |
|  查看a节点上所有账户  |  `personal.listAccounts`  |
|  查看节点所有的账户详情  |  `personal.listWallets`  |
|  枚举系统中的账户  |  `eth.accounts`  |
|  查看账户余额，返回值的单位是 Wei  |  `eth.getBalance('地址')`  |
|  列出区块总数  |  `eth.blockNumber`  |
|  获取交易  |  `eth.getTransaction()`  |
|  获取区块  |  `eth.getBlock()`  |
|  节点挖矿奖励所在的账户  |  `eth.coinbase`  |
|  查看节点连接数  |  `web3.net.peerCount`  |
|  Wei 换算成以太币  |  `web3.fromWei()`  |
|  以太币换算成 Wei  |  `web3.toWei()`  |
|  交易池中的状态  |  `txpool.status`  |
|  连接到其他节点  |  `admin.addPeer()`  |
|  查看连接的节点信息  |  `admin.peers`  |
|  查看本节点的连接数  |  `net.peerCount`  |
|  开始挖矿  |  `miner.start()`  |
|  停止挖矿  |  `miner.stop()`  |
|  修改节点的挖矿账户  |  `miner.setEtherbase()`  |
