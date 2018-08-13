package xin.carryzheng.sssp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xin.carryzheng.sssp.entity.Employee;
import xin.carryzheng.sssp.service.DepartmentService;
import xin.carryzheng.sssp.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengxin
 * @date 2018-08-11 13:36:39
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;


    @ResponseBody
    @RequestMapping(value = "/testJson")
    public Map<String, Object> testJson(){
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "Json");
        return map;
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id")Integer id){
        employeeService.delete(id);
        return "redirect:/emps";
    }


    /**
     * @author zhengxin
     * @date   18/8/11 下午7:50
     * 每次每个http请求都先回调用该方法,在其他方法通过@ModelAttribute注解标记在入参上直接进行注入
     */
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false)Integer id,
                            Map<String, Object> map){
        if(id != null){
            Employee employee = employeeService.get(id);
            System.out.println("getEmployee:" + employee.hashCode());
            employee.setDepartment(null);

            map.put("hhh", employee);
        }

    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String update(@ModelAttribute("hhh")Employee employee){

        System.out.println("update:" + employee.hashCode());
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id")Integer id, Map<String, Object> map){

        map.put("employee", employeeService.get(id));
        map.put("departments", departmentService.getAll());

        return "emp/input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee){
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxValidateLastName", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String validateLastName(@RequestParam(value = "lastName")String lastName){
        Employee employee = employeeService.getByLastName(lastName);
        if(employee == null){
            return  "0";
        }else {
            return "1";
        }

    }


    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    private String input(Map<String, Object> map){
        map.put("departments", departmentService.getAll());
        map.put("employee", new Employee());
        return "emp/input";
    }

    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
                       Map<String, Object> map){

        int pageNo = 1;

        try {
            pageNo = Integer.parseInt(pageNoStr);
            if (pageNo < 1)
                pageNo = 1;
        }catch (Exception e){
            e.printStackTrace();
        }

        Page<Employee> page = employeeService.getPage(pageNo, 5);
        map.put("page", page);

        return "emp/list";
    }

}
