package ggq.Controller;

import ggq.dao.DepartmentDao;
import ggq.dao.EmployeeDao;
import ggq.entities.Department;
import ggq.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAdd(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定 要求请求参数的名字和入参对象的属性名相同
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
        // 来到员工列表界面
        // redirect 表示重定向
        // forward 表示转发
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEitPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        model.addAttribute("emp", employee);
        return "emp/add";
    }

    @PutMapping("/emp/")
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
