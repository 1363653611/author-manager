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
 * @Auther: zbcn8
 * @Date: 2019/7/20 19:14
 */
@TableName("t_auth_dept")
@Data
public class TAuthDept implements Serializable,Cloneable{
    /** 部门ID */
    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    private Long deptId ;
    /** 上级部门ID */
    @TableField("PARENT_ID")
    private Long parentId ;
    /** 部门名称 */
    @TableField("DEPT_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    private String deptName ;
    /** 显示排序 */
    @TableField("ORDER_NUM")
    private String orderNum ;
    /** 创建人 */
    @TableField("CREATED_BY")
    private String createdBy ;
    /** 创建时间 */
    @TableField("CREATED_TIME")
    private Date createdTime ;
    /** 更新人 */
    @TableField("UPDATED_BY")
    private String updatedBy ;
    /** 更新时间 */
    @TableField("UPDATED_TIME")
    private Date updatedTime ;

}
