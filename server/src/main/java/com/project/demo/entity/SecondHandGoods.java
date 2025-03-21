package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 二手物品：(SecondHandGoods)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SecondHandGoods")
public class SecondHandGoods implements Serializable {

    // SecondHandGoods编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "second_hand_goods_id")
    private Integer second_hand_goods_id;

    // 物品序号
    @Basic
    private String item_no;
    // 物品名称
    @Basic
    private String item_name;
    // 封面
    @Basic
    private String cover;
    // 物品类型
    @Basic
    private String item_type;
    // 物品规格
    @Basic
    private String item_specification;
    // 物品数量
    @Basic
    private Integer item_quantity;
    // 物品单价
    @Basic
    private Integer item_unit_price;
    // 交易地址
    @Basic
    private String transaction_address;
    // 卖方
    @Basic
    private Integer seller;
    // 商品描述
    @Basic
    private String product_description;


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
