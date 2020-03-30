package com.jayer.vhr.controller.emp;

import com.jayer.vhr.model.*;
import com.jayer.vhr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    NationService nationService;
    @Autowired
    PositionService positionService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope){
        return employeeService.getEmpoyeeBypage(page, size, employee, beginDateScope);
    }
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.getAllNations();
    }
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusService.getAllPoloticsstatus();
    }
    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @PostMapping("/")
    public ResBean addEmp(@RequestBody Employee employee){
        if(employeeService.addEmp(employee) == 1){
            return ResBean.ok("添加成功");
        }else {
            return ResBean.error("添加失败");
        }
    }
    @GetMapping("/maxWorkID")
    public ResBean maxWorkID() {
        ResBean respBean = ResBean.build().setStatus(200)
                .setObj(String.format("%08d", employeeService.maxWorkID() + 1));
        return respBean;
    }

    @DeleteMapping("/{id}")
    public ResBean deleteEmpByEid(@PathVariable Integer id){
        if(employeeService.deleteEmployeeById(id) == 1){
            return ResBean.ok("删除成功");
        }else {
            return ResBean.error("删除失败");
        }
    }

    @PutMapping("/")
    public ResBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateEmp(employee) == 1){
            return ResBean.ok("更新成功");
        }else {
            return ResBean.error("更新失败");
        }
    }


}
