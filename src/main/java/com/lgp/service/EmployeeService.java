package com.lgp.service;

import com.lgp.bean.Employee;
import com.lgp.bean.EmployeeExample;
import com.lgp.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll(){
        return employeeMapper.selectByExamplewithDept(null);
    }

    public void saveEmp(Employee employee){
        int i = employeeMapper.insertSelective(employee);
    }

    public boolean checkUser(String empName){
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long l = employeeMapper.countByExample(example);
        return l==0;
    }

    public Employee getEmp(Integer id){
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    public void updateEmp(Employee employee) {
        System.out.println(employee);
        employeeMapper.updateByPrimaryKeySelective(employee);
        System.out.println("==========================================================");
        System.out.println(" employeeMapper.updateByPrimaryKeySelective(employee);");
        System.out.println("==========================================================");
    }


    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> str_ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(str_ids);
        employeeMapper.deleteByExample(example);


    }
}
