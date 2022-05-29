package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import org.springframework.stereotype.Service;


public interface SetmealService extends IService<Setmeal> {
    public void savesetmealWithsetmealDish(SetmealDto setmealDto);
}
