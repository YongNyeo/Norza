package com.example.norza.filter;

import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogFilter implements Filter {

    private static final String[] blackList = new String[]{"/mypage"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        String requestURI = ((HttpServletRequest) request).getRequestURI();
        try {

            if (isBlackList(requestURI)) { //인증이 필요한 페이지
                if (session == null || session.getAttribute("sessionUser") == null) {
                    httpServletResponse.sendRedirect("/");
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }

    }

    private boolean isBlackList(String requestURI) {
        return PatternMatchUtils.simpleMatch(blackList, requestURI);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}
