package com.zou.dao;

import com.zou.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 *       1.创建一个Dao层接口，并实现JpaRepository和JpaSpecificationExecutor
 *  	 2.提供相应的泛型
 *  	 封装了curd操作  和分页查询
 * @author BinPeng
 * @date 2020/4/22 17:05
 */

public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    /*  稍微复杂查询需要自定义方法 通过jpql 进行查询(像通用mapper)
    根据客户名称查询客户 jpql from Customer where custName = ?
    */
    @Query(value = " from Customer where custName = ? ")
    public Customer findByName(String name);

    //根据根据客户名称和客户id查询客户
    @Query(value = "from Customer where custName = ? and custId = ? ")//多个占位符
    public Customer findByNameAndId(String name,Long id);//注意字段顺序对应注解字段顺序

    /*也可以不对应占位符顺序   (不推荐)
    *  @Query(value = "from Customer where custName = ?2 and custId = ?1 ")//多个占位符
        public Customer findByNameAndId(Long id,String name);
    * */

}
