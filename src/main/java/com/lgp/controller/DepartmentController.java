package com.lgp.controller;

import com.lgp.bean.Department;
import com.lgp.bean.Msg;
import com.lgp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     *
     *返回所有部门信息
     */
    @ResponseBody
    @RequestMapping("/depts")
    public Msg getDeps(){
        List<Department> depts = departmentService.getDepts();
        Msg msg = Msg.success().add("depts", depts);
        return msg;

    }
}
