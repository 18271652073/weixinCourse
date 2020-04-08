package com.nbdeli.test.dom.resp;

import lombok.Data;

/**
 * 消息基类（公众帐号 -> 普通用户）
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class BaseMessage {
    // 接收方帐号（收到的 OpenID）
     private String ToUserName;
    // 开发者微信号
     private String FromUserName;
    // 消息创建时间 （整型）
     private long CreateTime;
    // 消息类型（text/music/news）
     private String MsgType;
    // 位 0x0001 被标志时，星标刚收到的消息
     private int FuncFlag;
}
