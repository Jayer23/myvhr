package com.jayer.vhr.mapper;

import com.jayer.vhr.model.Politicsstatus;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PoliticsstatusMapper {
    List<Politicsstatus> getAllPoliticsstatus();

}
