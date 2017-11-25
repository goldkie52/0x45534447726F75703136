<%-- 
    Document   : Admin view all payments page
    Author     : James Broadberry 14007903
    Author     : Matthew Carpenter 14012396
--%>

<%@page import="model.Payment"%>
<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - All Payments</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <h1 class="mb-3">All Payments</h1>
            <table class="table table-bordered table-hover">
                <thead class="thead-inverse">
                    <tr>
                        <th>Member</th>
                        <th>Type of Payment</th>
                        <th>Amount</th>
                        <th>Date/Time</th>
                    </tr>
                </thead>
                <tbody>
                    <% Payment[] payments = (Payment[]) request.getAttribute("payments");
                        for (Payment payment : payments) {
                    %>
                    <tr>
                        <td><%= payment.getMemId()%></td>
                        <td><%= payment.getTypeOfPayment()%></td>
                        <td>£<%= String.format("%.2f", payment.getAmount())%></td>
                        <td><%= payment.getDate().toString() + " " + payment.getTime().toString()%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>        
    </body>
</html>