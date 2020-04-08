package com.nbdeli.test.dom.req;

import lombok.Data;

/**
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;
}
