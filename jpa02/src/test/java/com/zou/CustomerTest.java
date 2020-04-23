package com.zou;

import com.zou.dao.CustomerDao;
import com.zou.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author BinPeng
 * @date 2020/4/23 9:48
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicatonContext.xml") //指定spring容器配置信息
public class CustomerTest {
    @Autowired
    private CustomerDao customerDao; //注入
    //根据id查询一个对象
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(3L);
        System.out.println(customer);
    }
    //保存或者更新 根据对象是否存在主键id,如果没有id那么是保存,存在id更新
    @Test
    public void save(){
        Customer customer=new Customer();
        customer.setCustId(5L);//存在id
        customer.setCustAddress("马厂镇");
        customer.setCustLevel("88");
        customer.setCustPhone("1888888888");
        customer.setCustName("java");
        customerDao.save(customer);
    }
    //根据id删除
    @Test
    public void delete(){
     customerDao.delete(5L);
    }
    //查询所有
    @Test
    public void FindAll(){
        List<Customer> all = customerDao.findAll();
        all.forEach(System.out::println);
    }
}
