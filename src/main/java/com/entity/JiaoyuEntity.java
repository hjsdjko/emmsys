package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 防疫宣传
 *
 * @author 
 * @email
 */
@TableName("jiaoyu")
public class JiaoyuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiaoyuEntity() {

	}

	public JiaoyuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 防疫宣传名称
     */
    @ColumnInfo(comment="防疫宣传名称",type="varchar(200)")
    @TableField(value = "jiaoyu_name")

    private String jiaoyuName;


    /**
     * 防疫宣传编号
     */
    @ColumnInfo(comment="防疫宣传编号",type="varchar(200)")
    @TableField(value = "jiaoyu_uuid_number")

    private String jiaoyuUuidNumber;


    /**
     * 防疫宣传照片
     */
    @ColumnInfo(comment="防疫宣传照片",type="varchar(200)")
    @TableField(value = "jiaoyu_photo")

    private String jiaoyuPhoto;


    /**
     * 防疫宣传类型
     */
    @ColumnInfo(comment="防疫宣传类型",type="int(11)")
    @TableField(value = "jiaoyu_types")

    private Integer jiaoyuTypes;


    /**
     * 防疫宣传视频
     */
    @ColumnInfo(comment="防疫宣传视频",type="varchar(200)")
    @TableField(value = "jiaoyu_video")

    private String jiaoyuVideo;


    /**
     * 文件
     */
    @ColumnInfo(comment="文件",type="varchar(200)")
    @TableField(value = "jiaoyu_file")

    private String jiaoyuFile;


    /**
     * 防疫宣传介绍
     */
    @ColumnInfo(comment="防疫宣传介绍",type="longtext")
    @TableField(value = "jiaoyu_content")

    private String jiaoyuContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "jiaoyu_delete")

    private Integer jiaoyuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：防疫宣传名称
	 */
    public String getJiaoyuName() {
        return jiaoyuName;
    }
    /**
	 * 设置：防疫宣传名称
	 */

    public void setJiaoyuName(String jiaoyuName) {
        this.jiaoyuName = jiaoyuName;
    }
    /**
	 * 获取：防疫宣传编号
	 */
    public String getJiaoyuUuidNumber() {
        return jiaoyuUuidNumber;
    }
    /**
	 * 设置：防疫宣传编号
	 */

    public void setJiaoyuUuidNumber(String jiaoyuUuidNumber) {
        this.jiaoyuUuidNumber = jiaoyuUuidNumber;
    }
    /**
	 * 获取：防疫宣传照片
	 */
    public String getJiaoyuPhoto() {
        return jiaoyuPhoto;
    }
    /**
	 * 设置：防疫宣传照片
	 */

    public void setJiaoyuPhoto(String jiaoyuPhoto) {
        this.jiaoyuPhoto = jiaoyuPhoto;
    }
    /**
	 * 获取：防疫宣传类型
	 */
    public Integer getJiaoyuTypes() {
        return jiaoyuTypes;
    }
    /**
	 * 设置：防疫宣传类型
	 */

    public void setJiaoyuTypes(Integer jiaoyuTypes) {
        this.jiaoyuTypes = jiaoyuTypes;
    }
    /**
	 * 获取：防疫宣传视频
	 */
    public String getJiaoyuVideo() {
        return jiaoyuVideo;
    }
    /**
	 * 设置：防疫宣传视频
	 */

    public void setJiaoyuVideo(String jiaoyuVideo) {
        this.jiaoyuVideo = jiaoyuVideo;
    }
    /**
	 * 获取：文件
	 */
    public String getJiaoyuFile() {
        return jiaoyuFile;
    }
    /**
	 * 设置：文件
	 */

    public void setJiaoyuFile(String jiaoyuFile) {
        this.jiaoyuFile = jiaoyuFile;
    }
    /**
	 * 获取：防疫宣传介绍
	 */
    public String getJiaoyuContent() {
        return jiaoyuContent;
    }
    /**
	 * 设置：防疫宣传介绍
	 */

    public void setJiaoyuContent(String jiaoyuContent) {
        this.jiaoyuContent = jiaoyuContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJiaoyuDelete() {
        return jiaoyuDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setJiaoyuDelete(Integer jiaoyuDelete) {
        this.jiaoyuDelete = jiaoyuDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jiaoyu{" +
            ", id=" + id +
            ", jiaoyuName=" + jiaoyuName +
            ", jiaoyuUuidNumber=" + jiaoyuUuidNumber +
            ", jiaoyuPhoto=" + jiaoyuPhoto +
            ", jiaoyuTypes=" + jiaoyuTypes +
            ", jiaoyuVideo=" + jiaoyuVideo +
            ", jiaoyuFile=" + jiaoyuFile +
            ", jiaoyuContent=" + jiaoyuContent +
            ", jiaoyuDelete=" + jiaoyuDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
