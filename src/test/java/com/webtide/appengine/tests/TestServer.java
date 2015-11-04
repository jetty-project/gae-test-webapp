package com.webtide.appengine.tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class TestServer 
{
  public static void main(String... arg) throws Exception
  {
    Server server = new Server(8080);
    
    // Enable parsing of jndi-related parts of web.xml and jetty-env.xml
    /*
    Configuration.ClassList classlist = Configuration.ClassList
            .setServerDefault(server);
    classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
            "org.eclipse.jetty.plus.webapp.EnvConfiguration",
            "org.eclipse.jetty.plus.webapp.PlusConfiguration");
    classlist.addBefore(
            "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
            "org.eclipse.jetty.annotations.AnnotationConfiguration");
    */

    WebAppContext context = new WebAppContext();
    
    context.setContextPath("/");
    context.setResourceBase("target/gae-test-webapp-1.0-SNAPSHOT");
    context.setParentLoaderPriority(true);
    
    server.setHandler(context);
    
    server.start();
    server.join();
  }
}
