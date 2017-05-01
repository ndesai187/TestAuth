import Auth.UserAuthUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by nirav on 30/4/17.
 */
public class MyAppServletContextListener
        implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //UserAuthUtil.addStartupCache("req_token");
        //UserAuthUtil.addStartupCache("acc_token");

        System.out.println("ServletContextListener started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        UserAuthUtil.flushOutCache();

        System.out.println("ServletContextListener destroyed");
    }
}
