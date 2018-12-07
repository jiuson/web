package com.yipinketang.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 默认的Servlet
 */
public class DefaultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>default</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>This is default page!</h1>");
        writer.println("</body>");
        writer.println("</html>");
//        writer.println("<h1>This is default page!</h1>");
    }
}
