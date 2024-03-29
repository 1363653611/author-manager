package com.zbcn.authormanager.author.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zbcn.authormanager.common.anntation.IsMobile;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: zbcn8
 * @Date: 2019/7/20 19:10
 */
@Data
@TableName("t_auth_user")
public class TAuthUser implements Serializable,Cloneable{

    // 用户状态：有效
    public static final String STATUS_VALID = "1";
    // 用户状态：锁定
    public static final String STATUS_LOCK = "0";

    // 默认头像
    public static final String DEFAULT_AVATAR = "default.jpg";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "1234qwer";

    // 性别男
    public static final String SEX_MALE = "0";
    // 性别女
    public static final String SEX_FEMALE = "1";
    // 性别保密
    public static final String SEX_UNKNOW = "2";
    // 黑色主题
    public static final String THEME_BLACK = "black";
    // 白色主题
    public static final String THEME_WHITE = "white";
    // TAB开启
    public static final String TAB_OPEN = "1";
    // TAB关闭
    public static final String TAB_CLOSE = "0";

    /** 用户ID;主键 */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId ;
    /** 用户名 */
    @TableField("USERNAME")
    @Size(min = 4, max = 10, message = "{range}")
    private String username ;
    /** 密码 */
    @TableField("PASSWORD")
    private String password ;
    /** 部门ID;部门Id */
    @TableField("DEPT_ID")
    private Long deptId ;
    /** 邮箱 */
    @TableField("EMAIL")
    @Size(max = 50, message = "{noMoreThan}")
    @Email(message = "{email}")
    private String email ;
    /** 联系电话 */
    @TableField("MOBILE")
    @IsMobile(message = "{mobile}")
    private String mobile ;
    /** 状态;0锁定 1有效 */
    @TableField("STATUS")
    @NotBlank(message = "{required}")
    private String status ;
    /** 性别;0男 1女 2保密 */
    @TableField("GENDER")
    @NotBlank(message = "{required}")
    private String gender ;
    /** 是否开启tab;0关闭 1开启 */
    private String isTab ;
    /** 主题 */
    @TableField("THEME")
    private String theme ;
    /** 头像 */
    @TableField("AVATAR")
    private String avatar ;
    /** 描述 */
    @TableField("DESCRIPTION")
    @Size(max = 100, message = "{noMoreThan}")
    private String description ;
    /** 创建时间 */
    @TableField("CREATED_TIME")
    private Date createdTime ;
    /** 更新人 */
    @TableField("MODIFY_BY")
    private String updatedBy ;
    /** 更新时间 */
    @TableField("UPDATED_TIME")
    private Date updatedTime ;
    /** 最近访问时间 */
    @TableField("LAST_LOGIN_TIME")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date lastLoginTime ;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;
    /**
     * 角色 ID
     */
    @NotBlank(message = "{required}")
    @TableField(exist = false)
    private String roleId;

    @TableField(exist = false)
    private String roleName;

}
