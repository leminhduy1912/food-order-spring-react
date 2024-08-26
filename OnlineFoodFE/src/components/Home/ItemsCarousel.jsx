import Slider from "react-slick";
import { topFood } from "./TopFood";
import ItemCarousel from "./ItemCarousel";
const ItemsCarousel = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 3,
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: false,
  };
  return (
    <div className="slider-container">
      <Slider {...settings}>
        {topFood.map((item, index) => {
          return (
            <ItemCarousel key={index} image={item.image} title={item.title} />
          );
        })}
      </Slider>
    </div>
  );
};

export default ItemsCarousel;
