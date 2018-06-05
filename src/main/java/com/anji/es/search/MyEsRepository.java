package com.anji.es.search;

import com.anji.es.search.entity.MyEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/4 22:47
 */
public interface MyEsRepository extends ElasticsearchRepository<MyEsEntity,Integer> {
}
