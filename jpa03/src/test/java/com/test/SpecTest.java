package com.test;

import com.zou.dao.CustmerDao;
import com.zou.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 *  ----------------Specifications动态查询----------------------------
 * @author BinPeng
 * @date 2020/4/25 16:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicatonContext.xml")
public class SpecTest {

    @Autowired
    private CustmerDao custmerDao;
    //查询单个对象
    /*自定义查询条件 (匿名内部类)
    *   1.实现Specification接口(提供泛型,查询的对象的类型)
    *   2.实现toPredicate方法(构造查询条件)
    *   3.需要借助方法参数中的两个参数(
    *           1.查询方式
    *               cb对象
    *           2.比较对象
    *               root对象
    *       root: 获取需要查询的对象属性)
    *       CriteriaBuilder : 构造查询条件,内部封装了很多的查询条件
    * */
    @Test //根据客户名称查询数据
    public void testSpec() {
        Specification<Customer> spec = new Specification<Customer>() {

            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //1.获取比较属性(pojo里面的属性名)
                Path<Object> custName = root.get("custName");
                //2.构建查询条件 select * from cst_customer where cust_name = 'zou1'
                return cb.equal(custName, "zou1");//精准匹配查询 第一个参数: path对象需要比较的属性,第二个参数 当前需要比较的取值 (比较的属性,比较的属性的取值)

            }
        };
        Customer one = custmerDao.findOne(spec);
        System.out.println(one);
    }

    //根据客户名和 客户所属的行业进行查询
    @Test
    public void findOne() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                /* root :1.客户名  2.客户所属的行业
                    cb: 构造查询条件
                        1.构造客户的精准匹配查询
                        2.构造行业的精准匹配查询
                        3.将以上两个查询联系起来
                * */
                Path<Object> custName = root.get("custName"); //获取第一个属性
                Path<Object> custIndustry = root.get("custIndustry");//第二个属性
                Predicate name = cb.equal(custName, "zou2"); //匹配1
                Predicate it = cb.equal(custIndustry, "it");//匹配2
                return cb.and(it, name);//联合  or是或者,and是并且
            }
        };
        Customer one = custmerDao.findOne(spec);
        System.out.println(one);
    }

    //根据客户名进行 模糊查询
    @Test
    public void findOneLike() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                //模糊查询
              return   cb.like(custName.as(String.class),"z%");//注意like等...需要制定数据类型  .as(....)
            }
        };
        List<Customer> one = custmerDao.findAll(spec);
        one.forEach(System.out::println);
    }
    //根据客户名进行 模糊查询  排序(desc倒序   asc升序)
    @Test
    public void findOneLikeSort() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                //模糊查询
                return   cb.like(custName.as(String.class),"z%");//注意like等...需要制定数据类型  .as(....)
            }
        };
        //添加排序
        Sort descSort=new Sort(Sort.Direction.DESC,"custId");//倒序  asc:升序
        List<Customer> listCustomer = custmerDao.findAll(spec,descSort);
        listCustomer.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testPage(){
        Specification spec=null;
        /*分页参数 :查询的页码,每页查询的条件
            返回 springDataJpa封装好的PageBean对象,数据列表,共条数
        * */
        Pageable pageable = new PageRequest(0,2);//0 代表第一页
        Page<Customer> page = custmerDao.findAll(null,pageable);//返回一个pageBean
        System.out.println("所有数据的总个数:   "+page.getTotalElements());
        System.out.println("第一页集合数据:"+page.getContent());
        System.out.println("每页显示2条---总页数:   "+page.getTotalPages());
    }
}
