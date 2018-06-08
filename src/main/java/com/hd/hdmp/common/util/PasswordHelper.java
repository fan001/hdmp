package com.hd.hdmp.common.util;

import com.google.common.base.Strings;
import com.hd.hdmp.entity.StaffinfoEntity;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author fanzhenxing
 * @create 2018/6/6 3:39 PM
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encrptPassword(StaffinfoEntity staffinfoEntity) {
        if (Strings.isNullOrEmpty(staffinfoEntity.getSalt())) {
            staffinfoEntity.setSalt(randomNumberGenerator.nextBytes().toHex());
        }
        String newPassword = new SimpleHash(algorithmName, staffinfoEntity.getPassword(),
                ByteSource.Util.bytes(staffinfoEntity.getCredentialsSalt()), hashIterations).toHex();
        staffinfoEntity.setPassword(newPassword);
    }
}
