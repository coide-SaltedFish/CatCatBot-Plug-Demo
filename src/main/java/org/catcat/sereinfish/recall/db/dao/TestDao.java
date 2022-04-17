package org.catcat.sereinfish.recall.db.dao;

import com.icecreamqaq.yudb.YuDao;
import com.icecreamqaq.yudb.jpa.annotation.Dao;
import org.catcat.sereinfish.recall.db.entity.TestEntity;

import java.util.List;

@Dao
public interface TestDao extends YuDao<TestEntity, Integer> {
    List<TestEntity> findAll();
}
