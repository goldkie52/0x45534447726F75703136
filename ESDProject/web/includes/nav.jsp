<%-- 
    Document   : Standard navigation bar
    Author     : James Broadberry 14007903
--%>

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-5">
    <%-- Nav bar branding --%>
    <div class="navbar-brand">
        <i class="fa fa-car p-2" aria-hidden="true"></i>    
        <span>XYZ Drivers Association</span>
    </div>

    <div class="navbar-collapse" >
        <%-- Left side nav bar --%>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <%
                // Create dynamic links
                if (request.getSession().getAttribute("loggedInUser") != null) {
                    model.UserStatus loggedInStatus = ((model.User) request.getSession().getAttribute("loggedInUser")).getStatus();
                    Map<String, String> links = new LinkedHashMap<String, String>();

                    // Generate links based on status
                    switch (loggedInStatus) {
                        case ADMIN:
                            links.put("View Payments", "/admin/view-payments.do");
                            links.put("View Members", "/admin/view-members.do");
                            links.put("View Claims", "/admin/view-claims.do");
                            links.put("Annual Turnover", "/admin/annual-turnover.do");
                            break;
                        case APPLIED:
                        case APPROVED:
                        case SUSPENDED:
                            links.put("View Payments", "/member/view-payments.do");
                            links.put("Make Payment", "/member/make-payment.do");
                            links.put("View Claims", "/member/view-claims.do");
                            break;
                    }

                    // Write all links out
                    String linkFormat = "<li class='nav-item'><a class='nav-link' href='%HREF%'>%TEXT%</a></li>";
                    for (Entry link : links.entrySet()) {
                        out.println(linkFormat.replace("%TEXT%", link.getKey().toString()).replace("%HREF%", link.getValue().toString()));
                    }
                }
            %>
        </ul>
        <%-- Right side nav bar --%>
        <%
            // If there is a user logged in currently, output the following
            if (request.getSession().getAttribute("loggedInUser") != null) {
        %>
        <ul class="navbar-nav ml-auto">
            <li class="navbar-item">
                <span class="navbar-text p-2">
                    <%
                        out.println("Signed in as " + ((model.User) request.getSession().getAttribute("loggedInUser")).getId());
                    %>
                </span>
            </li>
            <li class="navbar-item">
                <a class="nav-link" href="/logout.do">
                    <i class="fa fa-sign-out" aria-hidden="true"></i>
                    <span>Log out</span>
                </a>
            </li>
        </ul>
        <%
            // If there is no user logged in, output the following
        } else {
        %>
        <ul class="navbar-nav ml-auto">
            <li class="navbar-item">
                <a class="nav-link" href="/signup.do">
                    <i class="fa fa-user-plus" aria-hidden="true"></i>
                    <span>Sign up</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login.do">
                    <i class="fa fa-sign-in" aria-hidden="true"></i>
                    <span>Log in</span>
                </a>
            </li>
        </ul>
        <%
            }
        %>
    </div>
</nav>