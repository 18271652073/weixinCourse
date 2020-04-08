package com.nbdeli.test.dom.req;

import lombok.Data;

/**
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class BaseMessage {
    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个 OpenID）
     private String FromUserName;
    // 消息创建时间 （整型）
     private long CreateTime;
    // 消息类型（text/image/location/link）
     private String MsgType;
    // 消息 id，64 位整型
     private long MsgId;
}
