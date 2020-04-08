package com.nbdeli.test.dom.req;

import lombok.Data;

/**
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;
}
