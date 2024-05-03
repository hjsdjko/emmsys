
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
 * 物资申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shenqing")
public class ShenqingController {
    private static final Logger logger = LoggerFactory.getLogger(ShenqingController.class);

    private static final String TABLE_NAME = "shenqing";

    @Autowired
    private ShenqingService shenqingService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//疫情论坛
    @Autowired
    private GonggaoService gonggaoService;//疫情资讯
    @Autowired
    private JiaoyuService jiaoyuService;//防疫宣传
    @Autowired
    private JuanzengService juanzengService;//物资捐赠
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
        CommonUtil.checkMap(params);
        PageUtils page = shenqingService.queryPage(params);

        //字典表数据转换
        List<ShenqingView> list =(List<ShenqingView>)page.getList();
        for(ShenqingView c:list){
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
        ShenqingEntity shenqing = shenqingService.selectById(id);
        if(shenqing !=null){
            //entity转view
            ShenqingView view = new ShenqingView();
            BeanUtils.copyProperties( shenqing , view );//把实体数据重构到view中
            //级联表 物资
            //级联表
            WuziEntity wuzi = wuziService.selectById(shenqing.getWuziId());
            if(wuzi != null){
            BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWuziId(wuzi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(shenqing.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody ShenqingEntity shenqing, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shenqing:{}",this.getClass().getName(),shenqing.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            shenqing.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShenqingEntity> queryWrapper = new EntityWrapper<ShenqingEntity>()
            .eq("wuzi_id", shenqing.getWuziId())
            .eq("yonghu_id", shenqing.getYonghuId())
            .eq("shenqing_shuliang", shenqing.getShenqingShuliang())
            .in("shenqing_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenqingEntity shenqingEntity = shenqingService.selectOne(queryWrapper);
        if(shenqingEntity==null){
            shenqing.setInsertTime(new Date());
            shenqing.setShenqingYesnoTypes(1);
            shenqing.setCreateTime(new Date());
            shenqingService.insert(shenqing);
            return R.ok();
        }else {
            if(shenqingEntity.getShenqingYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(shenqingEntity.getShenqingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShenqingEntity shenqing, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shenqing:{}",this.getClass().getName(),shenqing.toString());
        ShenqingEntity oldShenqingEntity = shenqingService.selectById(shenqing.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            shenqing.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(shenqing.getShenqingText()) || "null".equals(shenqing.getShenqingText())){
                shenqing.setShenqingText(null);
        }
        if("".equals(shenqing.getShenqingYesnoText()) || "null".equals(shenqing.getShenqingYesnoText())){
                shenqing.setShenqingYesnoText(null);
        }

            shenqingService.updateById(shenqing);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ShenqingEntity shenqingEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,shenqingEntity:{}",this.getClass().getName(),shenqingEntity.toString());

        ShenqingEntity oldShenqing = shenqingService.selectById(shenqingEntity.getId());//查询原先数据
        WuziEntity wuziEntity = wuziService.selectById(oldShenqing.getWuziId());
        if(shenqingEntity.getShenqingYesnoTypes() == 2){//通过
            WuziChuruInoutEntity<Object> objectWuziChuruInoutEntity = new WuziChuruInoutEntity<>();
            objectWuziChuruInoutEntity.setWuziChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            objectWuziChuruInoutEntity.setWuziChuruInoutName(oldShenqing.getShenqingText());
            objectWuziChuruInoutEntity.setWuziChuruInoutTypes(1);
            objectWuziChuruInoutEntity.setWuziChuruInoutContent("捐赠");
            objectWuziChuruInoutEntity.setInsertTime(new Date());
            objectWuziChuruInoutEntity.setCreateTime(new Date());
            wuziChuruInoutService.insert(objectWuziChuruInoutEntity);
            WuziChuruInoutListEntity wuziChuruInoutListEntity = new WuziChuruInoutListEntity();
            wuziChuruInoutListEntity.setWuziChuruInoutId(objectWuziChuruInoutEntity.getId());
            wuziChuruInoutListEntity.setWuziId(wuziEntity.getId());
            wuziChuruInoutListEntity.setWuziChuruInoutListNumber(oldShenqing.getShenqingShuliang());
            wuziChuruInoutListEntity.setInsertTime(new Date());
            wuziChuruInoutListEntity.setCreateTime(new Date());
            wuziEntity.setWuziKucunNumber(wuziEntity.getWuziKucunNumber()-oldShenqing.getShenqingShuliang());
            wuziService.updateById(wuziEntity);
            wuziChuruInoutListService.insert(wuziChuruInoutListEntity);
//            shenqingEntity.setShenqingTypes();
        }else if(shenqingEntity.getShenqingYesnoTypes() == 3){//拒绝
//            shenqingEntity.setShenqingTypes();
        }
        shenqingEntity.setShenqingShenheTime(new Date());//审核时间
        shenqingService.updateById(shenqingEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShenqingEntity> oldShenqingList =shenqingService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shenqingService.deleteBatchIds(Arrays.asList(ids));

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
            List<ShenqingEntity> shenqingList = new ArrayList<>();//上传的东西
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
                            ShenqingEntity shenqingEntity = new ShenqingEntity();
//                            shenqingEntity.setShenqingUuidNumber(data.get(0));                    //报名编号 要改的
//                            shenqingEntity.setWuziId(Integer.valueOf(data.get(0)));   //物资 要改的
//                            shenqingEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shenqingEntity.setShenqingText(data.get(0));                    //理由 要改的
//                            shenqingEntity.setInsertTime(date);//时间
//                            shenqingEntity.setShenqingShuliang(Integer.valueOf(data.get(0)));   //物资申请数量 要改的
//                            shenqingEntity.setShenqingYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            shenqingEntity.setShenqingYesnoText(data.get(0));                    //审核回复 要改的
//                            shenqingEntity.setShenqingShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            shenqingEntity.setCreateTime(date);//时间
                            shenqingList.add(shenqingEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("shenqingUuidNumber")){
                                    List<String> shenqingUuidNumber = seachFields.get("shenqingUuidNumber");
                                    shenqingUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shenqingUuidNumber = new ArrayList<>();
                                    shenqingUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shenqingUuidNumber",shenqingUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<ShenqingEntity> shenqingEntities_shenqingUuidNumber = shenqingService.selectList(new EntityWrapper<ShenqingEntity>().in("shenqing_uuid_number", seachFields.get("shenqingUuidNumber")));
                        if(shenqingEntities_shenqingUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShenqingEntity s:shenqingEntities_shenqingUuidNumber){
                                repeatFields.add(s.getShenqingUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shenqingService.insertBatch(shenqingList);
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
        PageUtils page = shenqingService.queryPage(params);

        //字典表数据转换
        List<ShenqingView> list =(List<ShenqingView>)page.getList();
        for(ShenqingView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShenqingEntity shenqing = shenqingService.selectById(id);
            if(shenqing !=null){


                //entity转view
                ShenqingView view = new ShenqingView();
                BeanUtils.copyProperties( shenqing , view );//把实体数据重构到view中

                //级联表
                    WuziEntity wuzi = wuziService.selectById(shenqing.getWuziId());
                if(wuzi != null){
                    BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWuziId(wuzi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shenqing.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody ShenqingEntity shenqing, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shenqing:{}",this.getClass().getName(),shenqing.toString());
        Wrapper<ShenqingEntity> queryWrapper = new EntityWrapper<ShenqingEntity>()
            .eq("shenqing_uuid_number", shenqing.getShenqingUuidNumber())
            .eq("wuzi_id", shenqing.getWuziId())
            .eq("yonghu_id", shenqing.getYonghuId())
            .eq("shenqing_text", shenqing.getShenqingText())
            .eq("shenqing_shuliang", shenqing.getShenqingShuliang())
            .in("shenqing_yesno_types", new Integer[]{1,2})
            .eq("shenqing_yesno_text", shenqing.getShenqingYesnoText())
//            .notIn("shenqing_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenqingEntity shenqingEntity = shenqingService.selectOne(queryWrapper);
        if(shenqingEntity==null){
            shenqing.setInsertTime(new Date());
            shenqing.setShenqingYesnoTypes(1);
            shenqing.setCreateTime(new Date());
        shenqingService.insert(shenqing);

            return R.ok();
        }else {
            if(shenqingEntity.getShenqingYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(shenqingEntity.getShenqingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

