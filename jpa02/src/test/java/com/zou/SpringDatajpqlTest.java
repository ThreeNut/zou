package com.zou;

import com.zou.dao.CustomerDao;
import com.zou.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    //---------------------------jpql查询-------------------------------------------
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
    /*根据id更新 电话
    注意   1.由于是自己定义的方法 所以需要添加事务@Transactional
          2. jpa默认回滚事务 所以更新失败   @Rollback
    * */
    @Test
    @Transactional
    @Rollback(value = false) //关闭事务回滚
    public void updateById(){
        customerDao.updateById(1L,"88");
    }
//------------------------ sql语句查询-----------------------------
    //使用本地sql语句查询
    @Test
    public void findAllSql(){
        List<Object[]> allSql = customerDao.findAllSql();
        for (Object [] objects :allSql){
            System.out.println(Arrays.toString(objects));
        }
    }
    //使用sql语句进行 条件查询
    @Test
    public void findBySql(){
        List<Object[]> bySql = customerDao.findBySql("zou%");
        for(Object[] objects:bySql){
            System.out.println(Arrays.toString(objects));
        }
    }
//-------------------方法命名规则查询------------------------------------
    /*顾名思义，方法命名规则查询就是根据方法的名字，就能创建查询。
    只需要按照Spring Data JPA提供的方法命名规则定义方法的名称，就可以完成查询工作。
    Spring Data JPA在程序执行的时候会根据方法名称进行解析，并自动生成查询语句进行查询*/
    //查询
    @Test
    public void findByName() {
        Customer zou1 = customerDao.findByCustName("zou1");
        System.out.println(zou1);
    }
    //模糊查询
    @Test
    public void findByLike() {
        List<Customer> byCustNameLike = customerDao.findByCustNameLike("z%");
        byCustNameLike.forEach(System.out::println);
    }

    //使用 客户名称模糊匹配  且 客户所属行业精准匹配  查询
    @Test
    public void findByLikeMany() {
        Customer it = customerDao.findByCustNameLikeAndCustIndustry("z%", "it");
        System.out.println(it);
    }
}
//25集