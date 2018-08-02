package com.hd.hdmp.common.util.wework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hd.hdmp.entity.model.pojo.Token;
import com.hd.hdmp.entity.wework.WeworkUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    //凭证获取
    public static final  String token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT";
    public static final  String ticket_url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";


    /**
     * 发送https请求
     * @param requsetUrl 请求地址
     * @param requestMethod 请求方式post/get
     * @param outputStr 写入微信服务器的数据
     */
    public static String httpsRequestString(String requsetUrl, String requestMethod, String outputStr){
        try{
            System.out.println("httpsRequestString::return::json");
            //创建SSLContext对象，并使用指定的信任管理器初始化
            TrustManager[]  tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm,new SecureRandom());
            //从sslContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requsetUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            //设置属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            //当outoutStr不为null时，向输出流写数据
            if(null != outputStr){
                OutputStream os = conn.getOutputStream();
                //注意编码格式
                os.write(outputStr.getBytes("UTF-8"));
                os.close();
            }
            //从输入流读取返回内容
            InputStream inputStreams = conn.getInputStream();
            InputStreamReader inputStreamReadersr = new InputStreamReader(inputStreams,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReadersr);
            String str = null;
            StringBuffer buff = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null){
                buff.append(str);
            }
            //释放资源
            bufferedReader.close();
            inputStreamReadersr.close();
            inputStreams.close();
            conn.disconnect();
            //返回json数据
            logger.info(buff.toString());
            return buff.toString();
        }catch (ConnectException ce){
            logger.error("CommonUtil:连接超时：{}", ce);
        }catch (Exception e){
            logger.error("CommonUtil:https请求异常：{}", e);
        }
        return null;
    }

    /**
     * 发送https请求
     * @param requsetUrl 请求地址
     * @param requestMethod 请求方式post/get
     * @param outputStr 写入微信服务器的数据
     */
    public static Map<String, Object> httpsRequestMap(String requsetUrl, String     requestMethod, String outputStr){
        Map map = new HashMap();
        try{
            System.out.println("httpsRequesthttpsRequesthttpsRequesthttpsRequest");
            //创建SSLContext对象，并使用指定的信任管理器初始化
            TrustManager[]  tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm,new SecureRandom());
            //从sslContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requsetUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            //设置属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            //当outoutStr不为null时，向输出流写数据
            if(null != outputStr){
                OutputStream os = conn.getOutputStream();
                //注意编码格式
                os.write(outputStr.getBytes("UTF-8"));
                os.close();
            }
            //从输入流读取返回内容
            InputStream inputStreams = conn.getInputStream();
            InputStreamReader inputStreamReadersr = new InputStreamReader(inputStreams,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReadersr);
            String str = null;
            StringBuffer buff = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null){
                buff.append(str);
            }
            //释放资源
            bufferedReader.close();
            inputStreamReadersr.close();
            inputStreams.close();
            conn.disconnect();
            //返回json数据
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(buff.toString());
            map = mapper.readValue(buff.toString(), Map.class);
        }catch (ConnectException ce){
            logger.error("CommonUtil:连接超时：{}", ce);
        }catch (Exception e){
            logger.error("CommonUtil:https请求异常：{}", e);
        }
        return map;
    }

    public static String commonCommitHttp(String str_url,String method,String string){
        StringBuffer buffer = new StringBuffer();
        try{
            URL url = new URL(str_url);
            HttpURLConnection sendConn = (HttpURLConnection)url.openConnection();
            sendConn.setDoOutput(true);
            sendConn.setDoInput(true);
            sendConn.setRequestMethod(method);
            if(null != string){
                OutputStream outputStream = sendConn.getOutputStream();
                outputStream.write(string.getBytes("UTF-8"));
            }
            InputStream inputStream = sendConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = "";
            while((str = bufferedReader.readLine())!=null){
                System.out.println(str);
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            sendConn.disconnect();
            logger.info(buffer.toString());
        }catch (Exception e){
            logger.error("CommonUtil:http提交失败：{}"+ e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
        return buffer.toString();
    }

    /**
     * 获取接口访问凭证
     * @param corpid 企业微信号id
     * @param secret 企也应用的秘钥
     * @return Token
     */
    public static Token getToken(String corpid, String secret){
        Token token = null;
        String requestUrl = token_url.replace("ID",corpid).replace("SECRECT",secret);
        System.out.println("===>>>>"+requestUrl);
        //发起GET请求获取凭证
        Map map = httpsRequestMap(requestUrl, "GET", null);
        System.out.println(map);
        if(null != map){
            System.out.println("errcode="+map.get("errcode")+",,errmsg=="+map.get("errmsg"));
            token = new Token();
            token.setAccessToken(map.get("access_token").toString());
            token.setExpiresIn(Long.valueOf(map.get("expires_in").toString()));
            logger.info("access_token="+token.getAccessToken());
        }
        return token;
    }

    /**
     * 获取接口访问凭证
     * @param access_token 企业微信号id
     * @return Token
     */
    public static String getTicket(String access_token){
        String requestUrl = ticket_url.replace("ACCESS_TOKEN",access_token);
        //发起GET请求获取凭证
        Map map = httpsRequestMap(requestUrl, "GET", null);
        System.out.println(map);
        String ticket = "";
        if(null != map){
            System.out.println("errcode="+map.get("errcode")+",,errmsg=="+map.get("errmsg"));
            ticket = map.get("ticket").toString();
            logger.info("ticket="+ticket);
        }
        return ticket;
    }

    /**
     * 根据code获取用户的userid
     * @param access_token
     * @param code
     * @return 获取的userid
     */
    public static String getUserIDFromOauth2(String access_token,String code){
        logger.error("access_token==="+access_token+":::code="+code);
        logger.error("begin::"+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
        String userId = "";
        //根据code获取用户的userid
//        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
//        url = url.replace("ACCESS_TOKEN",access_token).replace("CODE",code);
//        //正式需处理此处
//        Map map = httpsRequestMap(url,"GET","");
//        logger.info("CommonBusiness:::map::"+map);
//        if(map != null){
//            userId = map.get("UserId").toString();
//            logger.error("userId:：：：：：:"+userId);
//        }
        userId = "ChenYongCheng";
        logger.error("end::"+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
        return userId;
    }
    /**
     * 获取企业部门信息
     * @param access_token 使用通讯录secret
     * @param id 为空则查询全部
     * @return 获取的公司的id
     */
    public static String getDepartmentid(String access_token, String id){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN";
        if(id==null||id == ""){
            url = url.replace("ACCESS_TOKEN", access_token);
        }else{
            url = url.replace("ACCESS_TOKEN", access_token)+"&id="+id;
        }
        System.out.println("getDepartmentUrl::"+url);
        String json = httpsRequestString( url, "GET","");
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            String department = node.get("department").toString();
            List<Map<String ,Object>> list = mapper.readValue(department, List.class);
            System.out.println("@@@@@@@@@@@"+department);
            return list.get(0).get("id").toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据部门id查询部门下的所有用户详细信息
     * @param access_token 通讯录secret
     * @param departmentid 根部门id
     * @return 获取的userid的json
     */
    public static List<WeworkUserModel> getUserByDepartmentId(String access_token, String departmentid){
        //String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=1";
        url = url.replace("ACCESS_TOKEN", access_token).replace("DEPARTMENT_ID", departmentid);
        String json = httpsRequestString( url, "GET","");
        System.out.println("json::"+json);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            String userlist = node.get("userlist").toString();
            List<WeworkUserModel> list_model = mapper.readValue(userlist, new TypeReference<List<WeworkUserModel>>() {});

            System.out.println("list_mode::"+list_model);
            return list_model;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据用户id查询用户详细信息
     * @param access_token 通讯录secret
     * @param userid 查询用户详细信息
     * @return 获取的userid的json
     */
    public static String getUserByUserID(String access_token, String userid){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

        url = url.replace("ACCESS_TOKEN", access_token).replace("USERID", userid);

        String json = httpsRequestString( url, "GET","");

        return json;
    }

    public static String sendMessage(String access_token, String message){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",access_token);
        String json = httpsRequestString(url, "POST", message);
        System.out.println(json);
        return  json;
    }

    public static String translate(Float latitude, Float longitude){

        String url = "http://apis.map.qq.com/ws/coord/v1/translate?locations="+latitude+","+longitude+"&type=1&key=HVJBZ-AMR33-2LL3T-YPQIS-PJJRF-FLFHO";
        String json = commonCommitHttp(url, "GET", null);
        System.out.println("json==>>"+json);
        return json;
    }
}