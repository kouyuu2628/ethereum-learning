package com.web3jlabs.eth.controller;

import com.web3jlabs.eth.service.EncrypedTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * 加密代币控制层
 *
 * @author huangyong
 * @date 2019/12/30 14:03
 */
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
