package com.zbcn.authormanager.author.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Authr: zbcn8
 * @Date: 2019/7/20 19:25
 */
@Data
@TableName("t_user_role")
public class TUserRole implements Serializable,Cloneable {
    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;
    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;
}