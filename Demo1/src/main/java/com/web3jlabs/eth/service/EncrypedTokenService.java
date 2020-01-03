package com.web3jlabs.eth.service;

/**
 * 加密代币服务层接口
 *
 * @author huangyong
 * @date 2019/12/30 14:12
 */
public interface EncrypedTokenService {

    /**
     * 查询账户的合约代币余额
     *
     * @param address 账户地址
     * @return 合约代币余额
     */
    String balanceOf(String address);
}
