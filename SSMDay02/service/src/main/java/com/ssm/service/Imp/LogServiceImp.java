package com.ssm.service.Imp;

import com.ssm.damain.SysLog;
import com.ssm.dao.ILogDao;
import com.ssm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImp implements ILogService {
    @Autowired
    private ILogDao iLogDao;
    @Override
    public void saveLog(SysLog sysLog) {
        iLogDao.saveLog(sysLog);
    }
}
