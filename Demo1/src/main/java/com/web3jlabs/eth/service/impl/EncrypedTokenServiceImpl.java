package com.web3jlabs.eth.service.impl;

import com.web3jlabs.eth.contracts.EncryptedToken;
import com.web3jlabs.eth.service.EncrypedTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;

/**
 * 加密代币服务层接口实现类
 *
 * @author huangyong
 * @date 2019/12/30 14:13
 */
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
