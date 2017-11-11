<%-- 
    Document   : Log in page
    Author     : James Broadberry 14007903
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - Log in</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <form class="form-horizontal" role="form" method="POST" action="/login.do">
                <div class="row">
                    <div class="col-md-4 mx-auto">
                        <h2>Login</h2>
                        <hr>
                    </div>
                </div>
                <%
                    // Check if the query string has set invalid to true, and if it has, display an error message
                    if (request.getParameter("invalid") != null && request.getParameter("invalid").equals("true")) {
                %>
                <div class="row">
                    <div class="col-md-4 mx-auto">
                        <div class="alert alert-danger" role="alert">
                            Incorrect username or password.
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
                <div class="row">
                    <div class="col-md-4 mx-auto">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-user-circle"></i></div>
                                <input type="text" name="username" class="form-control" id="username" placeholder="Username" required="" autofocus="" autocomplete="off">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 mx-auto">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-asterisk"></i></div>
                                <input type="password" name="password" class="form-control" id="password" placeholder="Password" required="" autocomplete="off">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 mx-auto text-center">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="remember" type="checkbox">
                                <span>Remember me</span>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 mx-auto">
                        <button type="submit" class="btn btn-success width-100 form-check">Login</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 mx-auto text-center">
                        <span class="p-2">Not a member? <a href="signup.jsp">Sign up here</a></span>
                    </div>
                </div>
            </form>
        </div>        
    </body>
</html>