import React, { useEffect, useState } from "react";
import FriendRequestItem from "./FriendRequestItem";
import { getFriendRequests } from "../../services/friendRequestService";

const FriendRequestList = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    async function fetchRequests() {
      const data = await getFriendRequests();
      setRequests(data);
    }
    fetchRequests();
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Friend Requests</h2>
      {requests.map((request) => (
        <FriendRequestItem key={request.id} request={request} />
      ))}
    </div>
  );
};

export default FriendRequestList;
