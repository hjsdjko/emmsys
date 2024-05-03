package com.entity.model;

import com.entity.JuanzengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 物资捐赠
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JuanzengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String juanzengUuidNumber;


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
    private String juanzengText;


    /**
     * 物资捐赠时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 物资捐赠数量
     */
    private Integer juanzengShuliang;


    /**
     * 报名状态
     */
    private Integer juanzengYesnoTypes;


    /**
     * 审核回复
     */
    private String juanzengYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date juanzengShenheTime;


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
