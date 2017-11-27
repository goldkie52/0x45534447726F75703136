<%-- 
    Document   : Admin view all members page
    Author     : James Broadberry 14007903
    Author     : Rachel Bailey 13006455
    Author     : Matthew Carpenter 14012396
--%>

<%@page import="model.MemberStatus"%>
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

            <div class="row w-100" id="alerts">
                <%
                    // Check if the query string has set success to false, and if it has, display an error message
                    String success = request.getParameter("success");
                    if (success != null) {
                        if (success.equals("false")) {
                %>
                <div class="alert alert-danger w-100" role="alert">
                    Error processing member.
                </div>
                <%
                } else if (success.equals("true")) {
                %>
                <div class="alert alert-success w-100" role="alert">
                    Member processed successfully.
                </div>
                <%     }
                    }
                %>
            </div>

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

                        <td>
                            <span><%= member.getStatus().toString()%></span>
                            <%
                                MemberStatus status = member.getStatus();
                                if (status == MemberStatus.APPLIED) {
                            %>
                            <form role="form" method="POST" action="/admin/process-member.do">
                                <input type="hidden" name="member" class="form-control" id="member" value="<%= member.getId()%>">
                                <button type="submit" class="btn btn-success form-check inline-button" name="approve">Approve</button>
                            </form>
                            <% } else if (status == MemberStatus.SUSPENDED) {%>
                            <form role="form" method="POST" action="/admin/process-member.do">
                                <input type="hidden" name="member" class="form-control" id="member" value="<%= member.getId()%>">
                                <button type="submit" class="btn btn-warning form-check inline-button" name="approve">Approve</button>
                            </form>
                            <% } else if (status == MemberStatus.APPROVED) {%>
                            <form role="form" method="POST" action="/admin/process-member.do">
                                <input type="hidden" name="member" class="form-control" id="member" value="<%= member.getId()%>">
                                <button type="submit" class="btn btn-danger form-check inline-button" name="suspend">Suspend</button>
                            </form>
                            <% }%>
                        </td>

                        <td><%= String.format("%.2f", member.getBalance())%> </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>        
    </body>
</html>