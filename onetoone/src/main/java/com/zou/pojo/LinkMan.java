package com.zou.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *  pojo的customer配置一对多  此类配置多对一
 * @author BinPeng
 * @date 2020/4/27 10:43
 */
@Entity
@Table(name = "cst_linkman")
public class LinkMan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长
    @Column(name = "lkm_id")
    private Long    lkm_id;// '联系人编号(主键)',
    @Column(name = "lkm_name")
    private String  lkmName;// '联系人姓名',
    @Column(name = "lkm_gender")
    private String  lkmGender;// '联系人性别',
    @Column(name = "lkm_phone")
    private String  lkmPhone;// '联系人办公电话',
    @Column(name = "lkm_mobile")
    private String  lkmMobile;// '联系人手机',
    @Column(name = "lkm_email")
    private String  lkmEmail;//'联系人邮箱',
    @Column(name = "lkm_position")
    private String  lkmPosition;//'联系人职位',
    @Column(name = "lkm_memo")
    private String  lkmMemo;// '联系人备注',


    /*配置多对一  联系人(一)到客户(多)
      使用注解的形式配置        多对一
        1.配置表关系
            @ManyToOne :多对一
                targetEntity :对方实体类字节码
        2.配置外键(中间表)
                name:外键名 referencedColumnName:外键对应的主键名
       配置外键过程,配置到了多的一方,就会在多的一方维护外键
    * */
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;//客户对象(一)


    public Long getLkm_id() {
        return lkm_id;
    }

    public void setLkm_id(Long lkm_id) {
        this.lkm_id = lkm_id;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "lkm_id=" + lkm_id +
                ", lkmName='" + lkmName + '\'' +
                ", lkmGender='" + lkmGender + '\'' +
                ", lkmPhone='" + lkmPhone + '\'' +
                ", lkmMobile='" + lkmMobile + '\'' +
                ", lkmEmail='" + lkmEmail + '\'' +
                ", lkmPosition='" + lkmPosition + '\'' +
                ", lkmMemo='" + lkmMemo + '\'' +
                ", customer=" + customer +
                '}';
    }
}
