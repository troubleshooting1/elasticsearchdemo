package com.anji.es.rest;

import com.anji.es.search.entity.MyEsEntity;
import com.anji.es.service.EsTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/4 22:47
 */
@RestController
@RequestMapping(value = "/es/test")
public class EsTest {
    @Resource
    EsTestService esTestService;

    @RequestMapping(value = "/add")
    public void add() {
        MyEsEntity myEsEntity = new MyEsEntity();
        myEsEntity.setId(3);

        //为了后期显示出ik分词器的效果，名字起了一个商品的名称
        myEsEntity.setName("五常香米");
        myEsEntity.setSex("man");
        myEsEntity.setAge(20);
        esTestService.add(myEsEntity);

        myEsEntity = new MyEsEntity();
        myEsEntity.setId(4);

        //为了后期显示出ik分词器的效果，名字起了一个商品的名称
        myEsEntity.setName("六常米");
        myEsEntity.setSex("man");
        myEsEntity.setAge(22);
        esTestService.add(myEsEntity);

        myEsEntity = new MyEsEntity();
        myEsEntity.setId(5);

        //为了后期显示出ik分词器的效果，名字起了一个商品的名称
        myEsEntity.setName("五常米");
        myEsEntity.setSex("woman");
        myEsEntity.setAge(50);
        esTestService.add(myEsEntity);
    }

    @RequestMapping(value="/delete")
    public void delete(){
        esTestService.delete();
    }

    @RequestMapping(value = "/select")
    public List<MyEsEntity> select(){
        List<MyEsEntity> myEsEntity=esTestService.select();
        return myEsEntity;
    }
}
