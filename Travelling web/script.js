$(document).ready(function () {
    $(".review-slider").slick({
      arrows: false,
      infinite: true,
      slidesToShow: 3,
      slidesToScroll: 3,
      dots: true,
      autoplay: true,
      autoplaySpeed: 2000,
      responsive: [
        {
          breakpoint: 768,
          settings: {
            arrows: false,
            slidesToShow: 2,
            slidesToScroll: 2,
          },
        },
        {
          breakpoint: 568,
          settings: {
            arrows: false,
            slidesToShow: 1,
            slidesToScroll: 1,
          },
        },
      ],
    });
});
