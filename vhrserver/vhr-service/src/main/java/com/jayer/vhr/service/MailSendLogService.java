package com.jayer.vhr.service;

import com.jayer.vhr.mapper.MailSendLogMapper;
import com.jayer.vhr.model.MailSendLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSendLogService {
    @Autowired
    MailSendLogMapper mailSendLogMapper;
    public Integer insert(MailSendLog mailSendLog){
        return mailSendLogMapper.insert(mailSendLog);
    }
}
