import React, { useEffect, useState } from "react";
import TeamRequestItem from "./TeamRequestItem";
import { getTeamRequests } from "../../services/teamRequestService";

const TeamRequestList = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    async function fetchRequests() {
      const data = await getTeamRequests();
      setRequests(data);
    }
    fetchRequests();
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Team Requests</h2>
      {requests.map((request) => (
        <TeamRequestItem key={request.id} request={request} />
      ))}
    </div>
  );
};

export default TeamRequestList;
