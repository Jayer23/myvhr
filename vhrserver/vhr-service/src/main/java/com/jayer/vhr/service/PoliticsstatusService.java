package com.jayer.vhr.service;

import com.jayer.vhr.mapper.PoliticsstatusMapper;
import com.jayer.vhr.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PoliticsstatusService {
    @Autowired
    PoliticsstatusMapper politicsstatusMapper;
    public List<Politicsstatus> getAllPoloticsstatus(){
        return politicsstatusMapper.getAllPoliticsstatus();
    }
}
