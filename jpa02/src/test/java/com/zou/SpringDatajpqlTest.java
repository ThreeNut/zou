package com.zou;

import com.zou.dao.CustomerDao;
import com.zou.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 复杂查询 自定义jqpl
 * @author BinPeng
 * @date 2020/4/23 21:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicatonContext.xml") //指定spring容器配置信息
public class SpringDatajpqlTest {
    @Autowired
    private CustomerDao customerDao; //注入
    //根据客户名称查询对象
    @Test
    public void testFindJpql(){
        Customer customer = customerDao.findByName("zou1");
        System.out.println(customer);
    }
    //根据客户名称和客户id查询对象
    @Test
    public void findNameAndId(){
        Customer customer = customerDao.findByNameAndId("zou1",1L);
        System.out.println(customer);
    }
}
