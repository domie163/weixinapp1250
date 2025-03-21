package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 失物招领：(LostAndFound)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LostAndFound")
public class LostAndFound implements Serializable {

    // LostAndFound编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_and_found_id")
    private Integer lost_and_found_id;

    // 物品名称
    @Basic
    private String item_name;
    // 物品类型
    @Basic
    private String item_type;
    // 物品图片
    @Basic
    private String item_picture;
    // 招领地址
    @Basic
    private String pickup_address;
    // 物品状态
    @Basic
    private String item_status;
    // 发布用户
    @Basic
    private Integer publishing_users;
    // 详情内容
    @Basic
    private String details;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
