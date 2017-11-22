<%-- 
    Document   : Member submit a claim page
    Author     : James Broadberry 14007903
    Author     : Charlotte Harris 14008503
--%>

<!DOCTYPE html>
<html>
     <head>
        <title>XYZ - Make Payment</title>
        <jsp:include page="/includes/references.jsp" />
     </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />
        
        <div class="container">
            <form class="form-horizontal mx-auto xyz-form-width" role="form" method="POST" action="/member/make-claim.do">
                <div class="row">
                    <div>
                        <h2>Make a claim</h2>
                        <hr>
                    </div>
                </div>
                <div class="row" id="alerts">
                <%
                    // Check if the query string has set invalid to true, and if it has, display an error message
                    if (request.getParameter("amount") != null && request.getParameter("amount").equals("invalid")) {
                %>
                        <div class="alert alert-danger" role="alert">
                            Amount invalid.
                        </div>
                <%
                    }
                    else if (request.getParameter("rationale") != null && request.getParameter("rationale").equals("invalid")) {
                %>
                        <div class="alert alert-danger" role="alert">
                            Rationale invalid.
                        </div>
                <%
                    }
                    if(request.getParameter("databaseReturn") != null && request.getParameter("databaseReturn").equals("invalid")){
                %>
                        <div class="alert alert-danger" role="alert">
                            Database returned invalid claim.
                        </div>
                <%
                    }
                    else if(request.getParameter("databaseReturn") != null && request.getParameter("databaseReturn").equals("valid")){
                %>
                        <div class="alert alert-success" role="alert">
                            Claim successfully added.
                        </div>
                <%
                    }
                %>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-money" aria-hidden="true"></i></div>
                                <input type="text" name="amount" class="form-control" placeholder="Amount" required="" autofocus="" autocomplete="off" pattern="^[0-9]*(\.[0-9]{2}){0,1}$" title="Numeric amount (two decimal places)">
                        </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-car" aria-hidden="true"></i></div>
                                <input type="text" name="rationale" class="form-control" placeholder="Rationale" required="" autofocus="" autocomplete="off" title="Rationale">
                        </div>
                        </div>
                     </div>
                    <div class="row">
                        <button type="submit" class="btn btn-success width-100 form-check">Make Claim</button>
                    </div>
            </form>
        </div>        
    </body>
</html>