<!DOCTYPE html>

<html lang="en">
<head>
    <title>Simple Loan System</title>
</head>
    <body>
    <h2>Add Loan</h2>
        <form name="inventory" action="create" method="post">

            <table>
                <tr>
                    <td><label>Customer Name</label></td>
                    <td><label>:</label></td>
                    <td><label><input type="text" name="customerName"></label></td>
                </tr>
                <tr>
                    <td><label>Loan Amount</label></td>
                    <td><label>:</label></td>
                    <td><label><input type="text" name="loanAmount"></label><br></td>
                </tr>
                <tr>
                    <td><label>Interest Percentage</label></td>
                    <td><label>:</label></td>
                    <td><label><input type="text" name="interestPercentage"></label><br></td>
                </tr>
                 <tr>
                     <td><label>Mortage Type</label></td>
                     <td><label>:</label></td>
                      <td><label><input type="text" name="mortageType"></label><br></td>
                      </tr>
                <tr>
                    <td><input type="submit" name="Add"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
