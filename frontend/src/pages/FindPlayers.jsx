// src/components/FindPlayers.jsx
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FindPlayers = ({ user }) => {
  const [players, setPlayers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [filters, setFilters] = useState({
    sport: '',
    location: '',
    skillLevel: ''
  });

  useEffect(() => {
    fetchPlayers();
  }, [filters]);

  const fetchPlayers = async () => {
    try {
      const queryParams = new URLSearchParams(filters).toString();
      const response = await axios.get(`/players/search?${queryParams}`);
      setPlayers(response.data);
      setLoading(false);
    } catch (error) {
      setError('Failed to fetch players. Please try again.');
      setLoading(false);
    }
  };

  const handleConnect = async (playerId) => {
    try {
      await axios.post(`/connections/request`, {
        toUserId: playerId
      });
      // Update the UI to show pending connection
      setPlayers(players.map(player => 
        player.id === playerId 
          ? { ...player, connectionStatus: 'PENDING' }
          : player
      ));
    } catch (error) {
      setError('Failed to send connection request. Please try again.');
    }
  };

  const handleFilterChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value
    });
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div className="text-red-500">{error}</div>;

  return (
    <div className="max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-6">Find Players</h2>
      
      {/* Filters */}
      <div className="bg-white p-4 rounded-lg shadow mb-6 grid grid-cols-3 gap-4">
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Sport
          </label>
          <select
            name="sport"
            value={filters.sport}
            onChange={handleFilterChange}
            className="w-full border rounded-md p-2"
          >
            <option value="">All Sports</option>
            <option value="football">Football</option>
            <option value="basketball">Basketball</option>
            <option value="cricket">Cricket</option>
          </select>
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Location
          </label>
          <input
            type="text"
            name="location"
            value={filters.location}
            onChange={handleFilterChange}
            className="w-full border rounded-md p-2"
            placeholder="Enter location"
          />
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Skill Level
          </label>
          <select
            name="skillLevel"
            value={filters.skillLevel}
            onChange={handleFilterChange}
            className="w-full border rounded-md p-2"
          >
            <option value="">All Levels</option>
            <option value="beginner">Beginner</option>
            <option value="intermediate">Intermediate</option>
            <option value="advanced">Advanced</option>
          </select>
        </div>
      </div>

      {/* Players Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {players.map(player => (
          <div key={player.id} className="bg-white rounded-lg shadow p-6">
            <div className="flex items-center mb-4">
              <img
                src={player.avatar || '/default-avatar.png'}
                alt={player.name}
                className="w-12 h-12 rounded-full mr-4"
              />
              <div>
                <h3 className="font-bold">{player.name}</h3>
                <p className="text-sm text-gray-600">{player.location}</p>
              </div>
            </div>
            <div className="mb-4">
              <p className="text-sm"><strong>Preferred Sports:</strong> {player.sports.join(', ')}</p>
              <p className="text-sm"><strong>Skill Level:</strong> {player.skillLevel}</p>
            </div>
            <button
              onClick={() => handleConnect(player.id)}
              disabled={player.connectionStatus === 'PENDING' || player.connectionStatus === 'CONNECTED'}
              className={`w-full py-2 px-4 rounded-lg ${
                player.connectionStatus === 'PENDING'
                  ? 'bg-gray-400 cursor-not-allowed'
                  : player.connectionStatus === 'CONNECTED'
                  ? 'bg-green-500'
                  : 'bg-blue-500 hover:bg-blue-600'
              } text-white font-bold`}
            >
              {player.connectionStatus === 'PENDING'
                ? 'Request Pending'
                : player.connectionStatus === 'CONNECTED'
                ? 'Connected'
                : 'Connect'}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default FindPlayers;