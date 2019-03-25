package com.ssm.dao;

import com.ssm.damain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ILogDao {
    @Insert("INSERT INTO sys_log VALUES (NULL ,#{visitTime},#{username},#{ip},#{method})")
    void saveLog(SysLog sysLog);
}
