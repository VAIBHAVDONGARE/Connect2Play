// src/components/BookingHistory.jsx
import React, { useState, useEffect } from 'react';
import { turfService } from '../services/api';

const BookingHistory = ({ user }) => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const response = await turfService.getBookings();
      setBookings(response.data);
    } catch (err) {
      setError('Failed to fetch booking history');
    } finally {
      setLoading(false);
    }
  };

  const cancelBooking = async (bookingId) => {
    try {
      await turfService.cancelBooking(bookingId);
      // Update bookings list
      setBookings(bookings.filter(booking => booking.id !== bookingId));
    } catch (err) {
      setError('Failed to cancel booking');
    }
  };

  if (loading) return <div className="text-center py-8">Loading...</div>;
  if (error) return <div className="text-red-500 text-center py-8">{error}</div>;

  return (
    <div className="max-w-4xl mx-auto p-4">
      <h2 className="text-2xl font-bold mb-6">Your Bookings</h2>
      
      <div className="space-y-4">
        {bookings.length === 0 ? (
          <p className="text-center text-gray-500">No bookings found</p>
        ) : (
          bookings.map(booking => (
            <div
              key={booking.id}
              className="bg-white rounded-lg shadow-md p-4 flex flex-wrap justify-between items-center"
            >
              <div className="flex-1 min-w-0 mr-4">
                <h3 className="text-lg font-semibold mb-1">{booking.turf.name}</h3>
                <p className="text-gray-600">
                  {new Date(booking.date).toLocaleDateString()} at {booking.timeSlot}
                </p>
                <p className="text-gray-600">{booking.turf.location}</p>
              </div>
              
              <div className="flex items-center space-x-4">
                <span className={`px-3 py-1 rounded-full text-sm ${
                  booking.status === 'CONFIRMED'
                    ? 'bg-green-100 text-green-800'
                    : booking.status === 'CANCELLED'
                    ? 'bg-red-100 text-red-800'
                    : 'bg-yellow-100 text-yellow-800'
                }`}>
                  {booking.status}
                </span>
                
                {booking.status === 'CONFIRMED' && 
                  new Date(booking.date) > new Date() && (
                    <button
                      onClick={() => cancelBooking(booking.id)}
                      className="text-red-500 hover:text-red-600"
                    >
                      Cancel
                    </button>
                  )}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default BookingHistory;