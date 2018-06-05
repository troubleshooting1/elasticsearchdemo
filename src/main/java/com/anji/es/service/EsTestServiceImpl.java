package com.anji.es.service;

import com.anji.es.search.MyEsRepository;
import com.anji.es.search.entity.MyEsEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/4 22:48
 */
@Service
public class EsTestServiceImpl implements EsTestService {
    @Resource
    MyEsRepository myEsRepository;

    @Override
    public void add(MyEsEntity myEsEntity) {
        myEsRepository.save(myEsEntity);
    }

    public static QueryBuilder createQuery() {
        BoolQueryBuilder query = QueryBuilders.boolQuery();

        QueryBuilder query1 = QueryBuilders.fuzzyQuery("addr", "BeiJing");
        // addr = Beijing
        query.must(query1);
        return query;
    }

    /**
     * 根据id查询
     *
     * @return
     */
    @Override
    public List<MyEsEntity> select() {
//        Iterable<MyEsEntity> data = myEsRepository.findAll();
//        List<MyEsEntity> content = new ArrayList<>();
//        for (MyEsEntity d : data) {
//            content.add(d);
//        }
//        return content;


        //创建builder
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not
        //设置模糊搜索
//        builder.must(QueryBuilders.fuzzyQuery("name", "五常大米"));
        builder.must(QueryBuilders.matchQuery("name", "五常米"));
        //设置性别必须为man
        builder.must(new QueryStringQueryBuilder("man").field("sex"));

        //按照年龄从高到低
        FieldSortBuilder sort = SortBuilders.fieldSort("age").order(SortOrder.DESC);

        //设置分页(拿第一页，一页显示两条)
        //注意!es的分页api是从第0页开始的(坑)
        PageRequest page = new PageRequest(0, 2);

        //构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //执行
        Page<MyEsEntity> search = myEsRepository.search(query);

        //获取总条数(前端分页需要使用)
        int total = (int) search.getTotalElements();

        //获取查询到的数据内容
        List<MyEsEntity> content = search.getContent();

        //为了方便我就不显示总条数了，只在控制台给各位同学打印总条数看一下了
        System.out.println(total);
        return content;
    }

    @Override
    public void delete() {
        //        MyEsEntity entity=(MyEsEntity)myEsRepository.findById(1);
        //        myEsRepository.delete(entity);
        myEsRepository.deleteById(1);
    }
}
