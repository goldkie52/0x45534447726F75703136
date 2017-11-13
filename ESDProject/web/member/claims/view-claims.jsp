<%-- 
    Document   : Member view claims page
    Author     : James Broadberry 14007903
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>XYZ - View Claims</title>
        <jsp:include page="/includes/references.jsp" />
    </head>

    <body>
        <jsp:include page="/includes/nav.jsp" />

        <div class="container">
            <h1 class="mb-3">Your Claims</h1>
            <table class="table table-bordered table-hover">
                <thead class="thead-inverse">
                    <tr>
                        <th>Date</th>
                        <th>Rationale</th>
                        <th>Status</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>01-01-2001</td>
                        <td>Replaced indicator fluid</td>
                        <td>APPROVED</td>
                        <td>£19.99</td>
                    </tr>
                    <tr>
                        <td>02-01-2001</td>
                        <td>More indicator fluid</td>
                        <td>REJECTED</td>
                        <td>£19.99</td>
                    </tr>
                </tbody>
            </table>
        </div>        
    </body>
</html>