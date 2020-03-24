package com.jayer.vhr.mapper;

import com.jayer.vhr.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    List<Menu> getAllMenusWithRole();
}
