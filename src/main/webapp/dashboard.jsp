<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    java.util.List<org.bson.Document> posts = (java.util.List<org.bson.Document>) request.getAttribute("posts");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Dashboard</title>
    <!-- Tailwind CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black text-white min-h-screen">

<%@ include file="navbar.jsp" %>

<div class="container mx-auto px-4 py-10">
    <div class="max-w-3xl mx-auto">
        <div class="bg-gray-900 border border-gray-700 rounded-xl shadow-md p-8 text-center">
            <h1 class="text-3xl font-bold mb-4">
                Welcome, <%= (username != null ? username : "User") %>!
            </h1>
            <p class="text-lg mb-6">
                Your registered email is:
                <span class="font-semibold text-purple-400"><%= (email != null ? email : "Not available") %></span>
            </p>
            <div class="flex justify-center gap-4">
                <a href="<%= request.getContextPath() %>/addpost" class="bg-purple-600 hover:bg-purple-700 text-white font-medium px-5 py-2 rounded-md shadow">
                    ➕ Add Post
                </a>
                <a href="<%= request.getContextPath() %>/logout" class="bg-red-600 hover:bg-red-700 text-white font-medium px-5 py-2 rounded-md shadow">
                    Logout
                </a>
            </div>
        </div>
    </div>

    <div class="max-w-3xl mx-auto mt-12">
        <h2 class="text-2xl font-semibold text-purple-300 mb-6">📌 All Posts</h2>

        <%
            if (posts != null && !posts.isEmpty()) {
                for (org.bson.Document post : posts) {
        %>
        <div class="bg-gray-800 border border-gray-700 rounded-lg p-5 mb-6 shadow">
            <h3 class="text-xl font-semibold text-white mb-2"><%= post.getString("title") %></h3>
            <p class="text-gray-300 mb-2"><%= post.getString("content") %></p>
            <span class="inline-block bg-gray-700 text-white text-xs font-medium px-3 py-1 rounded mb-2">
                <%= post.getString("visibility") %>
            </span>
            <p class="text-gray-400 text-sm">Author: <%= post.getString("author") != null ? post.getString("author") : "Unknown" %></p>

            <div class="mt-4 flex gap-3">
                <form action="<%= request.getContextPath() %>/updatepost" method="get">
                    <input type="hidden" name="id" value="<%= post.getObjectId("_id").toHexString() %>" />
                    <button type="submit" class="text-purple-400 border border-purple-500 hover:bg-purple-600 hover:text-white px-3 py-1 rounded text-sm">
                        ✏️ Update
                    </button>
                </form>

                <form action="<%= request.getContextPath() %>/deletepost" method="post">
                    <input type="hidden" name="id" value="<%= post.getObjectId("_id").toHexString() %>" />
                    <button type="submit" class="text-red-400 border border-red-500 hover:bg-red-600 hover:text-white px-3 py-1 rounded text-sm">
                        🗑️ Delete
                    </button>
                </form>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div class="text-center text-gray-400 border border-gray-700 bg-gray-900 rounded-lg p-6">
            No posts found. Start by adding one!
        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
