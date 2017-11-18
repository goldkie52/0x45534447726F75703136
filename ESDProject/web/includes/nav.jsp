<%-- 
    Document   : Standard navigation bar
    Author     : James Broadberry 14007903
--%>

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
                <a class="nav-link" href="/Signup">
                    <i class="fa fa-user-plus" aria-hidden="true"></i>
                    <span>Sign up</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/Login">
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