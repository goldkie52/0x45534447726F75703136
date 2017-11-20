<%-- 
    Document   : Admin view all members page
    Author     : James Broadberry 14007903
    Author     : Rachel Bailey 13006455
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
                    <% Member[] members = (Member[])request.getAttribute("members");
                        for (Member member : members) {
                    %>
                    <tr>
                        <td><% out.print(member.getId()); %> </td>  
                        <td><% out.print(member.getName()); %> </td>
                        <td><% out.print(member.getAddress().replace(",", ",<br/>")); %> </td>
                        <td><% out.print(member.getDob().toString()); %> </td>
                        <td><% out.print(member.getDor().toString()); %> </td>
                        <td><% out.print(member.getStatus().toString()); %> </td>
                        <td><% out.print(String.format("%.2f", member.getBalance())); %> </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>        
    </body>
</html>