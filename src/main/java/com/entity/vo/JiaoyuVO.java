package com.entity.vo;

import com.entity.JiaoyuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 防疫宣传
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiaoyu")
public class JiaoyuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 防疫宣传名称
     */

    @TableField(value = "jiaoyu_name")
    private String jiaoyuName;


    /**
     * 防疫宣传编号
     */

    @TableField(value = "jiaoyu_uuid_number")
    private String jiaoyuUuidNumber;


    /**
     * 防疫宣传照片
     */

    @TableField(value = "jiaoyu_photo")
    private String jiaoyuPhoto;


    /**
     * 防疫宣传类型
     */

    @TableField(value = "jiaoyu_types")
    private Integer jiaoyuTypes;


    /**
     * 防疫宣传视频
     */

    @TableField(value = "jiaoyu_video")
    private String jiaoyuVideo;


    /**
     * 文件
     */

    @TableField(value = "jiaoyu_file")
    private String jiaoyuFile;


    /**
     * 防疫宣传介绍
     */

    @TableField(value = "jiaoyu_content")
    private String jiaoyuContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "jiaoyu_delete")
    private Integer jiaoyuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：防疫宣传名称
	 */
    public String getJiaoyuName() {
        return jiaoyuName;
    }


    /**
	 * 获取：防疫宣传名称
	 */

    public void setJiaoyuName(String jiaoyuName) {
        this.jiaoyuName = jiaoyuName;
    }
    /**
	 * 设置：防疫宣传编号
	 */
    public String getJiaoyuUuidNumber() {
        return jiaoyuUuidNumber;
    }


    /**
	 * 获取：防疫宣传编号
	 */

    public void setJiaoyuUuidNumber(String jiaoyuUuidNumber) {
        this.jiaoyuUuidNumber = jiaoyuUuidNumber;
    }
    /**
	 * 设置：防疫宣传照片
	 */
    public String getJiaoyuPhoto() {
        return jiaoyuPhoto;
    }


    /**
	 * 获取：防疫宣传照片
	 */

    public void setJiaoyuPhoto(String jiaoyuPhoto) {
        this.jiaoyuPhoto = jiaoyuPhoto;
    }
    /**
	 * 设置：防疫宣传类型
	 */
    public Integer getJiaoyuTypes() {
        return jiaoyuTypes;
    }


    /**
	 * 获取：防疫宣传类型
	 */

    public void setJiaoyuTypes(Integer jiaoyuTypes) {
        this.jiaoyuTypes = jiaoyuTypes;
    }
    /**
	 * 设置：防疫宣传视频
	 */
    public String getJiaoyuVideo() {
        return jiaoyuVideo;
    }


    /**
	 * 获取：防疫宣传视频
	 */

    public void setJiaoyuVideo(String jiaoyuVideo) {
        this.jiaoyuVideo = jiaoyuVideo;
    }
    /**
	 * 设置：文件
	 */
    public String getJiaoyuFile() {
        return jiaoyuFile;
    }


    /**
	 * 获取：文件
	 */

    public void setJiaoyuFile(String jiaoyuFile) {
        this.jiaoyuFile = jiaoyuFile;
    }
    /**
	 * 设置：防疫宣传介绍
	 */
    public String getJiaoyuContent() {
        return jiaoyuContent;
    }


    /**
	 * 获取：防疫宣传介绍
	 */

    public void setJiaoyuContent(String jiaoyuContent) {
        this.jiaoyuContent = jiaoyuContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getJiaoyuDelete() {
        return jiaoyuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJiaoyuDelete(Integer jiaoyuDelete) {
        this.jiaoyuDelete = jiaoyuDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
