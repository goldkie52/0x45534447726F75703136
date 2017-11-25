<%-- 
    Document   : Member view payments page
    Author     : James Broadberry 14007903
    Author     : Matthew Carpenter 14012396
--%>

<%@page import="model.Payment"%>
<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - View Payments</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <div class="row mb-3">
                <div class="col">
                    <h1>Your Payments</h1>
                </div>
                <div class="col ml-auto">
                    <h5 class="text-right">Balance: £<%= String.format("%.2f", (Double) request.getAttribute("balance"))%></h5>    
                </div>

            </div>

            <table class="table table-bordered table-hover">
                <thead class="thead-inverse">
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Type</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <% Payment[] payments = (Payment[]) request.getAttribute("payments");
                        for (Payment payment : payments) {
                    %>
                    <tr>
                        <td><%= payment.getDate().toString()%></td>
                        <td><%= payment.getTime().toString()%></td>
                        <td><%= payment.getTypeOfPayment()%></td>
                        <td>£<%= String.format("%.2f", payment.getAmount())%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>       
    </body>
</html>