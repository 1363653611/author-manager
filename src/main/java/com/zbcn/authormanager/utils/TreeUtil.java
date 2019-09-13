package com.zbcn.authormanager.utils;

import com.zbcn.authormanager.common.entity.DeptTree;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName TreeUtil.java
 * @Description 构造结构树的util
 * @createTime 2019年08月11日 11:01:00
 */
public class TreeUtil {

    protected TreeUtil(){}

    public static <T> List<DeptTree<T>> buildDeptTree(List<DeptTree<T>> nodes){

        if(CollectionUtils.isEmpty(nodes)){
            return nodes;
        }
        List<DeptTree<T>> result = new ArrayList<>();

        nodes.forEach( children  -> {
            String pid = children.getParentId();
            if(StringUtils.isBlank(pid) || StringUtils.equals(pid,"0")){
                result.add(children);
                return;
            }
            for (DeptTree<T> n : nodes) {
                String id = n.getId();
                if(StringUtils.isNotBlank(id) && StringUtils.equals(id,pid)){
                    if(Objects.isNull(n.getChildren())){
                        n.initChildren();
                        n.getChildren().add(children);
                        children.setHasParent(true);
                        n.setHasChild(true);
                        return;
                    }
                }
            }

        });
        return result;
    }
}
