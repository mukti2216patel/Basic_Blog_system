<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, org.bson.Document" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Public Posts</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black text-white min-h-screen">

<%@ include file="navbar.jsp" %>

<div class="container mx-auto px-4 py-10">
    <h1 class="text-center text-4xl font-semibold mb-10 text-purple-300">📝 Public Posts</h1>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <%
            List<Document> l = (List<Document>) request.getAttribute("posts");
            if (l != null && !l.isEmpty()) {
                for (Document d : l) {
                    String title = d.getString("title");
                    String author = d.getString("author");
                    String content = d.getString("content");
        %>
        <div class="bg-gray-800 border border-gray-700 rounded-lg shadow p-6 flex flex-col justify-between h-full">
            <div>
                <h2 class="text-xl font-bold mb-2 text-white"><%= title %></h2>
                <p class="text-gray-300"><%= content %></p>
            </div>
            <div class="mt-4 text-right text-sm text-gray-400">
                By <%= author %>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div class="col-span-1 md:col-span-2 lg:col-span-3">
            <div class="bg-gray-900 border border-gray-700 text-center p-6 rounded-lg text-yellow-400">
                ⚠️ No posts available at the moment.
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
