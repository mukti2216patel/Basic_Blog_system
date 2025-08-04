<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Add Post</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black text-white min-h-screen flex items-center justify-center px-4">

<div class="w-full max-w-md bg-gray-900 shadow-lg rounded-xl p-8">
    <h3 class="text-2xl font-bold text-center mb-6 text-indigo-400">➕ Add New Post</h3>

    <% if (request.getAttribute("error") != null) { %>
    <div class="bg-red-600/20 text-red-300 px-4 py-3 rounded mb-4">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="<%=request.getContextPath()%>/addpost" method="POST" class="space-y-5">
        <div>
            <label for="title" class="block text-sm font-medium mb-1 text-gray-300">Post Title</label>
            <input type="text" id="title" name="title" required
                   class="w-full bg-gray-800 border border-gray-700 text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-500" />
        </div>

        <div>
            <label for="content" class="block text-sm font-medium mb-1 text-gray-300">Post Content</label>
            <textarea id="content" name="content" rows="5" required
                      class="w-full bg-gray-800 border border-gray-700 text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-500"></textarea>
        </div>

        <div>
            <span class="block text-sm font-medium text-gray-300 mb-2">Visibility</span>
            <div class="flex space-x-6">
                <label class="inline-flex items-center">
                    <input type="radio" name="visibility" value="public" checked
                           class="text-indigo-500 focus:ring-indigo-600 bg-gray-700 border-gray-600">
                    <span class="ml-2 text-sm">Public</span>
                </label>
                <label class="inline-flex items-center">
                    <input type="radio" name="visibility" value="private"
                           class="text-indigo-500 focus:ring-indigo-600 bg-gray-700 border-gray-600">
                    <span class="ml-2 text-sm">Private</span>
                </label>
            </div>
        </div>

        <div class="pt-4 flex justify-between">
            <a href="<%=request.getContextPath()%>/dashboard"
               class="bg-gray-700 hover:bg-gray-600 px-6 py-2 rounded-lg text-white font-medium transition">Cancel</a>
            <button type="submit"
                    class="bg-indigo-600 hover:bg-indigo-700 px-6 py-2 rounded-lg text-white font-medium transition">Submit</button>
        </div>
    </form>
</div>

</body>
</html>
