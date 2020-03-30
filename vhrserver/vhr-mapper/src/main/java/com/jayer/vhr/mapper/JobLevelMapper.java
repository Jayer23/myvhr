package com.jayer.vhr.mapper;

import com.jayer.vhr.model.JobLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobLevelMapper {
    List<JobLevel> getAllJobLevels();
}
