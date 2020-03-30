package com.jayer.vhr.mapper;

import com.jayer.vhr.model.Position;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PositionMapper {
    List<Position> getAllPositions();
}
