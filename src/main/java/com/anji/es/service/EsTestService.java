package com.anji.es.service;

import com.anji.es.search.entity.MyEsEntity;

import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/4 22:48
 */
public interface EsTestService {
    void add(MyEsEntity myEsEntity);

    List<MyEsEntity> select();

    void delete();
}
