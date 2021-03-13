package home.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Component
public class StatsInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(StatsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("SiteInterceptor preHandle");
        logger.info("Client User-Agent: " + request.getHeader("User-Agent"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
      //if (response.getContentType().equals("text/html") && modelAndView!=null) {
       try{
         String path = request.getContextPath();
         int len = path.indexOf(File.separator, 10);
         len = (len==-1?path.length():len);
         String basePath =  path.substring(0,len);
         logger.info("==============POST HANDLE basepath:" + basePath);
         System.out.println("==============POST HANDLE basepath:" + basePath);
         modelAndView.addObject("basePath", basePath);
      } catch (Exception e){}
        //super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("SiteInterceptor afterCompletion");
    }
}
