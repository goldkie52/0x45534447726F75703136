<%-- 
    Document   : Admin view all claims page
    Author     : James Broadberry 14007903
    Author     : Rachel Bailey 13006455
--%>

<%@page import="model.Claim"%>
<!DOCTYPE html>
<html>
     <head>
        <title>XYZ - All Claims</title>
        <jsp:include page="/includes/references.jsp" />
     </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />
        
        <div class="container">
            <h1 class="mb-3">All Claims</h1>
            <table class="table table-bordered table-hover">
                <thead class="thead-inverse">
                    <tr>
                        <th>Member</th>
                        <th>Date</th>
                        <th>Rationale</th>
                        <th>Status</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <% Claim[] claims = (Claim[])request.getAttribute("claims");
                        for (Claim claim : claims) {
                    %>
                    <tr>
                        <td><% out.print(claim.getMemId()); %></td>
                        <td><% out.print(claim.getDate().toString());%> </td>
                        <td><% out.print(claim.getRationale()); %></td>
                        <td><% out.print(claim.getStatus().toString()); %></td>
                        <td>£<% out.print(String.format("%.2f", claim.getAmount())); %></td>
                        
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>        
    </body>
</html>