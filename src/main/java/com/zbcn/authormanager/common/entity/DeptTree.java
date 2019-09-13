package com.zbcn.authormanager.common.entity;

import com.zbcn.authormanager.author.entity.TAuthDept;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName DeptTree.java
 * @Description 部门树
 * @createTime 2019年08月03日 20:03:00
 */
@Data
public class DeptTree<T> implements Serializable {
    private static final long serialVersionUID = -7335010664491035649L;

    private String id;
    private String icon;
    private String href;
    private String name;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<DeptTree<T>> children;
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private TAuthDept data;

    public void initChildren(){
        this.children = new ArrayList<>();
    }
}
