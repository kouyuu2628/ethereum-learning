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
![配置文件.png](https://upload-images.jianshu.io/upload_images/20507398-6b2f6ac2b8bec5ed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
## 将合约文件编译成java文件
`pom.xml`中添加`web3j-maven-plugin`,github有详细说明
```
https://github.com/web3j/web3j-maven-plugin
```
![web3j-maven-plugin.png](https://upload-images.jianshu.io/upload_images/20507398-98905429ec8f5363.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
下载完成后，执行`web3j:generate-sources`进行编译
![编译sol生成java.png](https://upload-images.jianshu.io/upload_images/20507398-1bc6fb1bc3a8d563.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
查看生成的`EncryptedToken.java`,里面有调用合约内部方法的接口，以及发布、加载合约的接口
```
package com.web3jlabs.eth.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class EncryptedToken extends Contract {
    private static final String BINARY = "6080604052620a2c2a60005534801561001757600080fd5b5060008054338252600160205260409091205561016e806100396000396000f30060806040526004361061004b5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166370a082318114610050578063a9059cbb14610090575b600080fd5b34801561005c57600080fd5b5061007e73ffffffffffffffffffffffffffffffffffffffff600435166100c3565b60408051918252519081900360200190f35b34801561009c57600080fd5b506100c173ffffffffffffffffffffffffffffffffffffffff600435166024356100eb565b005b73ffffffffffffffffffffffffffffffffffffffff1660009081526001602052604090205490565b33600090815260016020526040902054811061010357fe5b336000908152600160205260408082208054849003905573ffffffffffffffffffffffffffffffffffffffff93909316815291909120805490910190555600a165627a7a72305820a2c1565c84561d019a83fae1e72a7ed208136418bb132235819ba76883d1c6810029";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_TRANSFER = "transfer";

    @Deprecated
    protected EncryptedToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EncryptedToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EncryptedToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EncryptedToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static EncryptedToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EncryptedToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EncryptedToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EncryptedToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EncryptedToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EncryptedToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EncryptedToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EncryptedToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EncryptedToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EncryptedToken.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<EncryptedToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EncryptedToken.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EncryptedToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EncryptedToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EncryptedToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EncryptedToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}

```
## 利用web3j操作合约
```
#EncrypedTokenServiceImpl.java
@Service
public class EncrypedTokenServiceImpl implements EncrypedTokenService {

    /**
     * 私有链节点IP
     */
    @Value("${ethereum.ip}")
    private String ip;
    /**
     * 合约发布账户密码
     */
    @Value("${ethereum.contract.password}")
    private String contractPassword;
    /**
     * 合约发布账户keystore文件名称
     */
    @Value("${ethereum.contract.keystore}")
    private String contractKeystore;
    /**
     * 合约实例地址
     */
    @Value("${ethereum.contract.address}")
    private String contractAddress;
    /**
     * 文件存放路径
     */
    @Value("${staticResourcePath}")
    private String staticResourcePath;

    /**
     * 查询账户的合约代币余额
     *
     * @param address 账户地址
     * @return 合约代币余额
     */
    @Override
    public String balanceOf(String address) {
        try {
            // 创建web3j实例
            Web3j web3 = Web3j.build(new HttpService(ip));
            // 加载合约
            Credentials credentials = WalletUtils.loadCredentials(contractPassword, staticResourcePath + contractKeystore);
            EncryptedToken encryptedToken = EncryptedToken.load(contractAddress, web3, credentials, new StaticGasProvider(BigInteger.valueOf(100000), BigInteger.valueOf(2000000)));
            // 合约校验
            if (!encryptedToken.isValid()) {
                System.err.println("合约不合法");
                throw new IOException("合约不合法");
            }
            // 调用合约方法，查询余额
            BigInteger balance = encryptedToken.balanceOf(address).send();
            System.out.println(address + "的合约代币余额:" + balance);

            return balance.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询账户的合约代币余额异常");
        }
        return null;
    }
}
```
## 提供restful接口调用服务层，查询代币余额
```
#EncryptedTokenController.java
@RestController
@RequestMapping("/encryptedToken")
public class EncryptedTokenController {

    @Autowired
    private EncrypedTokenService encrypedTokenService;

    /**
     * 查询账号的合约代币余额
     *
     * @param address 账号地址
     */
    @GetMapping("/balanceOf")
    public String balanceOf(@PathParam("address") String address) {
        return encrypedTokenService.balanceOf(address);
    }
}
```
## 通过页面查询合约的代币余额
启动springboot以及启动连接的私有链节点
```
#address为合约地址
http://localhost:8881/Demo1/encryptedToken/balanceOf?address=0xd729dadd63721093358cec5468dd8367f7e60516
```
> 所谓的激活合约，本质上就是调用合约实例中的方法。  
激活合约，发起交易后，需要矿工挖矿，才会将交易打包进区块。  
但是此处查询余额的方法是‘读’方法，不需要花费比特币，因此不需要挖矿。  
页面会直接返回用户的代币余额。

※文中引用的所有代码和配置，都可在Demo1目录下查看。