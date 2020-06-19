package com.example.demo003.controller;

import com.example.demo003.dao.DepartmentDao;
import com.example.demo003.dao.EmployeeDao;
import com.example.demo003.entities.Department;
import com.example.demo003.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps",employees);

        /**thymeleaf默认配置文件中默认给return返回值拼接以下前缀和后缀：
         * 前缀：private String prefix = "classpath:/templates/";
         * 后缀：private String suffix = ".html";
         * 系统会自动到此路径下寻找相应的文件
         */
        return "emp/list";
    }

    //返回员工添加页面
    @GetMapping("/emp")
    public String addPage(Model model){
        //来到页面之前，查询出所有部门信息，在此页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //添加员工
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){

        System.out.println(employee);

        employeeDao.save(employee);

        //来到员工列表页面
        //redirect：从定向到一个地址
        //forward：转发到一个地址
        //“/”代表当前项目路径
        return "redirect:/emps";
    }

    @GetMapping("/test")
    public String test(){
        return "emp/test";
    }
}
