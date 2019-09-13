package com.zbcn.authormanager.author.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: zbcn8
 * @Date: 2019/7/20 19:24
 */
@Data
@TableName("t_role_menu")
public class TRoleMenu implements Serializable,Cloneable {
    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    protected Long roleId;
    /**
     * 菜单/按钮ID
     */
    @TableField("MENU_ID")
    private Long menuId;
}
