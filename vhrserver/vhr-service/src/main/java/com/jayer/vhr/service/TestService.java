package com.jayer.vhr.service;

import com.jayer.vhr.mapper.HrMapper;
import com.jayer.vhr.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    HrMapper hrMapper;
    public Hr selectByPrimaryKey(Integer id){
        Hr hr = hrMapper.selectByPrimaryKey(id);
        return hr;
    }

}
