package com.myproject.login.dao.elasticsearch;

import java.io.Serializable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseElasticsearchRepository<T, ID extends Serializable> extends ElasticsearchRepository<T, ID>{

}
