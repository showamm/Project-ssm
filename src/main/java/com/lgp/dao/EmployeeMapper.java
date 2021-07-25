package com.lgp.dao;

import com.lgp.bean.Employee;
import com.lgp.bean.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(Employee example);

    Employee selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> selectByExamplewithDept(EmployeeExample example);

    Employee selectByPrimaryKeyWithDept(Integer empid);
}