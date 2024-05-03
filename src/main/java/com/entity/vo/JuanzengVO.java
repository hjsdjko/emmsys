package com.entity.vo;

import com.entity.JuanzengEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 物资捐赠
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("juanzeng")
public class JuanzengVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "juanzeng_uuid_number")
    private String juanzengUuidNumber;


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

    @TableField(value = "juanzeng_text")
    private String juanzengText;


    /**
     * 物资捐赠时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 物资捐赠数量
     */

    @TableField(value = "juanzeng_shuliang")
    private Integer juanzengShuliang;


    /**
     * 报名状态
     */

    @TableField(value = "juanzeng_yesno_types")
    private Integer juanzengYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "juanzeng_yesno_text")
    private String juanzengYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "juanzeng_shenhe_time")
    private Date juanzengShenheTime;


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
    public String getJuanzengUuidNumber() {
        return juanzengUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setJuanzengUuidNumber(String juanzengUuidNumber) {
        this.juanzengUuidNumber = juanzengUuidNumber;
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
    public String getJuanzengText() {
        return juanzengText;
    }


    /**
	 * 获取：理由
	 */

    public void setJuanzengText(String juanzengText) {
        this.juanzengText = juanzengText;
    }
    /**
	 * 设置：物资捐赠时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：物资捐赠时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：物资捐赠数量
	 */
    public Integer getJuanzengShuliang() {
        return juanzengShuliang;
    }


    /**
	 * 获取：物资捐赠数量
	 */

    public void setJuanzengShuliang(Integer juanzengShuliang) {
        this.juanzengShuliang = juanzengShuliang;
    }
    /**
	 * 设置：报名状态
	 */
    public Integer getJuanzengYesnoTypes() {
        return juanzengYesnoTypes;
    }


    /**
	 * 获取：报名状态
	 */

    public void setJuanzengYesnoTypes(Integer juanzengYesnoTypes) {
        this.juanzengYesnoTypes = juanzengYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getJuanzengYesnoText() {
        return juanzengYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setJuanzengYesnoText(String juanzengYesnoText) {
        this.juanzengYesnoText = juanzengYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getJuanzengShenheTime() {
        return juanzengShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setJuanzengShenheTime(Date juanzengShenheTime) {
        this.juanzengShenheTime = juanzengShenheTime;
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
