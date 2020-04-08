package com.nbdeli.test.dom.req;

import lombok.Data;

/**
 * 音频消息
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class VoiceMessage extends BaseMessage {
    // 媒体 ID
     private String MediaId;
    // 语音格式
     private String Format;
}
