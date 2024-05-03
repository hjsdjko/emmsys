package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JiaoyuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 防疫宣传
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jiaoyu")
public class JiaoyuView extends JiaoyuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 防疫宣传类型的值
	*/
	@ColumnInfo(comment="防疫宣传类型的字典表值",type="varchar(200)")
	private String jiaoyuValue;




	public JiaoyuView() {

	}

	public JiaoyuView(JiaoyuEntity jiaoyuEntity) {
		try {
			BeanUtils.copyProperties(this, jiaoyuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 防疫宣传类型的值
	*/
	public String getJiaoyuValue() {
		return jiaoyuValue;
	}
	/**
	* 设置： 防疫宣传类型的值
	*/
	public void setJiaoyuValue(String jiaoyuValue) {
		this.jiaoyuValue = jiaoyuValue;
	}




	@Override
	public String toString() {
		return "JiaoyuView{" +
			", jiaoyuValue=" + jiaoyuValue +
			"} " + super.toString();
	}
}
