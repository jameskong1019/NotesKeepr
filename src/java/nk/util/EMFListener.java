/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import nk.dataccess.DBUtil;

/**
 * Web application lifecycle listener.
 *
 * @author james
 */
public class EMFListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBUtil.closeEmFactory();
    }
}
