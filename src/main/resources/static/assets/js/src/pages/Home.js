export default {
  template: "#home-template",
  mounted() {
    // const slides = document.querySelectorAll(".carousel-slide");
    // const dots = document.querySelectorAll(".dot");
    // let index = 0;

    // function showSlide(i) {
    //   slides.forEach((slide, idx) => {
    //     slide.style.opacity = idx === i ? "1" : "0";
    //     dots[idx].classList.toggle("bg-gray-300", idx === i);
    //     dots[idx].classList.toggle("bg-gray-500", idx !== i);
    //   });
    // }

    // function nextSlide() {
    //   index = (index + 1) % slides.length;
    //   showSlide(index);
    // }

    // function prevSlide() {
    //   index = (index - 1 + slides.length) % slides.length;
    //   showSlide(index);
    // }

    // document.getElementById("next").onclick = nextSlide;
    // document.getElementById("prev").onclick = prevSlide;

    // dots.forEach((dot, i) => dot.onclick = () => {
    //   index = i;
    //   showSlide(index);
    // });

    // // Auto-play
    // setInterval(nextSlide, 5000);
  }
};