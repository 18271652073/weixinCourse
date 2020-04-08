package com.nbdeli.test.dom.jsonentity;

import lombok.Data;

/**
 * @author Dukaixiang
 * @date 2019/4/30.
 * @Version 1.0
 */
@Data
public class ObjectMsg {
    private String ret;
    private String msg;
    private ObjectStr data;
}
