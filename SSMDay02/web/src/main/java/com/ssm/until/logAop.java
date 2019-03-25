package com.ssm.until;

import com.ssm.damain.SysLog;
import com.ssm.dao.ILogDao;
import com.ssm.service.ILogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class logAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ILogService logService;

    @Around("execution(* com.ssm.web.*.*(..))")
    public Object around(ProceedingJoinPoint pjp){
        //获取方法参数
        Object[] args = pjp.getArgs();
        //获取方法名
        String mothodName = pjp.getSignature().toShortString();
        //获取时间
        Date date = new Date();
        //获取用户名
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        //获取ip地址
        String remoteAddr = request.getRemoteAddr();

        SysLog log = new SysLog();
        log.setVisitTime(date);
        log.setUsername(username);
        log.setIp(remoteAddr);
        log.setMethod(mothodName);
        System.out.println(log);

        try {
            //放行,去到控制器处理请求
            Object retrunValue = pjp.proceed(args);
            //方法执行后的增强功能
            logService.saveLog(log);
            return retrunValue;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
