package com.nbdeli.test.service;

import com.nbdeli.test.common.utils.MessageUtil;
import com.nbdeli.test.dom.resp.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Slf4j
@Service
public class CoreService {

    @Autowired
    private ApiService apiService;

    public static String type = "";

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                String content = requestMap.get("Content");
                Map contentMap = new HashMap<>();
                contentMap.put("1", "AI聊天");
                contentMap.put("2", "美食识别");
                contentMap.put("3", "图片鉴黄");
                if (StringUtils.isEmpty(type)) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("您好，我是小 q，请回复数字选择服务：").append("\n\n");
                    buffer.append("1 AI聊天").append("\n");
                    buffer.append("2 美食识别").append("\n");
                    buffer.append("3 图片鉴黄").append("\n");
                    buffer.append("回复“?”显示此帮助菜单");
                    respContent = buffer.toString();
                    Iterator<Map.Entry<Object, Object>> entries = contentMap.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry<Object, Object> entry = entries.next();
                        if (entry.getValue().toString().contains(content)) {
                            if (entry.getKey().equals("1")) {
                                respContent = "可以开始聊天了！";
                                type = "1";
                            } else if (entry.getKey().equals("2")) {
                                respContent = "请输入图片！";
                                type = "2";
                            } else if (entry.getKey().equals("3")) {
                                respContent = "请输入图片！";
                                type = "3";
                            }
                        }
                    }
                    if (content.equals("1")) {
                        respContent = "可以开始聊天了！";
                        type = "1";
                    } else if (content.equals("2")) {
                        respContent = "请输入图片！";
                        type = "2";
                    } else if (content.equals("3")) {
                        respContent = "请输入图片！";
                        type = "3";
                    }
                } else if (type.equals("1")) {
                    respContent = apiService.getResponseStr(content);
                } else if (type.equals("2")) {
                    respContent = apiService.getFoodMsg(content);
                } else if (type.equals("3")) {
                    respContent = apiService.getPornMsg(content);
                }
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                String picUrl = requestMap.get("PicUrl");
                respContent = apiService.getPornMsg(picUrl);
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                }
            }
            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}
