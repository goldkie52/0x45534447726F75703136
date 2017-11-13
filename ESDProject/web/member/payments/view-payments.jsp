<%-- 
    Document   : Member view payments page
    Author     : James Broadberry 14007903
--%>

<!DOCTYPE html>
<html>
     <head>
        <title>XYZ - View Payments</title>
        <jsp:include page="/includes/references.jsp" />
     </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />
        
        <div class="container">
            <h1 class="mb-3">Your Payments</h1>
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
                    <tr>
                        <td>01-01-2001</td>
                        <td>10:52</td>
                        <td>FEE</td>
                        <td>£10</td>
                    </tr>
                    <tr>
                        <td>02-01-2001</td>
                        <td>11:21</td>
                        <td>FEE</td>
                        <td>£10</td>
                    </tr>
                </tbody>
            </table>
        </div>       
    </body>
</html>