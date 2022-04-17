package org.catcat.sereinfish.recall.db.service;

import com.icecreamqaq.yudb.jpa.annotation.Transactional;
import org.catcat.sereinfish.recall.db.dao.TestDao;
import org.catcat.sereinfish.recall.db.entity.TestEntity;

import javax.inject.Inject;
import java.util.List;

public class TestService {

    @Inject
    TestDao dao;

    @Transactional
    public void save(TestEntity entity){
        System.out.println("数据保存");
        dao.save(entity);
    }

    @Transactional
    public List<TestEntity> findAll(){
        return dao.findAll();
    }
}
