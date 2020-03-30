package com.jayer.vhr.service;

import com.jayer.vhr.mapper.NationMapper;
import com.jayer.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;
    public List<Nation> getAllNations(){
        return nationMapper.getAllNations();
    }
}
