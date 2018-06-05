package com.anji.es.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/4 22:48
 */
//lombok注解(可自己手动创建getset以及有参无参构造来代替)
@Data
@NoArgsConstructor
@AllArgsConstructor
//es注解，设置索引名称以及类型
@Document(indexName = "myes",type = "info")
public class MyEsEntity {
    //id(需要添加@Id注解,或会自动识别名称为id的字段为id,其余字段没有限制)
    @Id
    private Integer id;

    private String name;

    private String sex;

    private Integer age;
}
