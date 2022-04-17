package org.catcat.sereinfish.recall.db.entity;

import javax.persistence.*;

@Table(name = "cat_test")
@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    long time = System.currentTimeMillis();

    public TestEntity() {
    }
}
