import com.web3jlabs.eth.contracts.EncryptedToken;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Description
 *
 * @author huangyong
 * @date 2019/12/23 14:18
 */
public class Test {

    private static Web3j web3 = Web3j.build(new HttpService("http://192.168.11.46:8545/"));
    private static Admin admin = Admin.build(new HttpService("http://192.168.11.46:8545/"));
    private static EncryptedToken encryptedToken;

    public static void main(String[] args) throws IOException, CipherException {
//        // 获取以太坊基本信息
//        queryBasicInfo();
//        // 创建账号
//        createAccount("huangyong002");
//        // 发布合约实例
//        deployContract();
//        // 加载合约实例
        loadContract();
//        // 查询账号的合约代币余额
        balanceOf("0xd729dadd63721093358cec5468dd8367f7e60516");
//        balanceOf("0xa9a4d3b2b2fe511f918015f7a2bd79b3c306b5c7");
//        // 进行合约代币转账
//        transfer("0xa9a4d3b2b2fe511f918015f7a2bd79b3c306b5c7", BigInteger.valueOf(999));
//        // 查询账号的合约代币余额
//        balanceOf("0xd729dadd63721093358cec5468dd8367f7e60516");
//        balanceOf("0xa9a4d3b2b2fe511f918015f7a2bd79b3c306b5c7");
    }

    private static void queryBasicInfo() {
        // defaults to http://localhost:8545/
        Web3j web3 = Web3j.build(new HttpService());

        try {
            // web3_clientVersion returns the current client version.
            Web3ClientVersion clientVersion = web3.web3ClientVersion().send();
            System.out.println("clientVersion:" + clientVersion.getWeb3ClientVersion());

            //eth_blockNumber returns the number of most recent block.
            EthBlockNumber blockNumber = web3.ethBlockNumber().send();
            System.out.println("blockNumber:" + blockNumber.getBlockNumber().toString());

            //eth_gasPrice, returns the current price per gas in wei.
            EthGasPrice gasPrice = web3.ethGasPrice().send();
            System.out.println("gasPrice:" + gasPrice.getGasPrice().toString());

        } catch (IOException ex) {
            throw new RuntimeException("Error whilst sending json-rpc requests", ex);
        }
    }

    private static void deployContract() throws IOException, CipherException {
        try {
            Credentials credentials = WalletUtils.loadCredentials("huangyong001",
                    "C:\\Users\\THINK\\AppData\\Local\\Ethereum\\selfchain\\keystore\\UTC--2019-12-24T06-24-57.131296400Z--d729dadd63721093358cec5468dd8367f7e60516");

            EncryptedToken contract = EncryptedToken.deploy(
                    web3, credentials, new StaticGasProvider(BigInteger.valueOf(100000), BigInteger.valueOf(2000000))).send();
            System.out.println("contract.getContractAddress()：" + contract.getContractAddress());
            System.out.println("contract.getContractBinary()：" + contract.getContractBinary());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载合约
     */
    private static void loadContract() {
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials("huangyong001",
                    "C:\\Users\\THINK\\AppData\\Local\\Ethereum\\selfchain\\keystore\\UTC--2019-12-24T06-24-57.131296400Z--d729dadd63721093358cec5468dd8367f7e60516");
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }
        encryptedToken = EncryptedToken.load("0xa79f43e8a9a7ce3bf1269f97a2d1a3700c2f1a9f", web3, credentials, new StaticGasProvider(BigInteger.valueOf(100000), BigInteger.valueOf(2000000)));

        try {
            encryptedToken.isValid();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询账号的合约代币余额
     *
     * @param address 账号地址
     */
    private static void balanceOf(String address) {
        try {
            BigInteger balance = encryptedToken.balanceOf(address).send();
            System.out.println(address + "的合约代币余额:" + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 代币合约转账
     */
    private static void transfer(String toAddress, BigInteger amount) {
        try {
            TransactionReceipt send = encryptedToken.transfer(toAddress, amount).send();
            System.out.println("代币合约转账：" + send.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建账号
     *
     * @param pwd 密码
     * @return 账号
     */
    private static String createAccount(String pwd) {
        try {
            NewAccountIdentifier identifier = admin.personalNewAccount(pwd).send();
            if (identifier.getError() != null) {
                System.err.println("new account fail.");
                System.out.println(":" + identifier.getError().getCode());
                System.out.println(":" + identifier.getError().getData());
                System.out.println(":" + identifier.getError().getMessage());
            }
            return identifier.getAccountId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
