<nav class="bg-gray-900 text-white shadow">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex items-center justify-between h-16">
            <div class="flex items-center">
                <a href="<%= request.getContextPath() %>/dashboard" class="text-xl font-bold text-indigo-400 hover:text-indigo-300 transition">MyBlog</a>
            </div>
            <div class="hidden md:flex space-x-6">
                <a href="<%= request.getContextPath() %>/dashboard" class="hover:text-indigo-300 transition">Dashboard</a>
                <a href="<%= request.getContextPath() %>/publicpost" class="hover:text-indigo-300 transition">Explore</a>
                <a href="<%= request.getContextPath() %>/addpost" class="hover:text-indigo-300 transition">Add Post</a>
                <a href="<%= request.getContextPath() %>/logout" class="hover:text-red-400 transition">Logout</a>
            </div>

            <!-- Mobile Menu Button -->
            <div class="md:hidden flex items-center">
                <button class="mobile-menu-button focus:outline-none">
                    <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" stroke-width="2"
                         viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M4 6h16M4 12h16M4 18h16"></path>
                    </svg>
                </button>
            </div>
        </div>
    </div>

    <!-- Mobile Menu -->
    <div class="mobile-menu hidden md:hidden px-4 pb-4 space-y-2">
        <a href="<%= request.getContextPath() %>/dashboard" class="block text-sm hover:text-indigo-300">Dashboard</a>
        <a href="<%= request.getContextPath() %>/publicpost" class="block text-sm hover:text-indigo-300">Explore</a>
        <a href="<%= request.getContextPath() %>/addpost" class="block text-sm hover:text-indigo-300">Add Post</a>
        <a href="<%= request.getContextPath() %>/logout" class="block text-sm hover:text-red-400">Logout</a>
    </div>

    <script>
        // Toggle Mobile Menu
        const btn = document.querySelector(".mobile-menu-button");
        const menu = document.querySelector(".mobile-menu");
        btn.addEventListener("click", () => {
            menu.classList.toggle("hidden");
        });
    </script>
</nav>
