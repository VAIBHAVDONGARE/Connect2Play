import React, { useEffect, useState } from "react";
import { getTurfs } from "../../services/turfService";

const TurfList = () => {
  const [turfs, setTurfs] = useState([]);

  useEffect(() => {
    async function fetchTurfs() {
      const data = await getTurfs();
      setTurfs(data);
    }
    fetchTurfs();
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Available Turfs</h2>
      {turfs.map((turf) => (
        <div key={turf.id} className="border p-3 mb-2 rounded-lg shadow-md">
          <p className="font-semibold">{turf.name}</p>
          <p>Location: {turf.location}</p>
          <p>Price: ₹{turf.price}/hour</p>
        </div>
      ))}
    </div>
  );
};

export default TurfList;
