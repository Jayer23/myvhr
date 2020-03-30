package com.jayer.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayer.vhr.model.ResBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class VerificationFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if("POST".equals(req.getMethod()) && "/doLogin".equals(req.getServletPath())){
            String code = req.getParameter("code");
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            if (code == null || verify_code == null || "".equals(code) || !code.toLowerCase().equals(verify_code.toLowerCase())) {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(ResBean.error("验证码输入错误")));
                out.flush();
                out.close();
                return;
            } else {
                filterChain.doFilter(req,resp);
            }

        }else {
            filterChain.doFilter(req,resp);
        }
    }
}
