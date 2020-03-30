package com.jayer.vhr.service;

import com.jayer.vhr.mapper.PositionMapper;
import com.jayer.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;
    public List<Position> getAllPositions(){
        return positionMapper.getAllPositions();
    }
}
