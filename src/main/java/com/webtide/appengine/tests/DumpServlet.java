//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package com.webtide.appengine.tests;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
// @WebServlet(urlPatterns = {"/dump/*"}, name="Dump")
public class DumpServlet extends HttpServlet
{
    @Override
    public void init() throws ServletException 
    {
        getServletContext().log("DumpServlet init");
    }

    @Override
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException,
                                                        IOException
    {
        getServletContext().log("DumpServlet: "+request.getRequestURI());
        
        if (request.getParameter("ex")!=null)
          throw new ServletException(request.getParameter("ex"));
        
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("<h1>DumpServlet</h1>");
        out.println("<pre>");
        
        out.println("dispatcherType=" + request.getDispatcherType());
        out.println("method=" + request.getMethod());
        out.println("scheme=" + request.getScheme());
        out.println("serverName=" + request.getServerName());
        out.println("serverPort=" + request.getServerPort());
        out.println("requestURI=" + request.getRequestURI());
        out.println("contextPath=" + request.getContextPath());
        out.println("servletPath=" + request.getServletPath());
        out.println("pathInfo=" + request.getPathInfo());
        out.println("session=" + request.getSession(true).getId());

        out.println("</pre>");
    }
}
