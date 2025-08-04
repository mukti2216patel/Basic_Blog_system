<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black text-white min-h-screen flex items-center justify-center px-4">

<div class="w-full max-w-md bg-gray-900 shadow-lg rounded-xl p-8">
    <h3 class="text-2xl font-bold text-center mb-6 text-indigo-400">🔐 Login</h3>

    <%-- Show error if exists --%>
    <%
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage != null) {
    %>
        <div class="bg-red-600/20 text-red-300 px-4 py-3 rounded mb-4">
            <%= errorMessage %>
        </div>
    <%
        }
    %>

    <form action="login" method="POST" class="space-y-5">
        <div>
            <label for="email" class="block text-sm font-medium mb-1 text-gray-300">Email</label>
            <input type="email" id="email" name="email" required
                   class="w-full bg-gray-800 border border-gray-700 text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-500" />
        </div>

        <div>
            <label for="password" class="block text-sm font-medium mb-1 text-gray-300">Password</label>
            <input type="password" id="password" name="password" required
                   class="w-full bg-gray-800 border border-gray-700 text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-500" />
        </div>

        <div class="pt-2 text-center">
            <button type="submit"
                    class="bg-indigo-600 hover:bg-indigo-700 text-white font-medium px-6 py-2 rounded-lg transition">
                Login
            </button>
        </div>
    </form>

    <div class="mt-6 text-center text-sm text-gray-400">
        Don’t have an account? 
        <a href="index.jsp" class="text-indigo-400 hover:underline">Register here</a>
    </div>
</div>

</body>
</html>
