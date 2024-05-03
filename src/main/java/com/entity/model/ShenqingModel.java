package com.entity.model;

import com.entity.ShenqingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 物资申请
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShenqingModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String shenqingUuidNumber;


    /**
     * 物资
     */
    private Integer wuziId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 理由
     */
    private String shenqingText;


    /**
     * 物资申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 物资申请数量
     */
    private Integer shenqingShuliang;


    /**
     * 报名状态
     */
    private Integer shenqingYesnoTypes;


    /**
     * 审核回复
     */
    private String shenqingYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date shenqingShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
    public String getShenqingUuidNumber() {
        return shenqingUuidNumber;
    }


    /**
	 * 设置：报名编号
	 */
    public void setShenqingUuidNumber(String shenqingUuidNumber) {
        this.shenqingUuidNumber = shenqingUuidNumber;
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
    public String getShenqingText() {
        return shenqingText;
    }


    /**
	 * 设置：理由
	 */
    public void setShenqingText(String shenqingText) {
        this.shenqingText = shenqingText;
    }
    /**
	 * 获取：物资申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：物资申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：物资申请数量
	 */
    public Integer getShenqingShuliang() {
        return shenqingShuliang;
    }


    /**
	 * 设置：物资申请数量
	 */
    public void setShenqingShuliang(Integer shenqingShuliang) {
        this.shenqingShuliang = shenqingShuliang;
    }
    /**
	 * 获取：报名状态
	 */
    public Integer getShenqingYesnoTypes() {
        return shenqingYesnoTypes;
    }


    /**
	 * 设置：报名状态
	 */
    public void setShenqingYesnoTypes(Integer shenqingYesnoTypes) {
        this.shenqingYesnoTypes = shenqingYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getShenqingYesnoText() {
        return shenqingYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setShenqingYesnoText(String shenqingYesnoText) {
        this.shenqingYesnoText = shenqingYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getShenqingShenheTime() {
        return shenqingShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setShenqingShenheTime(Date shenqingShenheTime) {
        this.shenqingShenheTime = shenqingShenheTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
