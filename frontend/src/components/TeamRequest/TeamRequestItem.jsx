import React from "react";

const TeamRequestItem = ({ request }) => {
  return (
    <div className="border p-3 mb-2 rounded-lg shadow-md">
      <p className="font-semibold">Team: {request.teamName}</p>
      <p>Status: {request.status}</p>
      <div className="flex gap-2 mt-2">
        <button className="bg-green-500 text-white p-2 rounded">Accept</button>
        <button className="bg-red-500 text-white p-2 rounded">Reject</button>
      </div>
    </div>
  );
};

export default TeamRequestItem;
