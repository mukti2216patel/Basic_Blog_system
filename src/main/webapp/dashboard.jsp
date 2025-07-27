<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    // Fetch posts list from request scope
    java.util.List<org.bson.Document> posts = 
        (java.util.List<org.bson.Document>) request.getAttribute("posts");
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
    <!-- Welcome card -->
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <h1 class="card-title mb-4">
                        Welcome, <%= (username != null ? username : "User") %>!
                    </h1>
                    <p class="card-text fs-5 mb-4">
                        Your registered email is:
                        <strong><%= (email != null ? email : "Not available") %></strong>
                    </p>
                    <a href="addpost.jsp" class="btn btn-success me-2">➕ Add Post</a>
                    <a href="logout.jsp" class="btn btn-danger">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <div class="row justify-content-center mt-4">
        <div class="col-md-8">
            <h2 class="mb-3">📌 All Posts</h2>
            <%
                if (posts != null && !posts.isEmpty()) {
                    for (org.bson.Document post : posts) {
            %>
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="card-title"><%= post.getString("title") %></h5>
                                <p class="card-text"><%= post.getString("content") %></p>
                                <small class="text-muted">
                                    Author: <%= post.getString("author") != null ? post.getString("author") : "Unknown" %>
                                </small>
                            </div>
                        </div>
            <%
                    }
                } else {
            %>
                <p class="text-muted">No posts found. Start by adding one!</p>
            <%
                }
            %>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
