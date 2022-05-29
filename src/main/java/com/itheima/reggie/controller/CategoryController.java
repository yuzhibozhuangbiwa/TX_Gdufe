package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public R<Category> addCategory(@RequestBody Category category, HttpServletRequest request) {
        System.out.println(category);
        categoryService.save(category);
        System.out.println(category);
        return R.success(category);
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        log.info("type = {},name = {}" ,category.getType(),category.getName());
//其实你也明白了 只有负载上的 才可以传出来 这就解释了 name=null;
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Category::getType,category.getType());
        lambdaQueryWrapper.orderByDesc();


        List<Category> list = categoryService.list(lambdaQueryWrapper);
        return R.success(list);

    }


    @PutMapping
    public R<String> editCategory(@RequestBody Category category,HttpServletRequest request){
        System.out.println(category);
        categoryService.updateById(category);

        return R.success("修改Category完成");

    }



    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);

        Page pageInfo=new Page(page,pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Category::getName,name);
        lambdaQueryWrapper.orderByDesc();
       categoryService.page(pageInfo,lambdaQueryWrapper);
       return R.success(pageInfo);

    }

    @DeleteMapping
    public R<String> deletebyId(Long id){

        System.out.println(id);
//        categoryService.removeById(id); 这tmd的为啥不行 到时候看看 我靠
        //既然不行我们就自己写一个remove
        categoryService.remove(id);

        return R.success("删除成功了捏");
    }




}
