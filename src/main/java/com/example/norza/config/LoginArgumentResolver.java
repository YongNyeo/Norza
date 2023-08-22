package com.example.norza.config;

import com.example.norza.domain.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) { //파라미터 타입이 지원하는건지?
        boolean hasLogin = parameter.hasParameterAnnotation(Login.class); //로그인 어노테이션을 사용할예정이다.
        boolean assignableFrom = SessionUser.class.isAssignableFrom(parameter.getParameterType()); //SessionUser랑 (@login)아규먼트리졸버를 매치시킬때 t/f

        return hasLogin&&assignableFrom; //둘다 있을때.
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest)
                webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return session.getAttribute("sessionUser");
    }
}
