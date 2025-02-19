import React, { useEffect, useState } from "react";
import { getOwnerTurfs } from "../../services/turfService";

const TurfOwnerDashboard = () => {
  const [turfs, setTurfs] = useState([]);

  useEffect(() => {
    async function fetchOwnerTurfs() {
      const data = await getOwnerTurfs();
      setTurfs(data);
    }
    fetchOwnerTurfs();
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">My Turfs</h2>
      {turfs.map((turf) => (
        <div key={turf.id} className="border p-3 mb-2 rounded-lg shadow-md">
          <p className="font-semibold">{turf.name}</p>
          <p>Bookings: {turf.bookingsCount}</p>
          <p>Earnings: ₹{turf.earnings}</p>
        </div>
      ))}
    </div>
  );
};

export default TurfOwnerDashboard;
