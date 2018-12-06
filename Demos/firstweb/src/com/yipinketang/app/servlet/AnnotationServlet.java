package com.yipinketang.app.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 通过注解：@WebServlet注册一个servlet
 */

@WebServlet(urlPatterns = "/anno", initParams = {@WebInitParam(name = "initParam", value = "Hello world")})
public class AnnotationServlet extends HttpServlet {

    private String initParam;

    @Override
    public void init(ServletConfig config) throws ServletException {
        initParam = config.getInitParameter("initParam");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>通过注解@WebServlet注册一个servlet获取到的初始化参数：[initparam="+ initParam + "]</h1>");
    }
}
