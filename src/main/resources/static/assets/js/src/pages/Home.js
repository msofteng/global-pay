export default {
  template: `
    <!-- Carousel Container -->
    <div id="carousel" class="relative w-full h-[500px] md:h-[450px] lg:h-[400px] overflow-hidden">
      <!-- Slides -->
      <div class="carousel-slide absolute inset-0 transition-opacity duration-700 opacity-100
                  bg-cover bg-center"
          style="background-image:url('https://images.unsplash.com/photo-1581091226825-a6a2a5aee158?auto=format&fit=crop&w=1500&q=80');">
        <div class="w-full h-full bg-black/40 flex items-center">
          <div class="max-w-3xl px-10">
            <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-yellow-300 leading-tight">
              Começamos<br>a escrever<br>um novo capítulo...
            </h1>
          </div>
        </div>
      </div>

      <div class="carousel-slide absolute inset-0 transition-opacity duration-700 opacity-0
                  bg-cover bg-center"
          style="background-image:url('https://images.unsplash.com/photo-1556741533-f6acd647d2fb?auto=format&fit=crop&w=1500&q=80');">
        <div class="w-full h-full bg-black/40 flex items-center">
          <div class="max-w-3xl px-10">
            <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-blue-300 leading-tight">
              Seu futuro financeiro<br> começa aqui.
            </h1>
          </div>
        </div>
      </div>

      <div class="carousel-slide absolute inset-0 transition-opacity duration-700 opacity-0
                  bg-cover bg-center"
          style="background-image:url('https://images.unsplash.com/photo-1521791136064-7986c2920216?auto=format&fit=crop&w=1500&q=80');">
        <div class="w-full h-full bg-black/40 flex items-center">
          <div class="max-w-3xl px-10">
            <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-green-300 leading-tight">
              Segurança, inovação<br>e tecnologia ao seu lado.
            </h1>
          </div>
        </div>
      </div>

      <!-- Dots -->
      <div class="absolute bottom-4 left-1/2 -translate-x-1/2 flex space-x-3">
        <button class="dot w-3 h-3 rounded-full bg-gray-300"></button>
        <button class="dot w-3 h-3 rounded-full bg-gray-500"></button>
        <button class="dot w-3 h-3 rounded-full bg-gray-500"></button>
      </div>

      <!-- Navigation Arrows -->
      <button id="prev" class="absolute left-4 top-1/2 -translate-y-1/2 bg-black/30 hover:bg-black/50 text-white p-3 rounded-full">
        ❮
      </button>

      <button id="next" class="absolute right-4 top-1/2 -translate-y-1/2 bg-black/30 hover:bg-black/50 text-white p-3 rounded-full">
        ❯
      </button>
    </div>
  `,
  mounted() {
    const slides = document.querySelectorAll('.carousel-slide');
    const dots = document.querySelectorAll('.dot');
    let index = 0;

    function showSlide(i) {
      slides.forEach((slide, idx) => {
        slide.style.opacity = idx === i ? '1' : '0';
        dots[idx].classList.toggle('bg-gray-300', idx === i);
        dots[idx].classList.toggle('bg-gray-500', idx !== i);
      });
    }

    function nextSlide() {
      index = (index + 1) % slides.length;
      showSlide(index);
    }

    function prevSlide() {
      index = (index - 1 + slides.length) % slides.length;
      showSlide(index);
    }

    document.getElementById('next').onclick = nextSlide;
    document.getElementById('prev').onclick = prevSlide;

    dots.forEach((dot, i) => dot.onclick = () => {
      index = i;
      showSlide(index);
    });

    // Auto-play
    setInterval(nextSlide, 5000);
  }
};