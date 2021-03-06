# 创建私有链
### 配置创世区块
```
{
  "config": {
    "chainId": 1234,
    "homesteadBlock": 0,
    "eip150Block": 0,
    "eip155Block": 0,
    "eip158Block": 0,
    "byzantiumBlock": 0,
    "constantinopleBlock": 0,
    "petersburgBlock": 0
  },
  "alloc": {},
  "coinbase": "0x0000000000000000000000000000000000000000",
  "difficulty": "0x400",
  "extraData": "",
  "gasLimit": "0x2fefd8",
  "nonce": "0x0000000000000042",
  "mixhash": "0x0000000000000000000000000000000000000000000000000000000000000000",
  "parentHash": "0x0000000000000000000000000000000000000000000000000000000000000000",
  "timestamp": "0x00"
}
```
- 配置说明：
```
config.chainId // 区块链的ID，在 geth 命令中的 --networkid 参数需要与 chainId 的值一致
config.homesteadBlock // Homestead 硬分叉区块高度，不需要关注
config.eip155Block // EIP 155 硬分叉高度，不需要关注
config.eip158Block // EIP 158 硬分叉高度，不需要关注
coinbase // 矿工账号，第一个区块挖出后将给这个矿工账号发送奖励的以太币
difficulty // 难度值，越大越难
extraData // 附加信息随便填
gasLimit // gas 的消耗总量限制，用来限制区块能包含的交易信息总和，因为我们是私有链，所以填最大
nonce // 一个 64 位随机数
mixhash // 与 nonce 配合用于挖矿，由上一个区块的一部分生成的 hashs
parentHash // 上一个区块的 hash 值
alloc // 预设账号以及账号的以太币数量，私有链挖矿比较容易可以不配置
```
### 初始化创世区块
```
geth --datadir selfchain init C:\Users\THINK\AppData\Local\Ethereum\genesis.json
```
- ※所有步骤中，windows下的控制台窗口都是在`C:\Users\THINK\AppData\Local\Ethereum`目录下打开
### 启动私有链网络
```
geth --networkid 123 --datadir selfchain --rpc --rpcaddr 192.168.11.46 --rpcport 8545 --port 3000 --rpcapi personal,db,eth,net,web3 --allow-insecure-unlock --ipcdisable --nodiscover console 2>>geth1.log
```
### 创建账号
```
personal.newAccount("huangyong001")
```
```
账号：0xd729dadd63721093358cec5468dd8367f7e60516
密码：huangyong001
钱包文件：UTC--2019-12-24T06-24-57.131296400Z--d729dadd63721093358cec5468dd8367f7e60516
```
```
personal.newAccount("huangyong002")
```
```
账号：0xa9a4d3b2b2fe511f918015f7a2bd79b3c306b5c7
密码：huangyong002
钱包文件：UTC--2019-12-25T01-35-32.578345300Z--a9a4d3b2b2fe511f918015f7a2bd79b3c306b5c7
```
### 开始挖矿
```
miner.start()
```
- 等待ing
```
INFO [12-24|14:46:32.514] Generating DAG in progress epoch=1 percentage=28 elapsed=55.519s
INFO [12-24|14:46:34.086] Generating DAG in progress epoch=1 percentage=29 elapsed=57.091s
INFO [12-24|14:46:35.667] Generating DAG in progress epoch=1 percentage=30 elapsed=58.672s
```
- 当percentage到100时，停止挖矿
### 停止挖矿
```
miner.stop()
```
### 查看最后一个区块以及区块信息
```
eth.blockNumber
eth.getBlock(eth.blockNumber)
```
```
{
  difficulty: 131200,
  extraData: "0xda83010909846765746888676f312e31332e348777696e646f7773",
  gasLimit: 3191015,
  gasUsed: 0,
  hash: "0x6327e6a3651930b3403cea64cd367dab9f9e84962b175883f8b4de46d004a7ec",
  logsBloom: "0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
  miner: "0xd729dadd63721093358cec5468dd8367f7e60516",
  mixHash: "0x1b2b9f46c2eccf2d8526aaaa448cd1e493ceccfb83971e93d0518a46630b084d",
  nonce: "0x04461a919138f695",
  number: 16,
  parentHash: "0xf60714762ab3bc0fe7d9a098c2eaf1bf14a889df10531e23655109e97f1fb495",
  receiptsRoot: "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
  sha3Uncles: "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
  size: 538,
  stateRoot: "0x1dde967ddcb326e63b63bb652c3175ea7627093297535ca84c1f7bc5d2d4bfbf",
  timestamp: 1577171456,
  totalDifficulty: 2102592,
  transactions: [],
  transactionsRoot: "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
  uncles: []
}
```
### 拿到挖到最后一个区块的矿工的名字
```
eth.getBlock(eth.blockNumber).miner
```
### 查找该矿工总共有多少以太坊币
```
eth.getBalance(eth.getBlock(eth.blockNumber).miner)
```
### 使用创世区块的配置文件`genesis.json`初始化第二个节点
- 在`C:\Users\THINK\AppData\Local\Ethereum`打开新的终端
```
geth --datadir selfchain02 init C:\Users\THINK\AppData\Local\Ethereum\genesis.json
```
### 启动第二个节点
```
geth --networkid 123 --datadir selfchain02 --rpc --rpcaddr 192.168.11.46 --rpcport 8546 --port 3001 --rpcapi personal,db,eth,net,web3 --allow-insecure-unlock --ipcdisable --nodiscover console 2>>geth2.log
```
### 第一个节点上查看`enode`
```
admin.nodeInfo
```
```
{
  enode: "enode://98990baf3801d9b5a1b702df1dd1add464d7b0cb0e7a9d0059cbda6c55c91445b6d5c4b6e96aae78c6d6dc12f49231b3025c1d177deecabd9447efb49f10ea62@127.0.0.1:3000?discport=0",
  enr: "enr:-JC4QBdlUELz8r1dsbzh01lJaAZ0W3YbmH_R-0KeOri-cpXFR0m605HIf0AXJhNiZA9-xwP2WZFoRlGNd7zMJfGclmsNg2V0aMfGhBKNR6KAgmlkgnY0gmlwhH8AAAGJc2VjcDI1NmsxoQKYmQuvOAHZtaG3At8d0a3UZNewyw56nQBZy9psVckURYN0Y3CCC7g",
  id: "0a715bc4a4d21bae774cb845add7739813eff3611be116e343c07b8e86c25197",
  ip: "127.0.0.1",
  listenAddr: "[::]:3000",
  name: "Geth/v1.9.9-stable-01744997/windows-amd64/go1.13.4",
  ports: {
    discovery: 0,
    listener: 3000
  },
  protocols: {
    eth: {
      config: {
        byzantiumBlock: 0,
        chainId: 1234,
        constantinopleBlock: 0,
        eip150Block: 0,
        eip150Hash: "0x0000000000000000000000000000000000000000000000000000000000000000",
        eip155Block: 0,
        eip158Block: 0,
        homesteadBlock: 0,
        petersburgBlock: 0
      },
      difficulty: 3941312,
      genesis: "0xb052b04f62e162f598755f65ff9a8c3d00e8bcf709cc24c68c2d4780031553c1",
      head: "0x512d251ef884056add4ea4d1710ad725f24762f0b63bb06f5b6bc0905484e441",
      network: 123
    }
  }
}
```
### 在第二个节点上连接第一个节点
```
admin.addPeer("enode://98990baf3801d9b5a1b702df1dd1add464d7b0cb0e7a9d0059cbda6c55c91445b6d5c4b6e96aae78c6d6dc12f49231b3025c1d177deecabd9447efb49f10ea62@127.0.0.1:3000?discport=0")
```
### 在第二个节点创建账号，并指定为挖矿账号
```
personal.newAccount("huangyong101")
miner.setEtherbase(personal.listAccounts[0])
```
### 在第二个节点上开始挖矿
- 可以看到第一个节点在同步数据
### 第一个节点的用户给第二个节点的用户转账
```
#解锁账号
personal.unlockAccount("0xd729dadd63721093358cec5468dd8367f7e60516")
#发送交易
eth.sendTransaction({from: '0xd729dadd63721093358cec5468dd8367f7e60516', to: '0xf647200766920efcc97acb884ef1753620c0006f', value: 60000000000000})
#查看交易持状态：pending=1
txpool.status
#挖矿
miner.start()
#停止
miner.stop()
#查看交易持状态：pending=0，说明交易成功
txpool.status
#查看余额
eth.getBalance("0xf647200766920efcc97acb884ef1753620c0006f")
```
