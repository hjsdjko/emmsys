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
 * 物资捐赠
 *
 * @author 
 * @email
 */
@TableName("juanzeng")
public class JuanzengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JuanzengEntity() {

	}

	public JuanzengEntity(T t) {
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
     * 报名编号
     */
    @ColumnInfo(comment="报名编号",type="varchar(200)")
    @TableField(value = "juanzeng_uuid_number")

    private String juanzengUuidNumber;


    /**
     * 物资
     */
    @ColumnInfo(comment="物资",type="int(11)")
    @TableField(value = "wuzi_id")

    private Integer wuziId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 理由
     */
    @ColumnInfo(comment="理由",type="longtext")
    @TableField(value = "juanzeng_text")

    private String juanzengText;


    /**
     * 物资捐赠时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="物资捐赠时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 物资捐赠数量
     */
    @ColumnInfo(comment="物资捐赠数量",type="int(11)")
    @TableField(value = "juanzeng_shuliang")

    private Integer juanzengShuliang;


    /**
     * 报名状态
     */
    @ColumnInfo(comment="报名状态",type="int(11)")
    @TableField(value = "juanzeng_yesno_types")

    private Integer juanzengYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "juanzeng_yesno_text")

    private String juanzengYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "juanzeng_shenhe_time")

    private Date juanzengShenheTime;


    /**
     * 创建时间  listShow
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
	 * 获取：报名编号
	 */
    public String getJuanzengUuidNumber() {
        return juanzengUuidNumber;
    }
    /**
	 * 设置：报名编号
	 */

    public void setJuanzengUuidNumber(String juanzengUuidNumber) {
        this.juanzengUuidNumber = juanzengUuidNumber;
    }
    /**
	 * 获取：物资
	 */
    public Integer getWuziId() {
        return wuziId;
    }
    /**
	 * 设置：物资
	 */

    public void setWuziId(Integer wuziId) {
        this.wuziId = wuziId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：理由
	 */
    public String getJuanzengText() {
        return juanzengText;
    }
    /**
	 * 设置：理由
	 */

    public void setJuanzengText(String juanzengText) {
        this.juanzengText = juanzengText;
    }
    /**
	 * 获取：物资捐赠时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：物资捐赠时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：物资捐赠数量
	 */
    public Integer getJuanzengShuliang() {
        return juanzengShuliang;
    }
    /**
	 * 设置：物资捐赠数量
	 */

    public void setJuanzengShuliang(Integer juanzengShuliang) {
        this.juanzengShuliang = juanzengShuliang;
    }
    /**
	 * 获取：报名状态
	 */
    public Integer getJuanzengYesnoTypes() {
        return juanzengYesnoTypes;
    }
    /**
	 * 设置：报名状态
	 */

    public void setJuanzengYesnoTypes(Integer juanzengYesnoTypes) {
        this.juanzengYesnoTypes = juanzengYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getJuanzengYesnoText() {
        return juanzengYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setJuanzengYesnoText(String juanzengYesnoText) {
        this.juanzengYesnoText = juanzengYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getJuanzengShenheTime() {
        return juanzengShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setJuanzengShenheTime(Date juanzengShenheTime) {
        this.juanzengShenheTime = juanzengShenheTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Juanzeng{" +
            ", id=" + id +
            ", juanzengUuidNumber=" + juanzengUuidNumber +
            ", wuziId=" + wuziId +
            ", yonghuId=" + yonghuId +
            ", juanzengText=" + juanzengText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", juanzengShuliang=" + juanzengShuliang +
            ", juanzengYesnoTypes=" + juanzengYesnoTypes +
            ", juanzengYesnoText=" + juanzengYesnoText +
            ", juanzengShenheTime=" + DateUtil.convertString(juanzengShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
