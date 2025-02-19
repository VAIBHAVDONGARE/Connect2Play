// src/components/TurfBooking.jsx
import React, { useState, useEffect } from 'react';
import { turfService } from '../services/api';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

const TurfBooking = ({ user }) => {
  const [turfs, setTurfs] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [selectedTurf, setSelectedTurf] = useState(null);
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [selectedSlot, setSelectedSlot] = useState(null);
  const [searchParams, setSearchParams] = useState({
    location: '',
    sport: '',
    date: new Date()
  });

  const sports = ['Football', 'Cricket', 'Basketball', 'Tennis', 'Badminton'];
  const timeSlots = [
    '06:00 AM', '07:00 AM', '08:00 AM', '09:00 AM', '10:00 AM', 
    '11:00 AM', '12:00 PM', '01:00 PM', '02:00 PM', '03:00 PM',
    '04:00 PM', '05:00 PM', '06:00 PM', '07:00 PM', '08:00 PM',
    '09:00 PM', '10:00 PM'
  ];

  useEffect(() => {
    searchTurfs();
  }, [searchParams.date]);

  const searchTurfs = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await turfService.searchTurfs(searchParams);
      setTurfs(response.data);
    } catch (err) {
      setError('Failed to fetch turfs');
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = (e) => {
    e.preventDefault();
    searchTurfs();
  };

  const handleBooking = async () => {
    if (!selectedTurf || !selectedSlot) {
      setError('Please select a turf and time slot');
      return;
    }

    setLoading(true);
    setError('');
    setSuccess('');

    try {
      const bookingData = {
        turfId: selectedTurf.id,
        date: selectedDate,
        timeSlot: selectedSlot,
        userId: user.id
      };

      await turfService.createBooking(selectedTurf.id, bookingData);
      setSuccess('Booking confirmed successfully!');
      setSelectedTurf(null);
      setSelectedSlot(null);
      // Refresh turfs to update availability
      searchTurfs();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to create booking');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-6xl mx-auto p-4">
      {/* Search Section */}
      <div className="bg-white rounded-lg shadow-md p-6 mb-6">
        <h2 className="text-2xl font-bold mb-4">Find Available Turfs</h2>
        
        <form onSubmit={handleSearch} className="grid grid-cols-1 md:grid-cols-3 gap-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Location
            </label>
            <input
              type="text"
              value={searchParams.location}
              onChange={(e) => setSearchParams(prev => ({
                ...prev,
                location: e.target.value
              }))}
              className="w-full p-2 border rounded-md focus:ring-blue-500 focus:border-blue-500"
              placeholder="Enter location"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Sport
            </label>
            <select
              value={searchParams.sport}
              onChange={(e) => setSearchParams(prev => ({
                ...prev,
                sport: e.target.value
              }))}
              className="w-full p-2 border rounded-md focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="">All Sports</option>
              {sports.map(sport => (
                <option key={sport} value={sport}>{sport}</option>
              ))}
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Date
            </label>
            <DatePicker
              selected={searchParams.date}
              onChange={(date) => setSearchParams(prev => ({
                ...prev,
                date: date
              }))}
              minDate={new Date()}
              className="w-full p-2 border rounded-md focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <button
            type="submit"
            className="md:col-span-3 bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
          >
            Search Turfs
          </button>
        </form>
      </div>

      {/* Messages */}
      {error && (
        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
          {error}
        </div>
      )}
      {success && (
        <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
          {success}
        </div>
      )}

      {/* Turfs List */}
      {loading ? (
        <div className="text-center py-8">Loading...</div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {turfs.map(turf => (
            <div
              key={turf.id}
              className={`bg-white rounded-lg shadow-md overflow-hidden ${
                selectedTurf?.id === turf.id ? 'ring-2 ring-blue-500' : ''
              }`}
            >
              <img
                src={turf.image || '/default-turf.jpg'}
                alt={turf.name}
                className="w-full h-48 object-cover"
              />
              <div className="p-4">
                <h3 className="text-xl font-bold mb-2">{turf.name}</h3>
                <p className="text-gray-600 mb-2">{turf.location}</p>
                <p className="text-gray-600 mb-2">Sports: {turf.sports.join(', ')}</p>
                <p className="text-gray-600 mb-4">₹{turf.pricePerHour}/hour</p>
                
                <div className="flex justify-between items-center">
                  <div className="flex items-center">
                    <span className={`w-3 h-3 rounded-full ${
                      turf.available ? 'bg-green-500' : 'bg-red-500'
                    } mr-2`}></span>
                    <span className={turf.available ? 'text-green-500' : 'text-red-500'}>
                      {turf.available ? 'Available' : 'Booked'}
                    </span>
                  </div>
                  <button
                    onClick={() => setSelectedTurf(turf)}
                    className={`px-4 py-2 rounded-md ${
                      selectedTurf?.id === turf.id
                        ? 'bg-blue-100 text-blue-600'
                        : 'bg-blue-500 text-white hover:bg-blue-600'
                    }`}
                  >
                    {selectedTurf?.id === turf.id ? 'Selected' : 'Select'}
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      {/* Booking Section */}
      {selectedTurf && (
        <div className="mt-8 bg-white rounded-lg shadow-md p-6">
          <h3 className="text-xl font-bold mb-4">Book {selectedTurf.name}</h3>
          
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Select Time Slot
            </label>
            <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-2">
              {timeSlots.map(slot => (
                <button
                  key={slot}
                  onClick={() => setSelectedSlot(slot)}
                  className={`p-2 rounded-md text-sm ${
                    selectedSlot === slot
                      ? 'bg-blue-500 text-white'
                      : 'bg-gray-100 hover:bg-gray-200'
                  }`}
                  disabled={selectedTurf.bookedSlots?.includes(slot)}
                >
                  {slot}
                </button>
              ))}
            </div>
          </div>

          <div className="mt-6 flex justify-between items-center">
            <div>
              <p className="text-lg font-semibold">
                Total: ₹{selectedTurf.pricePerHour}
              </p>
              <p className="text-sm text-gray-600">
                {selectedSlot ? `Time: ${selectedSlot}` : 'Select a time slot'}
              </p>
            </div>
            <button
              onClick={handleBooking}
              disabled={!selectedSlot || loading}
              className="bg-green-500 text-white py-2 px-6 rounded-md hover:bg-green-600 disabled:bg-gray-300 disabled:cursor-not-allowed"
            >
              {loading ? 'Booking...' : 'Confirm Booking'}
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TurfBooking;