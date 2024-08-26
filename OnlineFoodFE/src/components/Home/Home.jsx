import Auth from "../Auth/Auth";
import RestaurantCard from "../Restaurant/RestaurantCard";
import "./Home.css";
import ItemsCarousel from "./ItemsCarousel";
const restaurantCard = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1];
const Home = () => {
  return (
    <>
      <div className="pb-10">
        <section className="banner -z-50 relative flex flex-col justify-center items-center">
          <div className="w-[50vw] z-10 text-center">
            <p className="font-bold text-2xl lg:text-6xl z-10 py-5">
              Duy's Food
            </p>
            <p className="text-xl z-10 text-gray-300 lg:text-4xl">
              Taste the convenience : Food,Fast and Delivered
            </p>
          </div>
          <div className="cover absolute top-0 left-0 right-0"></div>
          <div className="fadout"></div>
        </section>
        <section className="p-10 lg:py-10 lg:px-20">
          <p className="text-2xl font-semibold text-gray-400 py-3 pb-10">
            Top Food
          </p>
          <ItemsCarousel />
        </section>
        <section className="px-5 lg:px-20 pt-5">
          <p className="text-2xl font-semibold text-gray-400 pb-10 ">
            Top Restaurant
          </p>
          <div className="flex flex-wrap justify-around gap-5 items-center">
            {restaurantCard.map((item, index) => {
              return <RestaurantCard key={index} />;
            })}
          </div>
        </section>
      </div>
    </>
  );
};

export default Home;
