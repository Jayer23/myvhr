package com.jayer.vhr.service;

import com.jayer.vhr.mapper.MenuMapper;
import com.jayer.vhr.model.Hr;
import com.jayer.vhr.model.Menu;
import com.jayer.vhr.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames="my_menus_cache")
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Cacheable
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getMenusByHrId(){
        return menuMapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
    }
}
