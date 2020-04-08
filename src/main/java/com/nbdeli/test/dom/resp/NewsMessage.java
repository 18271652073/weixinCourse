package com.nbdeli.test.dom.resp;

import java.util.List;

/**
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
public class NewsMessage extends BaseMessage {
    // 图文消息个数，限制为 10 条以内
     private int ArticleCount;
    // 多条图文消息信息，默认第一个 item 为大图
     private List<Article> Articles;

    public NewsMessage() {
    }
}
