//package net.downson.znhy.filter;
//
//import net.downson.znhy.bean.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(filterName = "UserFilter",urlPatterns = "/SearchServlet")
//
//public class UserFilter implements Filter {
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//       //获取session 校验用户信息
//       req.setCharacterEncoding("utf-8");
//       resp.setContentType("text/html;charset=utf-8");
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse)resp;
//        HttpSession session = request.getSession();
//        User user =(User) session.getAttribute("user");
//        if(user==null){
//            response.sendRedirect(request.getContextPath()+"index.html");
//            return ;
//        }
//        //用户信息不为空
//
//        chain.doFilter(req, resp);
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//
//    }
//
//}
