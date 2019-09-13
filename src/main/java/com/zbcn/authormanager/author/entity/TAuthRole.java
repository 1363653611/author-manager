package com.zbcn.authormanager.author.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Authr: zbcn8
 * @Date: 2019/7/20 19:18
 */
@Data
@TableName("t_auth_role")
public class TAuthRole implements Serializable,Cloneable {
    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    @NotBlank(message = "{required}")
    private String roleName;
    /**
     * 角色描述
     */
    @TableField("REMARK")
    @Size(max = 50, message = "{noMoreThan}")
    private String remark;
    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField("UPDATED_BY")
    private String updatedBy;
    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private Date updatedTime;
}
