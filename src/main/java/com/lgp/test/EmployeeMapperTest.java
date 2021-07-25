package com.lgp.test;

import com.lgp.bean.Department;
import com.lgp.dao.DepartmentMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class EmployeeMapperTest {

    @Test
    public void countByExample() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        DepartmentMapper departmentMapper = context.getBean("departmentMapper", DepartmentMapper.class);
        System.out.println(departmentMapper);
        departmentMapper.ins(new Department(1,"ss"));
    }

    @Test
    public void deleteByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByExamplewithDept() {
    }

    @Test
    public void selectByPrimaryKeyWithDept() {
    }
}