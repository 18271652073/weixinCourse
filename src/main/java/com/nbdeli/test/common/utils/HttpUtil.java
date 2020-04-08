package com.nbdeli.test.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>Http工具类
 * <p>
 * <p>Http工具类，为系统提供通用Http访问操作方法：
 * <p>
 * <p>1、发送GET请求；
 * <p>2、发送POST请求。
 */
public class HttpUtil {

    /**
     * <p>发送GET请求
     *
     * @param url GET请求地址
     * @return 与当前请求对应的响应内容字节数组
     */
    public static byte[] doGet(String url) {

        return HttpUtil.doGet(url, null, null, 0);
    }

    /**
     * <p>发送GET请求
     *
     * @param url       GET请求地址
     * @param headerMap GET请求头参数容器
     * @return 与当前请求对应的响应内容字节数组
     */
    public static byte[] doGet(String url, Map headerMap) {

        return HttpUtil.doGet(url, headerMap, null, 0);
    }

    /**
     * <p>发送GET请求
     *
     * @param url       GET请求地址
     * @param proxyUrl  代理服务器地址
     * @param proxyPort 代理服务器端口号
     * @return 与当前请求对应的响应内容字节数组
     * @modify 窦海宁, 2012-03-19
     */
    public static byte[] doGet(String url, String proxyUrl, int proxyPort) {

        return HttpUtil.doGet(url, null, proxyUrl, proxyPort);
    }

    /**
     * <p>发送GET请求
     *
     * @param url       GET请求地址
     * @param headerMap GET请求头参数容器
     * @param proxyUrl  代理服务器地址
     * @param proxyPort 代理服务器端口号
     * @return 与当前请求对应的响应内容字节数组
     * @modify 窦海宁, 2012-03-19
     */
    public static byte[] doGet(String url, Map headerMap, String proxyUrl, int proxyPort) {

        byte[] content = null;
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);

