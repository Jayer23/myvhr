package com.jayer.vhr.mapper;

import com.jayer.vhr.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface EmployMapper {
    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);
    Long getTotal(@Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);
    int insertSelective(Employee record);
    Employee getEmployeeById(Integer id);
    Integer maxWorkID();
    Integer deleteByPrimaryKey(Integer id);
    Integer updateByPrimaryKeySelective(Employee record);


}
