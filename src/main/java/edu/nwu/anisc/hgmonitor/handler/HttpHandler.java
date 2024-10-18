package edu.nwu.anisc.hgmonitor.handler;

import cn.hutool.core.util.CharsetUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nwu.anisc.hgmonitor.exception.ApplicationException;
import edu.nwu.anisc.hgmonitor.response.ServerResponseEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 10:23
 * @since JDK 17
 */
@Component
public class HttpHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    @Autowired
    private ObjectMapper objectMapper;


    public <T> void printServerResponseToWeb(ServerResponseEntity<T> serverResponseEntity) {
        if (serverResponseEntity == null) {
            logger.info("print obj is null");
            return;
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (requestAttributes == null) {
            logger.error("requestAttributes is null, can not print to web");
            return;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            logger.error("httpServletResponse is null, can not print to web");
            return;
        }
        logger.error("response error: " + serverResponseEntity.getMsg());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(objectMapper.writeValueAsString(serverResponseEntity));
        } catch (IOException e) {
            throw new ApplicationException("io 异常", e);
        }
    }
}
