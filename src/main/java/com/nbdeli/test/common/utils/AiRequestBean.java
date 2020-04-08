package com.nbdeli.test.common.utils;

import com.nbdeli.test.common.constant.TencentAIConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Dukaixiang
 * @date 2019/4/30.
 * @Version 1.0
 */
public class AiRequestBean {
    public static final String ERROR = "error";
    private static TreeMap<String, String> mParams;

    private AiRequestBean() {
        mParams = new TreeMap<>();
        //时间戳
        String time_stamp = System.currentTimeMillis() / 1000 + "";
        //随机字符串
        String nonce_str = getRandomString(10);
        //appId
        String app_id = String.valueOf(TencentAIConstants.APP_ID_AI);
        //将通用参数设置进map中
        mParams.put("app_id", app_id);
        mParams.put("nonce_str", nonce_str);
        mParams.put("time_stamp", time_stamp);
    }

    /**
     * 随机字符串
     * nonce_str
     * @param length
     * @return
     */
    private static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     *
     * sign
     * TreeMap生成鉴权信息
     */
    private static String generateAppSign() throws UnsupportedEncodingException {
        Set<String> keySet = mParams.keySet();
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = mParams.get(key);
            sb.append("&").append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
        }
        sb.deleteCharAt(0);
        sb.append("&app_key=").append(TencentAIConstants.APP_KEY_AI);
        String sign = MD5s.MD5(sb.toString());
        return sign;
    }

    public static String getResultByURL(String url,Map mapParams) throws UnsupportedEncodingException {
        new AiRequestBean();
        Iterator<Map.Entry<Object, Object>> entries = mapParams.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Object, Object> entry = entries.next();
            mParams.put(entry.getKey().toString(),entry.getValue().toString());
        }
        mParams.put("sign",generateAppSign());
        byte[] result= HttpUtil.doPost(url,mParams,"utf-8");
        return new String(result);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        AiRequestBean aiRequestBean=new AiRequestBean();
//        aiRequestBean.mParams.put("session","10000");
//        aiRequestBean.mParams.put("question","test");
//        aiRequestBean.mParams.put("sign",aiRequestBean.generateAppSign());
//        byte[] result= HttpUtil.doPost("https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat",aiRequestBean.mParams,"utf-8");
//        System.out.println(new String(result));


    }

}
