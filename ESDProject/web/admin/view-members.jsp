<%-- 
    Document   : Admin view all members page
    Author     : James Broadberry 14007903
    Author     : Rachel Bailey 13006455
    Author     : Matthew Carpenter 14012396
--%>

<%@page import="model.Member"%>
<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - All Members</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <h1 class="mb-3">All Members</h1>
            <table class="table table-bordered table-hover">
                <thead class="thead-inverse">
                    <tr>
                        <th>Member</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>DoB</th>
                        <th>DoR</th>
                        <th>Status</th>
                        <th>Balance</th>
                    </tr>
                </thead>
                <tbody>
                    <% Member[] members = (Member[]) request.getAttribute("members");
                        for (Member member : members) {
                    %>
                    <tr>
                        <td><%= member.getId()%> </td>  
                        <td><%= member.getName()%> </td>
                        <td><%= member.getAddress().replace(",", ",<br/>")%> </td>
                        <td><%= member.getDob().toString()%> </td>
                        <td><%= member.getDor().toString()%> </td>
                        <td><%= member.getStatus().toString()%> </td>
                        <td><%= String.format("%.2f", member.getBalance())%> </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>        
    </body>
</html>