<!DOCTYPE html>

<html lang="en">
<head>
    <title>Simple Inventory System</title>
</head>
<body>
<h2>Update Stock Product Inventory</h2>
<form name="inventory" action="/updateStock" method="post">
    <table>
        <tr>
            <td><label>Customer Name</label></td>
            <td><label>:</label></td>
            <td><label><input type="text" name="productName" value="${loan.customerName}" disabled="true"></label></td>
        </tr>
        <tr>
            <td><label>Stock</label></td>
            <td><label>:</label></td>
            <td><label><input type="text" name="stock" value="${loan.loanAmount}"></label><br></td>
        </tr>
        <tr>
            <td><label>Price</label></td>
            <td><label>:</label></td>
            <td><label><input type="text" name="price" value="${loan.interestPercentage}" disabled="true"></label><br></td>
            <td><input type="hidden" name="id" value="${loan.id}"></td>
        </tr>
        <tr>
            <td><input type="submit" name="update"></td>
        </tr>
    </table>
</form>
</body>
</html>
