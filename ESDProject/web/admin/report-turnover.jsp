<%-- 
    Document   : Admin annual turnover page
    Author     : James Broadberry 14007903
    Author     : Rachel Bailey 13006455
    Author     : Matthew Carpenter 14012396
--%>

<%@page import="model.Member"%>
<%@page import="model.Claim"%>
<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - Annual Turnover</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <div class="row mb-3">
                <h1>Annual Turnover</h1>
            </div>

            <%
                // Check if the query string has set invalid to true, and if it has, display an error message
                if (request.getParameter("success") != null && request.getParameter("success").equals("false")) {
            %>
            <div class="alert alert-danger" role="alert">
                Error processing member charges.
            </div>
            <%
            } else if (request.getParameter("success") != null && request.getParameter("success").equals("true")) {
            %>

            <div class="alert alert-success" role="alert">
                Successfully charged members.
            </div>

            <%
                }
            %>

            <div class="row mb-3">
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
                        <% Claim[] claims = (Claim[]) request.getAttribute("turnoverClaims");
                            Member[] members = (Member[]) request.getAttribute("turnoverMembers");
                            for (Claim claim : claims) {
                        %>
                        <tr>
                            <td><%= claim.getMemId()%></td>
                            <td><%= claim.getDate().toString()%> </td>
                            <td><%= claim.getRationale()%></td>
                            <td><%= claim.getStatus().toString()%></td>
                            <td>£<%= String.format("%.2f", claim.getAmount())%></td>

                        </tr>
                        <%  }%>
                    </tbody>
                </table>

            </div>

            <div class="row">       
                <h5>Annual Turnover: £<%= String.format("%.2f", (Double) request.getAttribute("totalClaimValue"))%></h5>  
            </div>
            <div class="row">
                <form  class="form-horizontal mx-auto xyz-form-width" role="form" method="POST" action="/admin/charge-members.do">
                    <input type="hidden" name="memberAmount" class="form-control" id="memberAmount" value="<%= (Double) request.getAttribute("memberAmount")%>"> 
                    <button type="submit" class="btn btn-success form-check"> Charge Members</button>
                </form>
                <h5 class="xyz-line-height-40 ml-1">Annual Member Charges: £<%= String.format("%.2f", (Double) request.getAttribute("memberAmount"))%></h5>
            </div>

        </div>       
    </body>
</html>