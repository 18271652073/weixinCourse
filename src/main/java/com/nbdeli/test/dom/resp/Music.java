package com.nbdeli.test.dom.resp;

import lombok.Data;

/**
 * 音乐 model
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class Music {
    // 音乐名称
    private String Title;
    // 音乐描述
     private String Description;
    // 音乐链接
     private String MusicUrl;
    // 高质量音乐链接，WIFI 环境优先使用该链接播放音乐
     private String HQMusicUrl;
}
