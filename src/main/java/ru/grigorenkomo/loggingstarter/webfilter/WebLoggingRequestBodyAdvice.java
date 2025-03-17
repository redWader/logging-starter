package ru.grigorenkomo.loggingstarter.webfilter;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import ru.grigorenkomo.loggingstarter.aspect.LogExecutionAspect;
import ru.grigorenkomo.loggingstarter.utils.LoggingUtils;

import java.lang.reflect.Type;




@ControllerAdvice
public class WebLoggingRequestBodyAdvice extends RequestBodyAdviceAdapter {
    private static final Logger log = LoggerFactory.getLogger(WebLoggingRequestBodyAdvice .class);

    @Autowired
    private HttpServletRequest request;

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        String method = request.getMethod();
        String requestURI = request.getRequestURI() + LoggingUtils.formatQueryString(request);

        log.info("Тело запроса: {}", body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

}
