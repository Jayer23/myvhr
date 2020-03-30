package com.jayer.vhr.mapper;

import com.jayer.vhr.model.MailSendLog;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSendLogMapper {
    Integer insert(MailSendLog mailSendLog);
}
