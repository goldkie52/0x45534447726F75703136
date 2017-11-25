package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.UserStatus;

/**
 * Filter for member pages - if not logged in or not member will redirect to home.
 * @author Rachel Bailey 13006455
 * @author Matthew Carpenter 14012396
 */
public class MemberFilter implements Filter {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private static final boolean DEBUG = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public MemberFilter() {
    } 
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     *
     * @param request The servlet request processing
     * @param response The servlet response creating
     * @param chain The filter chain processing
     *
     * @exception IOException if input/output error occurs
     * @exception ServletException if servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (DEBUG) {
            log("MemberFilter:doFilter()");
        }
        
        // Get the logged in user
        User loggedInUser = (model.User) ((HttpServletRequest)request).getSession().getAttribute("loggedInUser");
        
        // If user is not logged in or not a member, redirect to homepage
        if (loggedInUser == null || loggedInUser.getStatus() == UserStatus.ADMIN) {
            ((HttpServletResponse)response).sendRedirect("/");
            return;
        }
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException t) {
            // If an exception is thrown in the filter chain,
            // we want to execute our after processing, and
            // rethrow the problem after that.
            problem = t;
        }

        // If there was a problem, we want to rethrow if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     * @return the filter configuration object
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * initialization method for this filter
     * @param filterConfig the filter configuration object
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {                
                log("MemberFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MemberFilter()");
        }
        StringBuilder sb = new StringBuilder("MemberFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                try (PrintStream ps = new PrintStream(response.getOutputStream());
                        PrintWriter pw = new PrintWriter(ps);) {
                    pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
                    
                    // PENDING! Localize this for next official release
                    pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                    pw.print(stackTrace);
                    pw.print("</pre></body>\n</html>"); //NOI18N
                    pw.close();
                }
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        } else {
            try {
                try (PrintStream ps = new PrintStream(response.getOutputStream())) {
                    t.printStackTrace(ps);
                }
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
    // </editor-fold>
    
}
