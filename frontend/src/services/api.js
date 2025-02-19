// src/services/api.js
import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'https://localhost:8080';

// Create axios instance with default config
const api = axios.create({
  baseURL: API_URL,
  withCredentials: true,
});

// Add token to requests
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Auth Services
export const authService = {
  login: (credentials) => api.post('/api/users/login', credentials),
  register: (userData) => api.post('/api/users/register', userData),
  //logout: () => api.post('/auth/logout'),
  //checkAuth: () => api.get('/auth/check'),
};

// User Services
export const userService = {
  getProfile: () => api.get('/api/users/id'),
  updateProfile: (data) => api.put('/api/users/update', data),
  searchUsers: (params) => api.get('/users/search', { params }),
};

// Connection Services
export const connectionService = {
  sendRequest: (senderId, receiverId) => api.post(`/${senderId}/friend-requests/${receiverId}`),
  acceptRequest: (requestId) => api.post(`/friend-requests/${requestId}/accept`),
  rejectRequest: (requestId) => api.post(`/friend-requests/${requestId}/reject`),
  cancelRequest: (requestId) => api.delete(`/friend-requests/${requestId}/cancel`),
  getPendingRequests: (userId) => api.get(`/${userId}/team-requests`),  // Fetch pending requests for a user
};

// Turf Services
export const turfService = {
 
  // Register a new Turf
  registerTurf: (ownerId, turfRegistrationData) => api.post(`/turfs/register/${ownerId}`, turfRegistrationData),

  // Update Turf Details
  updateTurf: (turfId, turfUpdateData) => api.put(`/turfs/update/${turfId}`, turfUpdateData),

  // Delete a Turf
  deleteTurf: (turfId) => api.delete(`/turfs/delete/${turfId}`),

  // Get Turf by ID
  getTurfById: (turfId) => api.get(`/turfs/${turfId}`),

  // Get all active turfs
  getAllActiveTurfs: () => api.get('/turfs/active'),

  // Search Turfs by City
  getTurfsByCity: (city) => api.get(`/turfs/city/${city}`),

  // Search Turfs by State
  getTurfsByState: (state) => api.get(`/turfs/state/${state}`),

  // Search Turfs by Sport Type
  getTurfsBySport: (sportType) => api.get(`/turfs/sport/${sportType}`),

  // Search Turfs by Price Range
  getTurfsByPriceRange: (minPrice, maxPrice) => api.get('/turfs/price-range', { params: { minPrice, maxPrice } }),

  // Upload Turf Images
  uploadTurfImages: (turfId, images) => api.post(`/turfs/${turfId}/upload-images`, images, {
    headers: {
      'Content-Type': 'multipart/form-data', // Important for uploading files
    }
  }),

  // Get Turf Images
  getTurfImages: (turfId) => api.get(`/turfs/${turfId}/images`),
 
};

// Game Services
export const gameService = {
  createGame: (gameData) => api.post('/games', gameData),
  joinGame: (gameId) => api.post(`/games/${gameId}/join`),
  getGames: (params) => api.get('/games', { params }),
  getGameDetails: (gameId) => api.get(`/games/${gameId}`),
};
