<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.bson.Document" %>
<%@ page import="org.bson.types.ObjectId" %>
<%

	Document post =(Document) request.getAttribute("post");
	String id = post.getObjectId("_id").toHexString();
	String title = post.getString("title");
	String content = post.getString("content");

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-body">
                <h2 class="mb-4">✏️ Update Post</h2>
                 <%
                            String errorMessage = (String) request.getAttribute("error");
                            if (errorMessage != null) {
                        %>
                            <div class="alert alert-danger">
                                <%= errorMessage %>
                            </div>
                        <%
                            }
                        %>
                <form action="<%= request.getContextPath()%>/updatepost" method="post">
                
                    <input type="hidden" name="id" value="<%= id %>" />
                    
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="title" value="<%= title %>" required />
                    </div>

                    <div class="mb-3">
                        <label for="content" class="form-label">Content</label>
                        <textarea class="form-control" id="content" name="content" rows="5" required><%= content %></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">💾 Save Changes</button>
                    <a href="dashboard" class="btn btn-secondary">← Back</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
