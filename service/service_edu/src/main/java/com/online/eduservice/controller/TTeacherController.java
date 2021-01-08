package com.online.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.commonutils.Result;
import com.online.eduservice.entity.TTeacher;
import com.online.eduservice.entity.vo.TeacherQuery;
import com.online.eduservice.service.TTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-08
 */

@RestController
@RequestMapping("/eduservice/teacher")
@Api(description= "讲师管理")
public class TTeacherController {

    @Autowired
    private TTeacherService tTeacherService;
    //查询所有讲师数据

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有讲师")
    public Result findAll(){
        List<TTeacher> list = tTeacherService.list(null);
        return Result.ok().data("teacher",list);
    }
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id 逻辑删除指定的讲师")
    //PathVariable  得到路径中的id
    public Result deleteById(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean flag = tTeacherService.removeById(id);
        if(flag){
            return Result.ok();
        }else {return Result.erro();}

    }


    @GetMapping("PagefindAll/{current}/{limit}")
    @ApiOperation(value = "查询分页查询所有讲师")
    public Result PagefindAll(@ApiParam(name = "current",value = "当前页",required = true) @PathVariable long current,
                              @ApiParam(name = "limit",value = "每页条数",required = true) @PathVariable long limit){
        Page<TTeacher> pageTeacher=new Page<TTeacher>(current,limit);
       tTeacherService.page(pageTeacher,null);
       long total=pageTeacher.getTotal();//总记录数
        List<TTeacher> records=pageTeacher.getRecords();
        Map map= new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return Result.ok().data(map);

       // return Result.ok().data("total",total).data("rows",records);
    }


    //条件查询带分页的方法
    @PostMapping("PageTeacherContidion/{current}/{limit}")
    @ApiOperation(value = "查询分页查询所有讲师")
    public Result PageTeacherContidion(
            @ApiParam(name = "current",value = "当前页",required = true) @PathVariable long current,
            @ApiParam(name = "limit",value = "每页条数",required = true) @PathVariable long limit,
            @ApiParam(name = "teacherQuery",value = "教师查询条件",required = true )@RequestBody(required = false) TeacherQuery teacherQuery){

        Page<TTeacher> pageTeacher=new Page<TTeacher>(current,limit);

        //构建查询条件 teacherQuery
        QueryWrapper<TTeacher> wrapper=new QueryWrapper<>();
        //判断条件值是否为空   不为空则拼接条件
        if(!StringUtils.isEmpty(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName());//like模糊查询
        }
        if(!StringUtils.isEmpty(teacherQuery.getLevel())){
            wrapper.eq("level",teacherQuery.getLevel()); //eq精确查询
        }
        if(!StringUtils.isEmpty(teacherQuery.getBegin())){
            wrapper.ge("gmt_create",teacherQuery.getBegin());
        }
        if(!StringUtils.isEmpty(teacherQuery.getEnd())){
            wrapper.le("gmt_create",teacherQuery.getEnd());
        }
        tTeacherService.page(pageTeacher,wrapper);

        long total=pageTeacher.getTotal();//总记录数
        List<TTeacher> records=pageTeacher.getRecords();
        Map map= new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return Result.ok().data(map);

        // return Result.ok().data("total",total).data("rows",records);
    }

    //新增讲师
    @PostMapping("/addTeacher")
    @ApiOperation(value = "新增讲师")
   public Result addTeacher( @ApiParam(name = "tTeacher",value = "添加的教师",required = true )@RequestBody TTeacher tTeacher){
        boolean save=tTeacherService.save(tTeacher);
        return  save?Result.ok():Result.erro();
       /*     if (save){
                return Result.ok();
            }
            else{
                return Result.erro();

            }*/
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    @ApiOperation(value = "根据id查询讲师")
    public Result getTeacher(@ApiParam(name = "id",value = "讲师id",required = true )@PathVariable String id) {
        TTeacher tTeacher = tTeacherService.getById(id);
        return Result.ok().data("teacher",tTeacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    @ApiOperation(value = "修改讲师")
    public Result updateTeacher(@ApiParam(name = "tTeacher",value = "讲师对象",required = true)@RequestBody TTeacher tTeacher) {
        boolean flag = tTeacherService.updateById(tTeacher);
        if(flag) {
            return Result.ok();
        } else {
            return Result.erro();
        }
    }

}

