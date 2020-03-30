package com.jayer.vhr.mapper;

import com.jayer.vhr.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    public List<Department> getAllDepartmentsByParentId(Integer pid);

}
