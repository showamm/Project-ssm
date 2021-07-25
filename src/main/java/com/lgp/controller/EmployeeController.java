package com.lgp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgp.bean.Employee;
import com.lgp.bean.Msg;
import com.lgp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        PageHelper.startPage(pn,10);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);
        return "list";
    }

    @RequestMapping("/empsjs")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        PageHelper.startPage(pn,10);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps,5);
        Msg msg = Msg.success().add("pageInfo", page);
        return msg;
    }

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            Map<String,Object> map = new HashMap<String, Object>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for(FieldError fieldError:fieldErrors){
                System.out.println("==========================================================");
                System.out.println("错误的字段名： "+fieldError.getField());
                System.out.println("错误信息： "+fieldError.getDefaultMessage());
                System.out.println("==========================================================");
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }
        employeeService.saveEmp(employee);
        return Msg.success();
    }

    /**
     * 检验用户名是否可用
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkUse(@RequestParam(value = "empName") String empName){
        //先判断用户名是否是合法表达式，和前端统一标准
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(regx)){
            return Msg.fail().add("va_msg","用户名可以是2-5位中文或者6-16位英文和数字的组合(mvc校验)");
        }

        boolean b = employeeService.checkUser(empName);
        if(b){
            return Msg.success();
        }
        else {
            return Msg.fail().add("va_msg","用户名已存在(mvc校验)");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public  Msg getEmp(@PathVariable("id") Integer id){
        Employee emp = employeeService.getEmp(id);
        Msg msg = Msg.success().add("emp", emp);
        return msg;
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result,@PathVariable("id") Integer id){
        System.out.println(" 执行了public Msg saveEmp(@Valid Employee employee, BindingResult result,@PathVariable(\"id\") Integer id)");
        if(result.hasErrors()){
            Map<String,Object> map = new HashMap<String, Object>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for(FieldError fieldError:fieldErrors){
                System.out.println("==========================================================");
                System.out.println("错误的字段名： "+fieldError.getField());
                System.out.println("错误信息： "+fieldError.getDefaultMessage());
                System.out.println("==========================================================");
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }
        employee.setEmpId(id);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/emp/{ids}")
    public Msg deleteEmp(@PathVariable("ids")String ids){
        if(ids.contains("-")){
            List<Integer> del_ids =new ArrayList<>();
            String[] str_ids = ids.split("-");
            for(String string:str_ids){
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
            return Msg.success();
        }else {
            int i = Integer.parseInt(ids);
            employeeService.deleteEmp(i);
            return Msg.success();
        }

    }


}
