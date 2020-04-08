package com.nbdeli.test.dom.req;

import lombok.Data;

/**
 * 链接消息
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class LinkMessage extends BaseMessage {
    // 消息标题
     private String Title;
    // 消息描述
     private String Description;
    // 消息链接
     private String Url;
}
