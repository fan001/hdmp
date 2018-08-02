package com.hd.hdmp.common.rfc;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

@Slf4j
public class RfcConnector64 {
    public static String D12 = "10.55.66.17";
    public static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
    public static SimpleDateFormat yms = new SimpleDateFormat("yyyy-MM-dd");

    public RfcConnector64() {
    }

    public static void createDataFile(String name, String suffix, Properties properties) {
        File cfg = new File(name + "." + suffix);
        log.error("@@@@@@@@@@@@@@@="+cfg.getAbsolutePath());
        if(!cfg.exists()) {
            try {
                FileOutputStream e = new FileOutputStream(cfg, false);
                properties.store(e, "for connect SAP !");
                e.close();
                log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Rfc config file create success!");
            } catch (Exception var5) {
                throw new RuntimeException("Unable to create the destination file " + cfg.getName(), var5);
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Rfc config file load success!");
    }

    static {
        log.error("Rfc init start.................................");
        Properties logonProperties = new Properties();
        logonProperties.setProperty("jco.client.ashost", D12);
        logonProperties.setProperty("jco.client.sysnr", "00");
        logonProperties.setProperty("jco.client.client", "600");
        logonProperties.setProperty("jco.client.user", "CRMRFC");
        logonProperties.setProperty("jco.client.passwd", "Hpi12345");
        logonProperties.setProperty("jco.client.lang", "zh");
        logonProperties.setProperty("jco.destination.pool_capacity", "3");
        logonProperties.setProperty("jco.destination.peak_limit", "10");
        createDataFile(ABAP_AS_POOLED, "jcoDestination", logonProperties);
    }
}