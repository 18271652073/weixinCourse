package com.nbdeli.test.dom.resp;

import lombok.Data;

/**
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@Data
public class Article {
    // 图文消息名称
     private String Title;
    // 图文消息描述
     private String Description;
    // 图片链接，支持 JPG、PNG 格式，较好的效果为大图 640*320，小图 80*80，限制图片 链接的域名需要与开发者填写的基本资料中的 Url 一致
     private String PicUrl;
    // 点击图文消息跳转链接
     private String Url;
}
