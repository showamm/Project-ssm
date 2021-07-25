package com.lgp.test;

import com.lgp.bean.Department;
import com.lgp.bean.Employee;
import com.lgp.bean.EmployeeExample;
import com.lgp.dao.DepartmentMapper;
import com.lgp.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class DepartmentTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void getDeptId() {
        Employee employee = new Employee(null, null, "M", null, 1);
        EmployeeExample employeeExample = new EmployeeExample();
        //构造自定义查询条件
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdBetween(10,200);

        List<Employee> employees = employeeMapper.selectByExamplewithDept(employeeExample);
        System.out.println("============================================");
        System.out.println(employees);
        System.out.println("============================================");

    }

    @Test
    public void setDeptId() {
        EmployeeMapper mapper = sqlSessionTemplate.getMapper(EmployeeMapper.class);

        for(int i=0;i<1000;i++){
            String substring = UUID.randomUUID().toString().substring(0, 6)+i;
            mapper.insertSelective(new Employee(null, substring, "M", "qq@monster.com", 1));
        }

    }

    @Test
    public void getDeptName() {
    }

    @Test
    public void setDeptName() {
    }
}