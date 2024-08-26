import PropTypes from "prop-types";

const ItemCarousel = ({ image, title }) => {
  console.log("image", image);
  return (
    <div className="flex flex-col justify-center items-center">
      <img
        src={image}
        className="w-[10rem] h-[10rem] lg:h-[14rem] lg:w-[14rem] rounded-full object-cover object-center"
        alt=""
      />
      <p className="py-5 font-semibold text-xl text-gray-400">{title}</p>
    </div>
  );
};

ItemCarousel.propTypes = {
  image: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
};

export default ItemCarousel;
