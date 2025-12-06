export default {
  template: "#home-template",
  mounted() {
    const slides = document.querySelectorAll(".carousel-slide");
    const dots = document.querySelectorAll(".dot");
    let index = 0;

    function showSlide(i) {
      slides.forEach((slide, idx) => {
        slide.style.opacity = idx === i ? "1" : "0";
        dots[idx].classList.toggle("bg-brand", idx === i);
        dots[idx].classList.toggle("bg-primary", idx !== i);
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

    document.getElementById("next").onclick = nextSlide;
    document.getElementById("prev").onclick = prevSlide;

    dots.forEach((dot, i) => dot.onclick = () => {
      index = i;
      showSlide(index);
    });

    setInterval(nextSlide, 3000);
  },
  data() {
    return {
      slides: [
        {
          title: `<br><br>40 anos de GlobalPay`,
          image: 'assets/img/posts/f-1.png'
        },
        {
          title: `Faça parte de uma equipe que vem crescendo ano após ano`,
          image: 'assets/img/posts/b-1.png'
        },
        {
          title: `<p class="text-shadow-lg text-shadow-black/50">FAB realiza homenagem para a GlobalPay</p>`,
          image: 'assets/img/posts/k-2.png'
        },
        {
          title: `<i class="text-white text-shadow-lg text-shadow-black/50">Nosso time feminino da Global!!!</i>`,
          image: `assets/img/posts/e-2.png`
        }
      ]
    }
  }
};