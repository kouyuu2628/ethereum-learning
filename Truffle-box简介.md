## Truffle Box
- 一组应用模板，帮助开发人员在以太坊区块链上使用truffle快速构建应用。
```
https://github.com/truffle-box
```
- 里面一共有28种合约模板，比如使用truffle进行初始化时使用的`truffle init`其实就是拉取了模板中的`bare-box`,还有`元币合约`、`宠物商店合约`等。  

- `truffle init`指令去拉取代码时，访问的是国外地址，很难下载成功。既然该指令是在下载工程代码，完全可以通过从git下载代码去替代该指令。  


```
#比如
truffle init
#等价于
git clone https://github.com/truffle-box/bare-box
```
```
#同理
truffle unbox tutorialtoken
#等价于
git clone https://github.com/truffle-box/tutorialtoken-box
```
```
truffle unbox pet-shop
#等价于
git clone https://github.com/truffle-box/pet-shop-box
```
