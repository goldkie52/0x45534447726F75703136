<%-- 
    Document   : Main home page
    Author     : James Broadberry 14007903
    Author     : Kieran Harris 14010534
    Author     : Matthew Carpenter 14012396
--%>

<%@page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - Home</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <%
                // Check if the query string has set signedUp to true, and if it has, display an the username and password
                if (request.getParameter("signedUp") != null && request.getParameter("signedUp").equals("true")) {
                    if ((request.getSession().getAttribute("loggedInUser") != null)) {
                        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            %>
            
                <div class="alert alert-success alert-dismissible fade show m-0 xyz-alert-bottom-right" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                    <strong>Account created</strong><br/>Username: <%= loggedInUser.getId() %><br/>Password: <%= loggedInUser.getPassword() %>
                </div>

            <%
                }
            }
            %>
            <h1>Welcome to XYZ Drivers Association</h1>
            <p>
                XYZ Drivers Association decides to set up a solidarity fund to 
                subsidise members for minor accidents so that the members can 
                avoid making claims to Insurance companies. The association 
                provides a certain amount of subsidy once a claim is made, and 
                sums up all claims made to the end of each year.
            </p>
            <p>
                The members are expected to pay annual membership fee and the 
                allocated portion of total annual charge from lumpsum of claims.
                The charges are allocated at the end of each year.
            </p>
            <p>
                Members will be able to make claims after 6 months of their 
                membership, be allowed to make maximum 2 claims per year, and 
                will not be supported if  these circumstances are not met. 
            </p>
        </div>        
    </body>
</html>