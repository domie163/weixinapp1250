package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 寻物启示：(Revelation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Revelation")
public class Revelation implements Serializable {

    // Revelation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revelation_id")
    private Integer revelation_id;

    // 物品名称
    @Basic
    private String item_name;
    // 物品类型
    @Basic
    private String item_type;
    // 物品图片
    @Basic
    private String item_picture;
    // 丢失地址
    @Basic
    private String missing_address;
    // 丢失时间
    @Basic
    private Timestamp lost_time;
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
