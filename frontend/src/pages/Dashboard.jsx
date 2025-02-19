// src/components/Dashboard.jsx
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { connectionService, gameService, turfService } from '../services/api';

const Dashboard = ({ user }) => {
  const [pendingRequests, setPendingRequests] = useState([]);
  const [upcomingGames, setUpcomingGames] = useState([]);
  const [upcomingBookings, setUpcomingBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const fetchDashboardData = async () => {
    try {
      const [requestsRes, gamesRes, bookingsRes] = await Promise.all([
        connectionService.getPendingRequests(),
        gameService.getGames({ status: 'UPCOMING' }),
        turfService.getBookings()
      ]);

      setPendingRequests(requestsRes.data);
      setUpcomingGames(gamesRes.data);
      setUpcomingBookings(bookingsRes.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching dashboard data:', error);
      setLoading(false);
    }
  };

  const handleAcceptRequest = async (requestId) => {
    try {
      await connectionService.acceptRequest(requestId);
      setPendingRequests(prev => 
        prev.filter(request => request.id !== requestId)
      );
    } catch (error) {
      console.error('Error accepting request:', error);
    }
  };

  const handleRejectRequest = async (requestId) => {
    try {
      await connectionService.rejectRequest(requestId);
      setPendingRequests(prev => 
        prev.filter(request => request.id !== requestId)
      );
    } catch (error) {
      console.error('Error rejecting request:', error);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="max-w-6xl mx-auto">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {/* Quick Actions */}
        <div className="bg-white rounded-lg shadow p-6">
          <h2 className="text-xl font-bold mb-4">Quick Actions</h2>
          <div className="space-y-3">
            <Link
              to="/find-players"
              className="block w-full text-center bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
            >
              Find Players
            </Link>
            <Link
              to="/turf-booking"
              className="block w-full text-center bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600"
            >
              Book a Turf
            </Link>
            <Link
              to="/games/create"
              className="block w-full text-center bg-purple-500 text-white py-2 px-4 rounded hover:bg-purple-600"
            >
              Create Game
            </Link>
          </div>
        </div>

        {/* Pending Requests */}
        <div className="bg-white rounded-lg shadow p-6">
          <h2 className="text-xl font-bold mb-4">Connection Requests</h2>
          {pendingRequests.length === 0 ? (
            <p className="text-gray-500">No pending requests</p>
          ) : (
            <div className="space-y-4">
              {pendingRequests.map(request => (
                <div key={request.id} className="flex items-center justify-between border-b pb-4">
                  <div className="flex items-center">
                    <img
                      src={request.fromUser.avatar || '/default-avatar.png'}
                      alt={request.fromUser.name}
                      className="w-10 h-10 rounded-full mr-3"
                    />
                    <div>
                      <p className="font-semibold">{request.fromUser.name}</p>
                      <p className="text-sm text-gray-500">{request.fromUser.location}</p>
                    </div>
                  </div>
                  <div className="flex space-x-2">
                    <button
                      onClick={() => handleAcceptRequest(request.id)}
                      className="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600"
                    >
                      Accept
                    </button>
                    <button
                      onClick={() => handleRejectRequest(request.id)}
                      className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                    >
                      Reject
                    </button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>

        {/* Upcoming Games */}
        <div className="bg-white rounded-lg shadow p-6">
          <h2 className="text-xl font-bold mb-4">Upcoming Games</h2>
          {upcomingGames.length === 0 ? (
            <p className="text-gray-500">No upcoming games</p>
          ) : (
            <div className="space-y-4">
              {upcomingGames.map(game => (
                <Link
                  key={game.id}
                  to={`/games/${game.id}`}
                  className="block border-b pb-4 hover:bg-gray-50"
                >
                  <div className="flex justify-between items-start">
                    <div>
                      <p className="font-semibold">{game.sport}</p>
                      <p className="text-sm text-gray-500">{game.location}</p>
                      <p className="text-sm text-gray-500">
                        {new Date(game.dateTime).toLocaleString()}
                      </p>
                    </div>
                    <div className="text-right">
                      <p className="text-sm font-semibold">
                        {game.currentPlayers}/{game.maxPlayers} players
                      </p>
                      <p className="text-xs text-gray-500">
                        Organized by {game.organizer.name}
                      </p>
                    </div>
                  </div>
                </Link>
              ))}            </div>
            )}
          </div>
  
          {/* Upcoming Bookings */}
          <div className="bg-white rounded-lg shadow p-6">
            <h2 className="text-xl font-bold mb-4">Upcoming Turf Bookings</h2>
            {upcomingBookings.length === 0 ? (
              <p className="text-gray-500">No upcoming bookings</p>
            ) : (
              <div className="space-y-4">
                {upcomingBookings.map(booking => (
                  <div key={booking.id} className="flex justify-between items-start border-b pb-4">
                    <div>
                      <p className="font-semibold">{booking.turf.name}</p>
                      <p className="text-sm text-gray-500">{booking.turf.location}</p>
                      <p className="text-sm text-gray-500">
                        {new Date(booking.dateTime).toLocaleString()}
                      </p>
                    </div>
                    <div className="text-right">
                      <p className="text-sm font-semibold">
                        {booking.players.length}/{booking.turf.maxPlayers} players
                      </p>
                      <p className="text-xs text-gray-500">Booked by {booking.user.name}</p>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      </div>
    );
  };
  
  export default Dashboard;
  