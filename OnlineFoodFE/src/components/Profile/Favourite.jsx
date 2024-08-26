import RestaurantCard from "../Restaurant/RestaurantCard";

const Favourite = () => {
  return (
    <div>
      <h1 className="py-5 text-2xl font-semibold">Le Minh Duy</h1>
      <div className="flex flex-wrap justify-center gap-3">
        {[1, 1, 1, 1].map((item, index) => {
          return <RestaurantCard key={index} />;
        })}
      </div>
    </div>
  );
};

export default Favourite;
