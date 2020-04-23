package com.zou.dao;

import com.zou.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

}
