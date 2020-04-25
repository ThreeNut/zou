package com.zou.dao;

import com.zou.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author BinPeng
 * @date 2020/4/25 16:39
 */
public interface CustmerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

}
