package com.hd.hdmp.common.rfc;

import com.sap.conn.jco.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class RfcCommon {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 调用RFC接口获取数据公用方法
     * @param importMap import类型查询条件参数Map key-参数名 value-参数值
     * @param tableMap talbe类型查询条件参数Map key-table名 value-table内容
     * @param structureList structure类型查询条件参数Map key-structure名 value-structure内容
     * @param functionName 调用的SAP函数名
     * @return 返回getTableParameterList
     * @throws Exception
     */
    public static JCoParameterList getRfcData(Map<String, Object> importMap, Map<String, Map<String,Object>> tableMap, List<Map<String, Map<String,Object>>> structureList, String functionName, String type) throws Exception{
        // 获取链接
        JCoDestination destination = JCoDestinationManager.getDestination(RfcConnector64.ABAP_AS_POOLED);
        try{
            destination.ping();
        } catch (Exception e) {
            log.error("SAP connection error!!");
//            ProcessDataBus.getInstance().getResultSet().setMessage("SAP生产机连接异常！..............");
            e.printStackTrace();
            throw new RuntimeException("SAP connection error.");
        }
        JCoFunction function = destination.getRepository().getFunction(functionName);
        if (function == null) {
            log.error("function not found in SAP");
            throw new RuntimeException("function not found in SAP.");
        }
        // 添加入口参数//如果有业务类型编码，则增加至输入参数中
        JCoParameterList input = function.getImportParameterList();

        if(type == null || type == ""){
            input.setValue("IV_TYPE", "A001");
        }else{
            input.setValue("IV_TYPE", type);
        }

        // importMap类型赋值
        if (importMap != null && importMap.size() > 0){
            for (Map.Entry<String, Object> entry : importMap.entrySet()){
                String key = entry.getKey();
                String value = isEmptyDo(entry.getValue());
                if (!isEmpty(key) && !isEmpty(value)){
                    function.getImportParameterList().setValue(key, value);
                }
                System.out.println();
            }
        }
        // table类型数据赋值
        if (tableMap != null && tableMap.size() > 0){
            System.out.println(tableMap);
            for (Map.Entry<String, Map<String,Object>> entry : tableMap.entrySet()){
                String key = entry.getKey();
                Map<String, Object> valueMap = entry.getValue();
                JCoTable jCoTable = function.getImportParameterList().getTable(key);
                if (valueMap != null && valueMap.size() > 0) {
                    jCoTable.appendRow();
                    for (Map.Entry<String, Object> subEntry : valueMap.entrySet()) {
                        String subKey = subEntry.getKey();
                        String subValue = isEmptyDo(subEntry.getValue());
                        if (!isEmpty(key) && !isEmpty(subValue)) {
                            jCoTable.setValue(subKey, subValue);
                        }
                    }
                }
            }
        }
        // structure类型赋值
        if(structureList != null && structureList.size() > 0){
            for(int i=0 ;i<structureList.size(); i++){
                Map<String, Map<String,Object>> structureMap = structureList.get(i);
                if (structureMap != null && structureMap.size() > 0){
                    for (Map.Entry<String, Map<String,Object>> entry : structureMap.entrySet()){
                        String key = entry.getKey();
                        Map<String,Object> valueMap = entry.getValue();
                        JCoStructure jCoFields = function.getImportParameterList().getStructure(key);
                        if (valueMap != null && valueMap.size()>0){
                            for (Map.Entry<String, Object> subEntry: valueMap.entrySet()){
                                String subKey = subEntry.getKey();
                                String subValue = isEmptyDo(subEntry.getValue());
                                if(!isEmpty(subKey) && !isEmpty(subValue)) {
                                    System.out.println(subKey.toUpperCase()+"::"+subValue);
                                    log.info(subKey.toUpperCase()+"::"+subValue);
                                    jCoFields.setValue(subKey.toUpperCase(), subValue);
                                }
                            }
                        }
                    }
                }
            }
        }


        // 执行
        System.out.println("---------------开始调用接口" + functionName + "---------------");
        long beg_ms = System.currentTimeMillis();
        function.execute(destination);

        // 结果
        JCoParameterList jCoParameterList = function.getExportParameterList();
        long end_ms = System.currentTimeMillis();
        System.out.println("---------------接口调用结束，用时" + (end_ms-beg_ms) + "毫秒---------------");

        return jCoParameterList;
    }

    public static JCoParameterList getRfcDataNew(Map<String, Object> importMap, List<Map<String, Map<String,Object>>> tableList, List<Map<String, Map<String,Object>>> structureList, String functionName, String type) throws Exception{
        // 获取链接
        JCoDestination destination = JCoDestinationManager.getDestination(RfcConnector64.ABAP_AS_POOLED);
        try{
            destination.ping();
        } catch (Exception e) {
            log.error("SAP connection error!!!");
//            ProcessDataBus.getInstance().getResultSet().setMessage("SAP生产机连接异常！..............");
            e.printStackTrace();
            throw new RuntimeException("SAP connection error.");
        }
        JCoFunction function = destination.getRepository().getFunction(functionName);
        if (function == null) {
            log.error("function not found in SAP");
            throw new RuntimeException("function not found in SAP.");
        }
        System.out.println("type===="+type);

        // importMap类型赋值
        if (importMap != null && importMap.size() > 0){
            for (Map.Entry<String, Object> entry : importMap.entrySet()){
                String key = entry.getKey();
                String value = isEmptyDo(entry.getValue());
                if (!isEmpty(key) && !isEmpty(value)){
                    function.getImportParameterList().setValue(key, value);
                }
            }
        }
        // table类型数据赋值
        if(tableList != null && tableList.size() > 0) {
            for(int i=0 ;i<tableList.size(); i++) {
                Map<String, Map<String,Object>> tableMap = tableList.get(i);
                if (tableMap != null && tableMap.size() > 0) {
                    System.out.println(tableMap);
                    for (Map.Entry<String, Map<String, Object>> entry : tableMap.entrySet()) {
                        String key = entry.getKey();
                        Map<String, Object> valueMap = entry.getValue();
                        JCoTable jCoTable = function.getImportParameterList().getTable(key);
                        if (valueMap != null && valueMap.size() > 0) {
                            jCoTable.appendRow();
                            for (Map.Entry<String, Object> subEntry : valueMap.entrySet()) {
                                String subKey = subEntry.getKey();
                                String subValue = isEmptyDo(subEntry.getValue());
                                if (!isEmpty(key) && !isEmpty(subValue)) {
                                    jCoTable.setValue(subKey, subValue);
                                }
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
        // structure类型赋值
        if(structureList != null && structureList.size() > 0){
            for(int i=0 ;i<structureList.size(); i++){
                Map<String, Map<String,Object>> structureMap = structureList.get(i);
                if (structureMap != null && structureMap.size() > 0){
                    for (Map.Entry<String, Map<String,Object>> entry : structureMap.entrySet()){
                        String key = entry.getKey();
                        Map<String,Object> valueMap = entry.getValue();
                        JCoStructure jCoFields = function.getImportParameterList().getStructure(key);
                        if (valueMap != null && valueMap.size()>0){
                            for (Map.Entry<String, Object> subEntry: valueMap.entrySet()){
                                String subKey = subEntry.getKey();
                                String subValue = isEmptyDo(subEntry.getValue());
                                if(!isEmpty(subKey) && !isEmpty(subValue)) {
                                    System.out.println(subKey.toUpperCase()+"::"+subValue);
                                    jCoFields.setValue(subKey.toUpperCase(), subValue);
                                }
                            }
                        }
                    }
                }
            }
        }


        // 执行
        System.out.println("---------------开始调用接口" + functionName + "---------------");
        long beg_ms = System.currentTimeMillis();
        function.execute(destination);

        // 结果
        JCoParameterList jCoParameterList = function.getExportParameterList();
        long end_ms = System.currentTimeMillis();
        System.out.println("---------------接口调用结束，用时" + (end_ms-beg_ms) + "毫秒---------------");

        return jCoParameterList;
    }

    /**
     * 将JcoTable的数据存入List
     * @param jCoTable
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> jcoTableToList(JCoTable jCoTable) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        System.out.println("jCoTable.getNumRows====="+jCoTable.getNumRows());
        for (int row = 0; row < jCoTable.getNumRows(); row++) {
            jCoTable.setRow(row);
            Map<String, Object> map = new HashMap<String, Object>();
            for (int col = 0; col < jCoTable.getNumColumns(); col++) {
                String type = jCoTable.getMetaData().getTypeAsString(col); // 数据类型
                String key = jCoTable.getMetaData().getName(col).toLowerCase();
                String value = isEmptyDo(jCoTable.getValue(col));
                if ("DATE".equals(type)) { //日期类型转换
                    if (!("".equals(value))) {
                        Date d = (Date) jCoTable.getValue(col);
                        value = dateFormat.format(d);
                    }
                }
                /*if ("TIME".equals(type)) { //时间类型转换
                    if (!("".equals(value))) {
                        Date d = (Date) jCoTable.getValue(col);
                        value = timeFormat.format(d);
                    }
                }*/
                System.out.println("key="+key+"===value=="+value);
                map.put(key, value);
            }
            resultList.add(map);
        }
        return resultList;
    }

    /**
     * 将JCoStructure的数据存入Map
     * @param jCoStructure
     * @return
     * @throws Exception
     */
    public static Map<String, Object> jcoStructureToMap(JCoStructure jCoStructure) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        log.info("jcoStructureToMap::"+jCoStructure.toXML());
        for(JCoField field : jCoStructure){
            String type = field.getTypeAsString();
            String key = field.getName().toLowerCase();
            String value = isEmptyDo(field.getValue());
            if ("DATE".equals(type)) { //日期类型转换
                if (!("".equals(value))) {
                    Date d = (Date) field.getValue();
                    value = dateFormat.format(d);
                }
            }
            System.out.println("key="+key+"::value=="+value);
            map.put(key, value);
        }
        return map;
    }

    public static boolean isEmpty(String s) {
        return null == s || 0 == s.trim().length() || s.trim().equals("null");
    }

    public static String isEmptyDo(String s) {
        return isEmpty(s) ? "" : s;
    }

    public static String isEmptyDo(Object o) {
        return null == o ? "" : isEmptyDo(o.toString());
    }

    public static String getBinary(String path){
        File file = new File(path);
        byte[] by = new byte[(int) file.length()];
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        try {
            InputStream is = new FileInputStream(file);
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);

            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            by = bytestream.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.toString(by);
    }

    public static String imgTo16(String path){
        String str= "";
        FileInputStream fis = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try
        {
            fis = new FileInputStream(path);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1)
            {
                bos.write(buff, 0, len);
            }
            bos.flush();
            // 得到图片的字节数组
            byte[] result = bos.toByteArray();
            System.out.println("++++==" + result.length);

            // 字节数组转成十六进制
            str = byte2HexStr(result);
            System.out.println("++++" + str);
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    public static String byte2HexStr(byte[] bytes){
        /*String hs = "";
        String stmp = "";
        for (int n = 0; n < bytes.length; n++)
        {
            stmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }*/
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }
        return buf.toString().toUpperCase();
    }

    public static byte[] imgToByte(String path){
        File file = new File(path);
        byte[] result = new byte[(int) file.length()];

        try
        {
            StringBuffer sb = new StringBuffer();
            FileInputStream fis = new FileInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1)
            {
                bos.write(buff, 0, len);
            }
            // 得到图片的字节数组
            result = bos.toByteArray();
            System.out.println("++++==" + result.length);


        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    public static Map<String ,Object> getFileInput(String path){
        Map<String, Object> map = new HashMap();
        String temp[] = path.trim().split("/");
        String fileName = temp[temp.length-1];
        String postfix = fileName.split("\\.")[1].toUpperCase();
        map.put("FILENAME",fileName);
        map.put("LANGUAGE","ZH");
        map.put("ATTA_TYPE",postfix);
        String content = imgTo16(path);
        /*byte[] result = imgToByte(path);
         String content  = binary(result, 2);*/
        map.put("ATTA_CONTENT",content);
        map.put("ATTA_SIZE","");
        return map;
    }

}