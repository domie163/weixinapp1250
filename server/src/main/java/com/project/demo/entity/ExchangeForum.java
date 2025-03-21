package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 交流论坛：(ExchangeForum)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ExchangeForum")
public class ExchangeForum implements Serializable {

    // ExchangeForum编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_forum_id")
    private Integer exchange_forum_id;

    // 论坛标题
    @Basic
    private String forum_title;
    // 封面
    @Basic
    private String cover;
    // 论坛标签
    @Basic
    private String forum_label;
    // 帖子内容
    @Basic
    private String post_content;
    // 发布用户
    @Basic
    private Integer publishing_users;
    // 发布日期
    @Basic
    private Timestamp release_date;

    // 点击数
    @Basic
    private Integer hits;

    // 点赞数
    @Basic
    private Integer praise_len;












    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