        if (headerMap != null) {

            //头部请求信息
            if (headerMap != null) {

                Iterator iterator = headerMap.entrySet().iterator();
                while (iterator.hasNext()) {

                    Entry entry = (Entry) iterator.next();
                    getMethod.addRequestHeader(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        }

        if (StringUtils.isNotBlank(proxyUrl)) {

            httpClient.getHostConfiguration().setProxy(proxyUrl, proxyPort);
        }

        //设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        //postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER , new DefaultHttpMethodRetryHandler());
        InputStream inputStream = null;
        try {

            if (httpClient.executeMethod(getMethod) == HttpStatus.SC_OK) {

                //读取内容
                inputStream = getMethod.getResponseBodyAsStream();
                content = IOUtils.toByteArray(inputStream);
            } else {

                System.err.println("Method failed: " + getMethod.getStatusLine());
            }
        } catch (IOException ex) {

            ex.printStackTrace();
        } finally {

            IOUtils.closeQuietly(inputStream);
            getMethod.releaseConnection();
        }
        return content;
    }

    /**
     * <p>发送POST请求
     *
     * @param url          POST请求地址
     * @param parameterMap POST请求参数容器
     * @return 与当前请求对应的响应内容字符串
     */
    public static String doPostOfStringResult(String url, Map parameterMap, String paramCharset) throws UnsupportedEncodingException {
        byte[] result = HttpUtil.doPost(url, null, parameterMap, paramCharset, null, 0);
        return new String(result, paramCharset);
    }

    /**
     * <p>发送POST请求
     *
     * @param url          POST请求地址
     * @param parameterMap POST请求参数容器
     * @return 与当前请求对应的响应内容字节数组
     */
    public static byte[] doPost(String url, Map parameterMap) {

        return HttpUtil.doPost(url, null, parameterMap, null, null, 0);
    }

    /**
     * <p>发送POST请求
     *
     * @param url          POST请求地址
     * @param parameterMap POST请求参数容器
     * @param paramCharset 参数字符集名称
     * @return 与当前请求对应的响应内容字节数组
     * @modify 窦海宁, 2012-05-21
     */
    public static byte[] doPost(String url, Map parameterMap, String paramCharset) {

        return HttpUtil.doPost(url, null, parameterMap, paramCharset, null, 0);
    }

    /**
     * <p>发送POST请求
     *
     * @param url          POST请求地址
     * @param headerMap    POST请求头参数容器
     * @param parameterMap POST请求参数容器
     * @param paramCharset 参数字符集名称
     * @return 与当前请求对应的响应内容字节数组
     * @modify 窦海宁, 2012-05-21
     */
    public static byte[] doPost(String url, Map headerMap, Map parameterMap, String paramCharset) {

        return HttpUtil.doPost(url, headerMap, parameterMap, paramCharset, null, 0);
    }

    /**
     * <p>发送POST请求
     *
     * @param url          POST请求地址
     * @param parameterMap POST请求参数容器
     * @param paramCharset 参数字符集名称
     * @param proxyUrl     代理服务器地址
     * @param proxyPort    代理服务器端口号
     * @return 与当前请求对应的响应内容字节数组
     */
    public static byte[] doPost(String url, Map parameterMap, String paramCharset, String proxyUrl, int proxyPort) {

        return HttpUtil.doPost(url, null, parameterMap, paramCharset, proxyUrl, proxyPort);
    }

    /**
     * <p>发送POST请求
     *
     * @param url          POST请求地址
     * @param headerMap    POST请求头参数容器
     * @param parameterMap POST请求参数容器
     * @param paramCharset 参数字符集名称
     * @param proxyUrl     代理服务器地址
     * @param proxyPort    代理服务器端口号
     * @return 与当前请求对应的响应内容字节数组
     * @modify 窦海宁, 2012-05-21
     */
    public static byte[] doPost(String url, Map headerMap, Map parameterMap, String paramCharset, String proxyUrl, int proxyPort) {

        byte[] content = null;
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        if (StringUtils.isNotBlank(paramCharset)) {

            postMethod.getParams().setContentCharset(paramCharset);
            postMethod.getParams().setHttpElementCharset(paramCharset);
        }

        if (headerMap != null) {

            //头部请求信息
            if (headerMap != null) {

                Iterator iterator = headerMap.entrySet().iterator();
                while (iterator.hasNext()) {

                    Entry entry = (Entry) iterator.next();
                    postMethod.addRequestHeader(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        }

        Iterator iterator = parameterMap.keySet().iterator();
        while (iterator.hasNext()) {

            String key = (String) iterator.next();
            postMethod.addParameter(key, (String) parameterMap.get(key));
        }

        if (StringUtils.isNotBlank(proxyUrl)) {

            httpClient.getHostConfiguration().setProxy(proxyUrl, proxyPort);
        }

        //设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        //postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER , new DefaultHttpMethodRetryHandler());
        InputStream inputStream = null;
        try {

            if (httpClient.executeMethod(postMethod) == HttpStatus.SC_OK) {

                //读取内容
                inputStream = postMethod.getResponseBodyAsStream();
                content = IOUtils.toByteArray(inputStream);
            } else {

                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
        } catch (IOException ex) {

            ex.printStackTrace();
        } finally {

            IOUtils.closeQuietly(inputStream);
            postMethod.releaseConnection();
        }
        return content;
    }

//    public static void main(String[] args) throws Exception{
////    	StringBuffer strBuf = new StringBuffer();
////    	BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\del.txt"));
////    	String lineTxt = null;
////        while ((lineTxt = reader.readLine()) != null) {
////          strBuf.append(lineTxt);
////        }
////        reader.close();
////        String url = strBuf.toString();
//    	String url = "http://openapi.delib2b.com/chinatower/api/order/submit?town=0&county=2849&creatorPhone=&detailAddress=%E5%8C%97%E4%BA%AC%E6%B5%B7%E6%B7%80%E5%8C%BA%E4%B8%89%E7%8E%AF%E5%88%B0%E5%9B%9B%E7%8E%AF%E4%B9%8B%E9%97%B4%E5%8C%97%E4%BA%AC%E6%B5%B7%E6%B7%80%E5%8C%BA%E5%AE%9A%E6%85%A7%E5%8C%97%E9%87%8C18%E5%8F%B7%E6%A5%BC%E6%B0%B8%E5%85%B4%E8%8A%B1%E5%9B%AD%E5%95%86%E5%8A%A1%E9%85%92%E5%BA%97809&sku=%5B%7B%22skuId%22%3A%22100001753PCS%22%2C%22num%22%3A1.0000%7D%5D&token=f6e87cce38968313c61e1bf7978db57e&mobile=15910402132&creatorMobile=&thirdOrder=PO-dlsd-1020190320103658034888&province=1&email=pengjc%40chinatowercom.cn&name=%E5%90%B4%E5%85%88%E7%94%9F&createdTime=2019-03-20+10%3A36%3A58&phone=&remark=%E5%8F%AA%E5%B7%A5%E4%BD%9C%E6%97%A5%E9%80%81%E8%B4%A7%E3%80%82%E7%BA%BF%E4%B8%8A%E6%B5%8B%E8%AF%95%E4%B8%8B%E5%8D%95&zip=100000&address=%E5%8C%97%E4%BA%AC%E6%B5%B7%E6%B7%80%E5%8C%BA%E5%AE%9A%E6%85%A7%E5%8C%97%E9%87%8C18%E5%8F%B7%E6%A5%BC%E6%B0%B8%E5%85%B4%E8%8A%B1%E5%9B%AD%E5%95%86%E5%8A%A1%E9%85%92%E5%BA%97809&creatorName=%E5%BD%AD%E5%BB%BA%E6%98%A5&city=2800";
//    	Pair<String, Map<String, String>> urlPair = ParamParser.getUrlPair(url);
//        String entryStr = urlPair.getKey();
//    	Map<String, String> paramMap = urlPair.getValue();
//    	paramMap.put("token", "0a9fcf281d098b135ec47da4b6f27770");
//        System.out.println(new String(doPost(entryStr, paramMap)));
//    }

}