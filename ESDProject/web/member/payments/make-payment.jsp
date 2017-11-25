<%-- 
    Document   : Member make payment page
    Author     : James Broadberry 14007903
    Author     : Kieran Harris 14010534
    Author     : Matthew Carpenter 14012396
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
            <form class="form-horizontal mx-auto xyz-form-width" role="form" method="POST" action="/member/make-payment.do">
                <div class="row">
                    <div>
                        <h2>Make a payment</h2>
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
                        String success = request.getParameter("success");
                        if (success != null) {
                            if (success.equals("false")) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        Error adding payment.
                    </div>
                    <%
                    } else if (success.equals("true")) {
                    %>
                    <div class="alert alert-success" role="alert">
                        Payment successfully added.
                    </div>
                    <%
                            }
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
                </div>
                <div class="row">
                    <button type="submit" class="btn btn-success width-100 form-check">Make Payment</button>
                </div>
            </form>
        </div>        
    </body>
</html>