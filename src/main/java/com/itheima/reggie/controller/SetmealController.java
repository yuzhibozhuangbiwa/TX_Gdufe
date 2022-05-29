package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealDishService;
import com.itheima.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

@Autowired
    SetmealDishService setmealDishService;
@Autowired
    SetmealService setmealService;
@Autowired
    DishService dishService;
@Autowired
    CategoryService categoryService;

    @PostMapping
    public R<String> sava(@RequestBody SetmealDto setmealDto){


        setmealService.savesetmealWithsetmealDish(setmealDto);
        return R.success("套餐添加成功");
    }

    @DeleteMapping
    public R<String> delete(Long[] ids){

        


        return R.success("删除套餐成功");
    }



    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){

        Page<Setmeal> pageInfo=new Page();
        Page<SetmealDto> dtoPage5=new Page<>();
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo,lambdaQueryWrapper);

        BeanUtils.copyProperties(pageInfo,dtoPage5,"records");
        List<Setmeal> records=pageInfo.getRecords();

        List<SetmealDto> list=records.stream().map((item)->{

            SetmealDto setmealDto=new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            Category byId = categoryService.getById(setmealDto.getCategoryId());
            String name1 = byId.getName();
            setmealDto.setCategoryName(name1);

            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage5.setRecords(list);
        return R.success(dtoPage5);
    }



}
