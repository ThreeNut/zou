package com.zou.pojo;


import javax.persistence.*;
import java.io.Serializable;

/**
 * ************************JPA 入门案例**************************
 *
 * 客户实体类
 *  配置映射关系
 *      1.实体类和表的映射关系
 *      2.实体类中属性和表中字段的映射关系
 *
 * @author BinPeng
 * @date 2020/4/20 19:48
 *
 */
@Entity//声名实体类
@Table(name = "cst_customer") //name属性就是数据库表的名称
public class Customer implements Serializable {

    @Id//主键
    /* GenerationType.IDENTITY :使用mysql 可以自增长
       GenerationType.SEQUENCE :序列 oracle数据库的自增长
       GenerationType.TABLE :jpa提供的一种机制通过一张数据表的形式帮助数据自增
       GenerationType.AUTO 自动选择
    * */
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长
    @Column(name = "cust_id")  //数据库的字段
    private Long custId;        //客户主键
    @Column(name = "cust_name")
    private String custName;    //名称
    @Column(name = "cust_source")
    private String custSource;  //来源
    @Column(name = "cust_industry")
    private String custIndustry;//级别
    @Column(name = "cust_level")
    private String custLevel;   //行业
    @Column(name = "cust_address")
    private String custAddress; //地址
    @Column(name = "cust_phone")
    private String custPhone;   //电话


    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

}
