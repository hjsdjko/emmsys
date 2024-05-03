package com.entity.vo;

import com.entity.ShenqingEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 物资申请
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shenqing")
public class ShenqingVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "shenqing_uuid_number")
    private String shenqingUuidNumber;


    /**
     * 物资
     */

    @TableField(value = "wuzi_id")
    private Integer wuziId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 理由
     */

    @TableField(value = "shenqing_text")
    private String shenqingText;


    /**
     * 物资申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 物资申请数量
     */

    @TableField(value = "shenqing_shuliang")
    private Integer shenqingShuliang;


    /**
     * 报名状态
     */

    @TableField(value = "shenqing_yesno_types")
    private Integer shenqingYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "shenqing_yesno_text")
    private String shenqingYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "shenqing_shenhe_time")
    private Date shenqingShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：报名编号
	 */
    public String getShenqingUuidNumber() {
        return shenqingUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setShenqingUuidNumber(String shenqingUuidNumber) {
        this.shenqingUuidNumber = shenqingUuidNumber;
    }
    /**
	 * 设置：物资
	 */
    public Integer getWuziId() {
        return wuziId;
    }


    /**
	 * 获取：物资
	 */

    public void setWuziId(Integer wuziId) {
        this.wuziId = wuziId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：理由
	 */
    public String getShenqingText() {
        return shenqingText;
    }


    /**
	 * 获取：理由
	 */

    public void setShenqingText(String shenqingText) {
        this.shenqingText = shenqingText;
    }
    /**
	 * 设置：物资申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：物资申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：物资申请数量
	 */
    public Integer getShenqingShuliang() {
        return shenqingShuliang;
    }


    /**
	 * 获取：物资申请数量
	 */

    public void setShenqingShuliang(Integer shenqingShuliang) {
        this.shenqingShuliang = shenqingShuliang;
    }
    /**
	 * 设置：报名状态
	 */
    public Integer getShenqingYesnoTypes() {
        return shenqingYesnoTypes;
    }


    /**
	 * 获取：报名状态
	 */

    public void setShenqingYesnoTypes(Integer shenqingYesnoTypes) {
        this.shenqingYesnoTypes = shenqingYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getShenqingYesnoText() {
        return shenqingYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setShenqingYesnoText(String shenqingYesnoText) {
        this.shenqingYesnoText = shenqingYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getShenqingShenheTime() {
        return shenqingShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setShenqingShenheTime(Date shenqingShenheTime) {
        this.shenqingShenheTime = shenqingShenheTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
