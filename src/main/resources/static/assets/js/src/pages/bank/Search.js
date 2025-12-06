export default {
  template: `
    <div class="max-w-2xl mx-auto py-16 px-6">
      <!-- Ícone topo -->
      <div class="flex justify-center mb-6">
        <div class="p-3 rounded-full bg-gray-100">
          <svg class="w-8 h-8 text-gray-500" fill="none" stroke="currentColor" stroke-width="2"
              viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round"
              d="M17 20h5V4H2v16h5m10 0v-4a2 2 0 00-2-2H9a2 2 0 00-2 2v4m10 0H7" />
          </svg>
        </div>
      </div>

      <!-- Título -->
      <h2 class="text-center text-xl font-semibold mb-2">Add team members</h2>

      <p class="text-center text-gray-500 mb-8 max-w-lg mx-auto">
        You haven’t added any team members to your project yet. As the owner of this
        project, you can manage team member permissions.
      </p>

      <!-- Input + Button -->
      <div class="flex gap-3 mb-10">
        <input 
          type="email"
          placeholder="Enter an email"
          class="flex-1 border border-gray-300 rounded-lg px-4 py-2 
                focus:outline-none focus:ring-2 focus:ring-indigo-500"
        >
        <button 
          class="bg-indigo-600 hover:bg-indigo-700 text-white px-5 rounded-lg font-medium">
          Send invite
        </button>
      </div>

      <!-- Texto seção -->
      <p class="text-sm text-gray-500 mb-4">
        Team members previously added to projects
      </p>

      <hr class="border-gray-200 mb-2">

      <!-- Lista -->
      <ul class="space-y-6">

        <!-- Item -->
        <li>
          <div class="flex items-center justify-between">
            <div class="flex items-center gap-4">
              <img src="https://via.placeholder.com/48" class="w-12 h-12 rounded-full" />
              <div>
                <p class="font-medium text-gray-800">Lindsay Walton</p>
                <p class="text-gray-500 text-sm">Front-end Developer</p>
              </div>
            </div>

            <button class="flex items-center gap-1 text-indigo-600 hover:text-indigo-700 font-medium">
              <span class="text-xl">+</span> Invite
            </button>
          </div>

          <hr class="border-gray-200 mt-6">
        </li>

        <!-- Item -->
        <li>
          <div class="flex items-center justify-between">
            <div class="flex items-center gap-4">
              <img src="https://via.placeholder.com/48" class="w-12 h-12 rounded-full" />
              <div>
                <p class="font-medium text-gray-800">Courtney Henry</p>
                <p class="text-gray-500 text-sm">Designer</p>
              </div>
            </div>

            <button class="flex items-center gap-1 text-indigo-600 hover:text-indigo-700 font-medium">
              <span class="text-xl">+</span> Invite
            </button>
          </div>

          <hr class="border-gray-200 mt-6">
        </li>

        <!-- Item -->
        <li>
          <div class="flex items-center justify-between">
            <div class="flex items-center gap-4">
              <img src="https://via.placeholder.com/48" class="w-12 h-12 rounded-full" />
              <div>
                <p class="font-medium text-gray-800">Tom Cook</p>
                <p class="text-gray-500 text-sm">Director of Product</p>
              </div>
            </div>

            <button class="flex items-center gap-1 text-indigo-600 hover:text-indigo-700 font-medium">
              <span class="text-xl">+</span> Invite
            </button>
          </div>
        </li>

      </ul>

      <!-- Paginação -->
      <div class="mt-10 flex justify-center gap-2">
        <button class="px-3 py-1 border border-gray-300 rounded hover:bg-gray-100">&laquo;</button>
        <button class="px-3 py-1 border border-indigo-500 bg-indigo-600 text-white rounded">1</button>
        <button class="px-3 py-1 border border-gray-300 rounded hover:bg-gray-100">2</button>
        <button class="px-3 py-1 border border-gray-300 rounded hover:bg-gray-100">3</button>
        <button class="px-3 py-1 border border-gray-300 rounded hover:bg-gray-100">&raquo;</button>
      </div>
    </div>
  `,
};