
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 防疫宣传
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiaoyu")
public class JiaoyuController {
    private static final Logger logger = LoggerFactory.getLogger(JiaoyuController.class);

    private static final String TABLE_NAME = "jiaoyu";

    @Autowired
    private JiaoyuService jiaoyuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//疫情论坛
    @Autowired
    private GonggaoService gonggaoService;//疫情资讯
    @Autowired
    private JuanzengService juanzengService;//物资捐赠
    @Autowired
    private ShenqingService shenqingService;//物资申请
    @Autowired
    private WuziService wuziService;//物资
    @Autowired
    private WuziChuruInoutService wuziChuruInoutService;//出入库
    @Autowired
    private WuziChuruInoutListService wuziChuruInoutListService;//出入库详情
    @Autowired
    private WuziCollectionService wuziCollectionService;//物资收藏
    @Autowired
    private WuziLiuyanService wuziLiuyanService;//物资留言
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private YuangongService yuangongService;//员工
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        params.put("jiaoyuDeleteStart",1);params.put("jiaoyuDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = jiaoyuService.queryPage(params);

        //字典表数据转换
        List<JiaoyuView> list =(List<JiaoyuView>)page.getList();
        for(JiaoyuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaoyuEntity jiaoyu = jiaoyuService.selectById(id);
        if(jiaoyu !=null){
            //entity转view
            JiaoyuView view = new JiaoyuView();
            BeanUtils.copyProperties( jiaoyu , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoyuEntity jiaoyu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiaoyu:{}",this.getClass().getName(),jiaoyu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JiaoyuEntity> queryWrapper = new EntityWrapper<JiaoyuEntity>()
            .eq("jiaoyu_name", jiaoyu.getJiaoyuName())
            .eq("jiaoyu_types", jiaoyu.getJiaoyuTypes())
            .eq("jiaoyu_video", jiaoyu.getJiaoyuVideo())
            .eq("jiaoyu_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaoyuEntity jiaoyuEntity = jiaoyuService.selectOne(queryWrapper);
        if(jiaoyuEntity==null){
            jiaoyu.setJiaoyuDelete(1);
            jiaoyu.setInsertTime(new Date());
            jiaoyu.setCreateTime(new Date());
            jiaoyuService.insert(jiaoyu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaoyuEntity jiaoyu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiaoyu:{}",this.getClass().getName(),jiaoyu.toString());
        JiaoyuEntity oldJiaoyuEntity = jiaoyuService.selectById(jiaoyu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(jiaoyu.getJiaoyuPhoto()) || "null".equals(jiaoyu.getJiaoyuPhoto())){
                jiaoyu.setJiaoyuPhoto(null);
        }
        if("".equals(jiaoyu.getJiaoyuVideo()) || "null".equals(jiaoyu.getJiaoyuVideo())){
                jiaoyu.setJiaoyuVideo(null);
        }
        if("".equals(jiaoyu.getJiaoyuFile()) || "null".equals(jiaoyu.getJiaoyuFile())){
                jiaoyu.setJiaoyuFile(null);
        }
        if("".equals(jiaoyu.getJiaoyuContent()) || "null".equals(jiaoyu.getJiaoyuContent())){
                jiaoyu.setJiaoyuContent(null);
        }

            jiaoyuService.updateById(jiaoyu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiaoyuEntity> oldJiaoyuList =jiaoyuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<JiaoyuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            JiaoyuEntity jiaoyuEntity = new JiaoyuEntity();
            jiaoyuEntity.setId(id);
            jiaoyuEntity.setJiaoyuDelete(2);
            list.add(jiaoyuEntity);
        }
        if(list != null && list.size() >0){
            jiaoyuService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<JiaoyuEntity> jiaoyuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JiaoyuEntity jiaoyuEntity = new JiaoyuEntity();
//                            jiaoyuEntity.setJiaoyuName(data.get(0));                    //防疫宣传名称 要改的
//                            jiaoyuEntity.setJiaoyuUuidNumber(data.get(0));                    //防疫宣传编号 要改的
//                            jiaoyuEntity.setJiaoyuPhoto("");//详情和图片
//                            jiaoyuEntity.setJiaoyuTypes(Integer.valueOf(data.get(0)));   //防疫宣传类型 要改的
//                            jiaoyuEntity.setJiaoyuVideo(data.get(0));                    //防疫宣传视频 要改的
//                            jiaoyuEntity.setJiaoyuFile(data.get(0));                    //文件 要改的
//                            jiaoyuEntity.setJiaoyuContent("");//详情和图片
//                            jiaoyuEntity.setJiaoyuDelete(1);//逻辑删除字段
//                            jiaoyuEntity.setInsertTime(date);//时间
//                            jiaoyuEntity.setCreateTime(date);//时间
                            jiaoyuList.add(jiaoyuEntity);


                            //把要查询是否重复的字段放入map中
                                //防疫宣传编号
                                if(seachFields.containsKey("jiaoyuUuidNumber")){
                                    List<String> jiaoyuUuidNumber = seachFields.get("jiaoyuUuidNumber");
                                    jiaoyuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiaoyuUuidNumber = new ArrayList<>();
                                    jiaoyuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiaoyuUuidNumber",jiaoyuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //防疫宣传编号
                        List<JiaoyuEntity> jiaoyuEntities_jiaoyuUuidNumber = jiaoyuService.selectList(new EntityWrapper<JiaoyuEntity>().in("jiaoyu_uuid_number", seachFields.get("jiaoyuUuidNumber")).eq("jiaoyu_delete", 1));
                        if(jiaoyuEntities_jiaoyuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiaoyuEntity s:jiaoyuEntities_jiaoyuUuidNumber){
                                repeatFields.add(s.getJiaoyuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [防疫宣传编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiaoyuService.insertBatch(jiaoyuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jiaoyuService.queryPage(params);

        //字典表数据转换
        List<JiaoyuView> list =(List<JiaoyuView>)page.getList();
        for(JiaoyuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaoyuEntity jiaoyu = jiaoyuService.selectById(id);
            if(jiaoyu !=null){


                //entity转view
                JiaoyuView view = new JiaoyuView();
                BeanUtils.copyProperties( jiaoyu , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoyuEntity jiaoyu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiaoyu:{}",this.getClass().getName(),jiaoyu.toString());
        Wrapper<JiaoyuEntity> queryWrapper = new EntityWrapper<JiaoyuEntity>()
            .eq("jiaoyu_name", jiaoyu.getJiaoyuName())
            .eq("jiaoyu_uuid_number", jiaoyu.getJiaoyuUuidNumber())
            .eq("jiaoyu_types", jiaoyu.getJiaoyuTypes())
            .eq("jiaoyu_video", jiaoyu.getJiaoyuVideo())
            .eq("jiaoyu_delete", jiaoyu.getJiaoyuDelete())
//            .notIn("jiaoyu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaoyuEntity jiaoyuEntity = jiaoyuService.selectOne(queryWrapper);
        if(jiaoyuEntity==null){
            jiaoyu.setJiaoyuDelete(1);
            jiaoyu.setInsertTime(new Date());
            jiaoyu.setCreateTime(new Date());
        jiaoyuService.insert(jiaoyu);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

