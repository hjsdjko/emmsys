
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
 * 物资捐赠
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/juanzeng")
public class JuanzengController {
    private static final Logger logger = LoggerFactory.getLogger(JuanzengController.class);

    private static final String TABLE_NAME = "juanzeng";

    @Autowired
    private JuanzengService juanzengService;


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
        CommonUtil.checkMap(params);
        PageUtils page = juanzengService.queryPage(params);

        //字典表数据转换
        List<JuanzengView> list =(List<JuanzengView>)page.getList();
        for(JuanzengView c:list){
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
        JuanzengEntity juanzeng = juanzengService.selectById(id);
        if(juanzeng !=null){
            //entity转view
            JuanzengView view = new JuanzengView();
            BeanUtils.copyProperties( juanzeng , view );//把实体数据重构到view中
            //级联表 物资
            //级联表
            WuziEntity wuzi = wuziService.selectById(juanzeng.getWuziId());
            if(wuzi != null){
            BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWuziId(wuzi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(juanzeng.getYonghuId());
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
    public R save(@RequestBody JuanzengEntity juanzeng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,juanzeng:{}",this.getClass().getName(),juanzeng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            juanzeng.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JuanzengEntity> queryWrapper = new EntityWrapper<JuanzengEntity>()
            .eq("wuzi_id", juanzeng.getWuziId())
            .eq("yonghu_id", juanzeng.getYonghuId())
            .eq("juanzeng_shuliang", juanzeng.getJuanzengShuliang())
            .in("juanzeng_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JuanzengEntity juanzengEntity = juanzengService.selectOne(queryWrapper);
        if(juanzengEntity==null){
            juanzeng.setInsertTime(new Date());
            juanzeng.setJuanzengYesnoTypes(1);
            juanzeng.setCreateTime(new Date());
            juanzengService.insert(juanzeng);
            return R.ok();
        }else {
            if(juanzengEntity.getJuanzengYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(juanzengEntity.getJuanzengYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JuanzengEntity juanzeng, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,juanzeng:{}",this.getClass().getName(),juanzeng.toString());
        JuanzengEntity oldJuanzengEntity = juanzengService.selectById(juanzeng.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            juanzeng.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(juanzeng.getJuanzengText()) || "null".equals(juanzeng.getJuanzengText())){
                juanzeng.setJuanzengText(null);
        }
        if("".equals(juanzeng.getJuanzengYesnoText()) || "null".equals(juanzeng.getJuanzengYesnoText())){
                juanzeng.setJuanzengYesnoText(null);
        }

            juanzengService.updateById(juanzeng);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody JuanzengEntity juanzengEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,juanzengEntity:{}",this.getClass().getName(),juanzengEntity.toString());

        JuanzengEntity oldJuanzeng = juanzengService.selectById(juanzengEntity.getId());//查询原先数据
        WuziEntity wuziEntity = wuziService.selectById(oldJuanzeng.getWuziId());
        if(juanzengEntity.getJuanzengYesnoTypes() == 2){//通过

            WuziChuruInoutEntity<Object> objectWuziChuruInoutEntity = new WuziChuruInoutEntity<>();
            objectWuziChuruInoutEntity.setWuziChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            objectWuziChuruInoutEntity.setWuziChuruInoutName(oldJuanzeng.getJuanzengText());
            objectWuziChuruInoutEntity.setWuziChuruInoutTypes(2);
            objectWuziChuruInoutEntity.setWuziChuruInoutContent("捐赠");
            objectWuziChuruInoutEntity.setInsertTime(new Date());
            objectWuziChuruInoutEntity.setCreateTime(new Date());
            wuziChuruInoutService.insert(objectWuziChuruInoutEntity);
            WuziChuruInoutListEntity wuziChuruInoutListEntity = new WuziChuruInoutListEntity();
            wuziChuruInoutListEntity.setWuziChuruInoutId(objectWuziChuruInoutEntity.getId());
            wuziChuruInoutListEntity.setWuziId(wuziEntity.getId());
            wuziChuruInoutListEntity.setWuziChuruInoutListNumber(oldJuanzeng.getJuanzengShuliang());
            wuziChuruInoutListEntity.setInsertTime(new Date());
            wuziChuruInoutListEntity.setCreateTime(new Date());
            wuziEntity.setWuziKucunNumber(wuziEntity.getWuziKucunNumber()+oldJuanzeng.getJuanzengShuliang());
            wuziService.updateById(wuziEntity);
            wuziChuruInoutListService.insert(wuziChuruInoutListEntity);

        }else if(juanzengEntity.getJuanzengYesnoTypes() == 3){//拒绝
//            juanzengEntity.setJuanzengTypes();
        }
        juanzengEntity.setJuanzengShenheTime(new Date());//审核时间
        juanzengService.updateById(juanzengEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JuanzengEntity> oldJuanzengList =juanzengService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        juanzengService.deleteBatchIds(Arrays.asList(ids));

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
            List<JuanzengEntity> juanzengList = new ArrayList<>();//上传的东西
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
                            JuanzengEntity juanzengEntity = new JuanzengEntity();
//                            juanzengEntity.setJuanzengUuidNumber(data.get(0));                    //报名编号 要改的
//                            juanzengEntity.setWuziId(Integer.valueOf(data.get(0)));   //物资 要改的
//                            juanzengEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            juanzengEntity.setJuanzengText(data.get(0));                    //理由 要改的
//                            juanzengEntity.setInsertTime(date);//时间
//                            juanzengEntity.setJuanzengShuliang(Integer.valueOf(data.get(0)));   //物资捐赠数量 要改的
//                            juanzengEntity.setJuanzengYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            juanzengEntity.setJuanzengYesnoText(data.get(0));                    //审核回复 要改的
//                            juanzengEntity.setJuanzengShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            juanzengEntity.setCreateTime(date);//时间
                            juanzengList.add(juanzengEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("juanzengUuidNumber")){
                                    List<String> juanzengUuidNumber = seachFields.get("juanzengUuidNumber");
                                    juanzengUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> juanzengUuidNumber = new ArrayList<>();
                                    juanzengUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("juanzengUuidNumber",juanzengUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<JuanzengEntity> juanzengEntities_juanzengUuidNumber = juanzengService.selectList(new EntityWrapper<JuanzengEntity>().in("juanzeng_uuid_number", seachFields.get("juanzengUuidNumber")));
                        if(juanzengEntities_juanzengUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JuanzengEntity s:juanzengEntities_juanzengUuidNumber){
                                repeatFields.add(s.getJuanzengUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        juanzengService.insertBatch(juanzengList);
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
        PageUtils page = juanzengService.queryPage(params);

        //字典表数据转换
        List<JuanzengView> list =(List<JuanzengView>)page.getList();
        for(JuanzengView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JuanzengEntity juanzeng = juanzengService.selectById(id);
            if(juanzeng !=null){


                //entity转view
                JuanzengView view = new JuanzengView();
                BeanUtils.copyProperties( juanzeng , view );//把实体数据重构到view中

                //级联表
                    WuziEntity wuzi = wuziService.selectById(juanzeng.getWuziId());
                if(wuzi != null){
                    BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWuziId(wuzi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(juanzeng.getYonghuId());
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
    public R add(@RequestBody JuanzengEntity juanzeng, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,juanzeng:{}",this.getClass().getName(),juanzeng.toString());
        Wrapper<JuanzengEntity> queryWrapper = new EntityWrapper<JuanzengEntity>()
            .eq("juanzeng_uuid_number", juanzeng.getJuanzengUuidNumber())
            .eq("wuzi_id", juanzeng.getWuziId())
            .eq("yonghu_id", juanzeng.getYonghuId())
            .eq("juanzeng_text", juanzeng.getJuanzengText())
            .eq("juanzeng_shuliang", juanzeng.getJuanzengShuliang())
            .in("juanzeng_yesno_types", new Integer[]{1,2})
            .eq("juanzeng_yesno_text", juanzeng.getJuanzengYesnoText())
//            .notIn("juanzeng_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JuanzengEntity juanzengEntity = juanzengService.selectOne(queryWrapper);
        if(juanzengEntity==null){
            juanzeng.setInsertTime(new Date());
            juanzeng.setJuanzengYesnoTypes(1);
            juanzeng.setCreateTime(new Date());
        juanzengService.insert(juanzeng);

            return R.ok();
        }else {
            if(juanzengEntity.getJuanzengYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(juanzengEntity.getJuanzengYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

