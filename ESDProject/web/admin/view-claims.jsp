<%-- 
    Document   : Admin view all claims page
    Author     : James Broadberry 14007903
    Author     : Rachel Bailey 13006455
    Author     : Matthew Carpenter 14012396
--%>

<%@page import="model.ClaimStatus"%>
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
            <div class="row w-100" id="alerts">
                <%
                    // Check if the query string has set success to false, and if it has, display an error message
                    String success = request.getParameter("success");
                    if (success != null) {
                        if (success.equals("false")) {
                %>
                <div class="alert alert-danger w-100" role="alert">
                    Error processing claim.
                </div>
                <%
                } else if (success.equals("true")) {
                %>
                <div class="alert alert-success w-100" role="alert">
                    Claim processed successfully.
                </div>
                <%     }
                    }
                %>
            </div>
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
                    <% Claim[] claims = (Claim[]) request.getAttribute("claims");
                        for (Claim claim : claims) {
                    %>
                    <tr>
                        <td><%= claim.getMemId()%></td>
                        <td><%= claim.getDate().toString()%> </td>
                        <td><%= claim.getRationale()%></td>
                        <td><% ClaimStatus status = claim.getStatus();
                            if (status == ClaimStatus.PENDING) {
                            %>
                            <form role="form" method="POST" action="/admin/process-claim.do">
                                <input type="hidden" name="claim" class="form-control" id="claim" value="<%= claim.getId()%>">
                                <button type="submit" class="btn btn-success form-check inline-button" name="approve">Approve</button>
                                <button type="submit" class="btn btn-danger form-check inline-button" name="reject">Reject</button>
                            </form>
                            <% } else {
                                    out.print(claim.getStatus().toString());
                                }%></td>
                        <td>£<%= String.format("%.2f", claim.getAmount())%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>        
    </body>
</html>