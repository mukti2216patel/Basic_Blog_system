<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String username = (String) session.getAttribute("username");
	String email = (String) session.getAttribute("email");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Dashboard</title>
 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h1 class="card-title mb-4">Welcome, <%= (username != null ? username : "User") %>!</h1>
                        <p class="card-text fs-5 mb-4">Your registered email is: <strong><%= (email != null ? email : "Not available") %></strong></p>
                        <!-- Logout Button -->
                        <a href="logout.jsp" class="btn btn-danger">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper (optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
