package top.qoj.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import top.qoj.annotation.QOJAccess;
import top.qoj.annotation.QOJAccessEnum;
import top.qoj.utils.ServiceContextUtils;
import top.qoj.validator.AccessValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Autowired
    private AccessValidator accessValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            QOJAccess QOJAccess = ServiceContextUtils.getAnnotation(handlerMethod.getMethod(), handlerMethod.getBeanType(), QOJAccess.class);
            if (QOJAccess == null || QOJAccess.value().length == 0) {
                return true;
            }
            for (QOJAccessEnum value : QOJAccess.value()) {
                accessValidator.validateAccess(value);
            }
            return true;
        }else if (handler instanceof ResourceHttpRequestHandler){
            // 静态资源的请求不处理
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
