package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;


    @Override
    public void remove(Long id) {

        LambdaQueryWrapper<Dish> dishlambdaQueryWrapper=new LambdaQueryWrapper<>();
        dishlambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishlambdaQueryWrapper);

                if(count1>0){
                    throw new CustomException("已经与菜品绑定上了");
        }
                LambdaQueryWrapper<Setmeal> SetmeallambdaQueryWrapper=new LambdaQueryWrapper<>();
                SetmeallambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
                int count2=setmealService.count(SetmeallambdaQueryWrapper);

                if(count2>0){
                    throw new CustomException("已经与套餐绑定上了");
                }

                super.removeById(id);


    }
}
