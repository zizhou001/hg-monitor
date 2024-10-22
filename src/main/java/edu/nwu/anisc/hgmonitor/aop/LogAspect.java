package edu.nwu.anisc.hgmonitor.aop;

import edu.nwu.anisc.hgmonitor.annotation.OperLog;
import edu.nwu.anisc.hgmonitor.bo.UserInfoBO;
import edu.nwu.anisc.hgmonitor.entity.Log;
import edu.nwu.anisc.hgmonitor.mapper.LogMapper;
import edu.nwu.anisc.hgmonitor.response.ServerResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 15:18
 * @since JDK 17
 */
@Aspect
@Component
public class LogAspect {

    @Resource
    LogMapper logMapper;

    private UserInfoBO currentUser;

    /**
     * 设置日志切入点，在标注了注解 @OperLog 的方法上切入
     */
    @Pointcut("@annotation(edu.nwu.anisc.hgmonitor.annotation.OperLog)")
    public void logPointCut(){}

    /**
     * 设置操作异常的切入点记录异常，扫描controller及其子包下的所有异常
     */
    @Pointcut("execution(public * edu.nwu.anisc.hgmonitor.controller..*.*(..))")
    public void exceptionLogPointCut(){}


    /**
     * 拦截用户操作日志，连接点正常执行后记录操作
     * @param joinPoint  切入点
     * @param responseEntity  返回结果
     */
    @AfterReturning(value = "logPointCut()", returning = "responseEntity")
    public void saveLog(JoinPoint joinPoint, Object responseEntity){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        MethodSignature signature =  (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        OperLog opLog = method.getAnnotation(OperLog.class);
        ServerResponseEntity r = (ServerResponseEntity) responseEntity;
        Log log = new Log();
        String uuid = UUID.randomUUID().toString();
        log.setLogId(uuid);
        log.setLogOperator("unknown");
        log.setLogIp("unknown");
        log.setLogResult("unknown");
        log.setLogTime(new Date());
        log.setLogDetail(r.getMsg());

        try {
            if(r.getCode().equals("0") || r.getCode().startsWith("20")){
                log.setLogResult("成功");
                if(methodName.equals("login") || methodName.equals("register")){
                    currentUser = (UserInfoBO) r.getData();
                }
            }else{
                log.setLogResult("失败");
            }
            if(currentUser != null){
                log.setLogOperator(currentUser.getUsername());
                log.setLogIp(getIpAddr(request));
            }

            if (opLog != null) {
                String scope = opLog.scope();
                String content = opLog.content();
                String level = opLog.level();
                log.setLogScope(scope);
                log.setLogContent(content);
                log.setLogLevel(Integer.parseInt(level));
            }
            int inserted = logMapper.insert(log);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取请求的ip地址
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
