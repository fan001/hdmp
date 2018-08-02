package com.hd.hdmp.common.util.wework;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * User: admin
 * Date: 16-11-15
 * Time: 下午4:14
 * 自定义信任管理器类
 */
public class MyX509TrustManager implements X509TrustManager {
    @Override //检查客户端证书
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
    }

    @Override  //检查服务端证书
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
    }

    @Override  //返回受信任证书数组
    public X509Certificate[] getAcceptedIssuers() {
        //return new X509Certificate[0];  //To change body of implemented methods use File | Settings | File Templates.
        return null;//全部信任
    }
}
