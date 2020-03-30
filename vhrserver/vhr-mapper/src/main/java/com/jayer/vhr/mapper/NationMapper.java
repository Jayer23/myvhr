package com.jayer.vhr.mapper;

import com.jayer.vhr.model.Nation;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NationMapper {
    List<Nation> getAllNations();
}
