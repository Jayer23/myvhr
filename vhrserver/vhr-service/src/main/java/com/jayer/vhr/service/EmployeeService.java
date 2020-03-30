package com.jayer.vhr.service;

import com.jayer.vhr.mapper.EmployMapper;
import com.jayer.vhr.model.Employee;
import com.jayer.vhr.model.MailConstants;
import com.jayer.vhr.model.MailSendLog;
import com.jayer.vhr.model.RespPageBean;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployMapper employMapper;
    @Autowired
    MailSendLogService mailSendLogService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");


    public RespPageBean getEmpoyeeBypage(Integer page, Integer size, Employee employee, Date[] beginDateScope){
        if(page != null && size != null){
            page = (page - 1) *  size;
        }
        RespPageBean respPageBean = new RespPageBean();
        List<Employee> employees = employMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        respPageBean.setData(employees);
        Long total = employMapper.getTotal(employee, beginDateScope);
        respPageBean.setTotal(total);
        return respPageBean;
    }

    public Integer addEmp(Employee employee){
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        Double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract)))*12 + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));
        employee.setContractTerm(month/12);
        int result = employMapper.insertSelective(employee);
        if(result == 1){
            MailSendLog mailSendLog = new MailSendLog();
            Employee emp = employMapper.getEmployeeById(employee.getId());
            String msgId = UUID.randomUUID().toString();
            mailSendLog.setMsgId(msgId);
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setEmpId(emp.getId());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
            mailSendLogService.insert(mailSendLog);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,MailConstants.MAIL_ROUTING_KEY_NAME,emp,new CorrelationData(msgId));
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,MailConstants.MAIL_ROUTING_KEY_NAME,emp,new CorrelationData(msgId));
        }
        return result;
    }
    public Integer maxWorkID(){
        return employMapper.maxWorkID();
    }
    public Integer deleteEmployeeById(Integer id){
        return employMapper.deleteByPrimaryKey(id);
    }
    public Integer updateEmp(Employee employee){
        return employMapper.updateByPrimaryKeySelective(employee);
    }
}
