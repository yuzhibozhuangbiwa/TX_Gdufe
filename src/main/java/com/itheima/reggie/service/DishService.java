package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    public void savedishWithFlavor(DishDto dishDto);


   public DishDto show(Long id);

    public void updatedishWithFlavor(DishDto dishDto);
}
