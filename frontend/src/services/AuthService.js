// src/services/authService.js
import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL;

export const authService = {
  async login(credentials) {
    const response = await axios.post(`${API_URL}/auth/login`, credentials);
    return response.data;
  },

  async register(userData) {
    const response = await axios.post(`${API_URL}/auth/register`, userData);
    return response.data;
  },

  async validateToken(token) {
    const response = await axios.get(`${API_URL}/auth/validate`, {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
  },

  async forgotPassword(email) {
    const response = await axios.post(`${API_URL}/auth/forgot-password`, { email });
    return response.data;
  },

  async resetPassword(token, password) {
    const response = await axios.post(`${API_URL}/auth/reset-password`, {
      token,
      password
    });
    return response.data;
  }
};

// src/services/bookingService.js
import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL;

export const bookingService = {
  async getTurfAvailability(date) {
    const response = await axios.get(`${API_URL}/turf/availability`, {
      params: { date }
    });
    return response.data;
  },

  async createBooking(bookingData) {
    const response = await axios.post(`${API_URL}/bookings`, bookingData);
    return response.data;
  },

  async getUserBookings(userId) {
    const response = await axios.get(`${API_URL}/bookings/user/${userId}`);
    return response.data;
  },

  async cancelBooking(bookingId) {
    const response = await axios.delete(`${API_URL}/bookings/${bookingId}`);
    return response.data;
  }
};

// src/services/teamService.js
import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL;

export const teamService = {
  async createTeam(teamData) {
    const response = await axios.post(`${API_URL}/teams`, teamData);
    return response.data;
  },

  async getUserTeams(userId) {
    const response = await axios.get(`${API_URL}/teams/user/${userId}`);
    return response.data;
  },

  async updateTeam(teamId, teamData) {
    const response = await axios.put(`${API_URL}/teams/${teamId}`, teamData);
    return response.data;
  },

  async deleteTeam(teamId) {
    const response = await axios.delete(`${API_URL}/teams/${teamId}`);
    return response.data;
  }
};