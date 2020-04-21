package com.zou.test;

import com.zou.util.JpaUtil;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * 使用jpql查询
 * @author BinPeng
 * @date 2020/4/21 15:43
 */

public class JpqlTest {
    //查询全部
    @Test
    public void findAll(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        String jpql="from Customer"; //jpql不支持* 所以前面不写
        Query query = manager.createQuery(jpql);//创建query查询对象
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);

        transaction.commit();//提交事务
        manager.close();//释放
    }
    //查询全部倒序 DESC
    @Test
    public void findAllDESC(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        String jpql="from Customer ORDER BY custId desc";
        Query query = manager.createQuery(jpql);//创建query查询对象
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);

        transaction.commit();//提交事务
        manager.close();//释放
    }

    //统计查询
    @Test
    public void findCount(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        String jpql="select count(custId) from Customer";
        Query query = manager.createQuery(jpql);//创建query查询对象
        long singleResult = (long) query.getSingleResult();
        System.out.println(singleResult);
        transaction.commit();//提交事务
        manager.close();//释放
    }

    //分页查询
    @Test
    public void findPage(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        String jpql="from Customer";
        Query query = manager.createQuery(jpql);//创建query查询对象
        //起始索引
        query.setFirstResult(0);//从0查询不包含0
        query.setMaxResults(2);//截止到哪
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);

        transaction.commit();//提交事务
        manager.close();//释放
    }

    //条件查询
    @Test
    public void findLike(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();//开启事务
        String jpql=" from Customer where custName like ? ";
        Query query = manager.createQuery(jpql);//创建query查询对象
        query.setParameter(1,"zou%");//第一个参数占位符的索引位置,第二个参数:取值
        List resultList = query.getResultList();
       for (Object o:resultList){
           System.out.println(o);
       }

        transaction.commit();//提交事务
        manager.close();//释放
    }
}
