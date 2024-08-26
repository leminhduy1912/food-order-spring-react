import EventCard from "./EventCard";

const Event = () => {
  return (
    <div className="mt-5 px-5 flex flex-wrap gap-5">
      {[1, 1, 1, 1].map((item, index) => {
        return <EventCard key={index} />;
      })}
    </div>
  );
};

export default Event;
