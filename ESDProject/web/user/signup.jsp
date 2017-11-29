<%-- 
    Document   : Sign up page
    Author     : James Broadberry 14007903
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - Sign up</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <form class="form-horizontal mx-auto xyz-form-width" role="form" method="POST" action="/signup.do">
                <div class="row">
                    <div>
                        <h2>Sign up</h2>
                        <hr>
                    </div>
                </div>
                <div class="row" id="alerts">
                    <%
                        // Check if the query string has set invalid to true, and if it has, display an error message
                        if (request.getParameter("user") != null && request.getParameter("user").equals("invalid")) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        Someone with your name is already registered.
                    </div>
                    <%
                        }
                    %>

                    <%
                        // Check if the query string has set invalid to true, and if it has, display an error message
                        if (request.getParameter("dob") != null && request.getParameter("dob").equals("invalid")) {
                    %>

                    <div class="alert alert-danger" role="alert">
                        Please enter a valid date.
                    </div>

                    <%
                        }
                    %>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">#1</div>
                            <input type="text" name="firstname" class="form-control" placeholder="First name" required="" autofocus="" autocomplete="off">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">#2</div>
                            <input type="text" name="lastname" class="form-control" placeholder="Last name" required="" autofocus="" autocomplete="off">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                            <input type="text" name="dob" class="form-control" id="dob" placeholder="D.O.B. (dd-mm-yyyy)" required="" autocomplete="off" pattern="[0-9]{2}-[0-9]{2}-[0-9]{4}" title="DD-MM-YYYY">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon"><i class="fa fa-map-marker"></i></div>
                            <input type="text" name="postcode" class="form-control" id="postcode" placeholder="Postcode" required="" autocomplete="off">
                            <button type="button" id="postcode-lookup" class="btn btn-primary" formnovalidate><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
                <div class="row initial-hidden" id="address-lookup">
                    <div class="form-group">
                        <select name="address" required="" size="6" class="width-100 overflow-scroll" id="available-addresses">

                        </select>
                    </div>
                </div>
                <div class="row">
                    <button type="submit" class="btn btn-success width-100 form-check">Sign up</button>
                </div>
                <div class="row">
                    <div class="mx-auto">
                        <span class="p-2">Already a member? <a href="/login.do">Log in here</a></span>
                    </div>
                </div>
            </form>
        </div>        
    </body>
</html>