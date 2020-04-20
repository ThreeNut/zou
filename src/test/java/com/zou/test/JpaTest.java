package com.zou.test;

import com.zou.pojo.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 测试 入门案例
 * @author BinPeng
 * @date 2020/4/20 20:36
 */
public class JpaTest {

    /**
     * 创建实体管理类工厂，借助Persistence的静态方法获取
     * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
     *
     */
    @Test
    public void test(){

        //1.加载配置文件创建工厂(实体管理器工厂)对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获取事务对象,开启事务
        EntityTransaction tx = em.getTransaction();
        //4.完成增删改查操作
        tx.begin();//开启事务
        Customer customer=new Customer();
        customer.setCustName("zou");
        customer.setCustAddress("邹庄村2");
        em.persist(customer);//保存
        //5.提交事务
        tx.commit();
        //6.释放资源
        em.close();
        factory.close();
    }
}
