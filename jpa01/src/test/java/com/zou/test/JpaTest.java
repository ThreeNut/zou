package com.zou.test;

import com.zou.pojo.Customer;
import com.zou.util.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 测试 入门案例  *********************基本CURD操作***************
 * @author BinPeng
 * @date 2020/4/20 20:36
 */
public class JpaTest {

    /**
     * 创建实体管理类工厂，借助Persistence的静态方法获取
     * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
     *
     */
    //persist  保存
    @Test
    public void test(){

        //1.加载配置文件创建工厂(实体管理器工厂)对象
       // EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager em = JpaUtil.getEntityManager();
        //3.获取事务对象,开启事务
        EntityTransaction tx = em.getTransaction();
        //4.完成增删改查操作
        tx.begin();//开启事务
        Customer customer=new Customer();
        customer.setCustName("zou");
        customer.setCustAddress("邹庄村3");
        em.persist(customer);//保存
        //5.提交事务
        tx.commit();
        //6.释放资源
        em.close();
      //  factory.close();
    }
    //立即加载  查询   find
    @Test
    public void findById(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        //增删改查
        Customer customer = manager.find(Customer.class, 1L);//查询
        System.out.println(customer);
        transaction.commit();//提交事务
        manager.close();//释放
    }
    //推荐延迟加载   getReference
    @Test
    public void findById2(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        //查询  延迟加载,
        Customer customer = manager.getReference(Customer.class, 1L);//查询
//        什么时候使用才会进行数据库的查询
        System.out.println(customer);//此时才会查询数据库(动态代理)
        transaction.commit();//提交事务
        manager.close();//释放
    }
    //删除操作  remove
    @Test
    public void delete(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        //查询要删除的对象
        Customer customer = manager.find(Customer.class, 4L);
        //调用remove删除
        manager.remove(customer);
        transaction.commit();//提交事务
        manager.close();//释放
    }
    //更新操作 merge
    @Test
    public void update(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        //查询要更新的对象
        Customer customer = manager.find(Customer.class, 3L);
        customer.setCustLevel("666");
        manager.merge(customer);//更新
        transaction.commit();//提交事务
        manager.close();//释放
    }
}
