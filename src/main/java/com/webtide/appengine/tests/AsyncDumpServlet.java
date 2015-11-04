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

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/async/dump/*"}, name="AsyncDump", asyncSupported=true, loadOnStartup=2)
public class AsyncDumpServlet extends HttpServlet
{
    @Override
    public void init() throws ServletException 
    {
        getServletContext().log("AsyncDumpServlet: init");
    }
  
    @Override
    protected void doGet(final HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException
    {
        getServletContext().log("AsyncDumpServlet: "+request.getRequestURI());
        final AsyncContext async = request.startAsync();

        async.start(new Runnable()
	{
	  @Override public void run()
	  {
            try
            {
                Thread.sleep(1000);
                getServletContext().log("AsyncDumpServlet: dispatch!");
                async.dispatch("/dump"+request.getRequestURI());
            }
            catch(Throwable x)
            {
                getServletContext().log("AsyncDumpServlet:", x);
            }
	  }
        });
    }
}
