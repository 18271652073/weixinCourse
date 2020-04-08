package com.nbdeli.test.dom.jsonentity;

import lombok.Data;

import java.util.List;

/**
 * @author Dukaixiang
 * @date 2019/4/30.
 * @Version 1.0
 */
@Data
public class ObjectStr {
    private String topk;
    private List<ObjectList> object_list;
}
