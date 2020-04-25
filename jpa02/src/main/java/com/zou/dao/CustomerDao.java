package com.zou.dao;

import com.zou.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
      --------------- @Query 代表查询-----------------
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

    //根据id更新 电话  注意由于是自己定义的方法 所以需要添加事务
    @Query(value = "update Customer set custPhone = ?2 where custId = ?1 ")
    @Modifying //表示...@Query是更新的操作
    public  void updateById(Long id,String phone);

    //------------------使用sql语句查询 所有数据-------------
    @Query(value = "select * from cst_customer ", nativeQuery = true)//nativeQuery 使用本地sql语句查询
    public List<Object [] > findAllSql();

    //sql语句 条件查询
    @Query(value = "select * from cst_customer where cust_name like ?",nativeQuery = true)
    public List<Object []> findBySql(String name);

/*    方法命名规则查询
*     按照Spring Data JPA 定义的规则，
* 查询方法以findBy开头，涉及条件查询时，
* 条件的属性用条件关键字连接，要注意的是：
* 条件属性首字母需大写。框架在进行方法名解析时，
* 会先把方法名多余的前缀截取掉，然后对剩下部分进行解析。

* -------------方法命名的约定--------------
*   findBy : 查询
*   对象(pojo)中的属性名(首字母大写) : 查询条件
*   findByCustName --根据客户名查询
* */
    public Customer findByCustName(String custName);

    //模糊查询  findBy + pojo属性名 + 查询方式 like
    public List<Customer> findByCustNameLike(String custName);

    //多条件查询    findBy + pojo属性名 + 查询方式 like 多条件连接符(and|or) + 属性名 + 查询方式
    //使用客户名称模糊匹配和客户所属行业精准匹配查询
    public Customer findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
}
