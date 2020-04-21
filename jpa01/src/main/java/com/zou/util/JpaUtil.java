package com.zou.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 创建jpa的工具类
 * 在 JPA 规范中, EntityTransaction是完成事务操作的核心对象，
 * 对于EntityTransaction在我们的java代码中承接的功能比较简单
 *
 *
 *  static修饰的变量为静态变量，静态变量在内存中只有一份存储空间，静态变量不属于某个实例对象，被一个类中的所有对象所共享，
 *  属于类，所以也叫类变量，可以直接通过类名来引用。
 * @author BinPeng
 * @date 2020/4/21 11:48
 */
public class JpaUtil {
    private static EntityManagerFactory factory;
    static {
        //加载配置文件
        factory = Persistence.createEntityManagerFactory("myJpa");
    }
    //获取entityManager对象
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
