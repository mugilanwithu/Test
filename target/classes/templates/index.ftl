<!DOCTYPE html>

<html lang="en">
<head>
    <title>Simple Inventory System</title>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.5/css/bootstrap.min.css'>
</head>
<body>
<h2>Data Product List</h2>
<table>
    <thead>
    <tr>
        <td>Customer Name</td>
        <td>Loan Amount</td>
        <td>Interest percentage</td>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>
    <#list loans as loan>
       <tr>
               <td>${loan.customerName}</td>
               <td>${loan.loanAmount}</td>
               <td>${loan.interestPercentage}</td>
               <td><a href="editStock/${loan.id}"><button type="button">update stock</button></a></td>
               <td><a href="editPrice/${loan.id}"><button type="button">update price</button></a></td>
               <td><a href="delete/${loan.id}"><button type="button">delete</button></a></td>
           </tr>
       </#list>
    <tr>
        <td colspan="4" align="right"><a href="create"><button type="button">add</button></a></td>
    </tr>
    </tbody>
</table>
</body>
</html>
