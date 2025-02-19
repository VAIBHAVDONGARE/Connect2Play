import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import PrivateRoutes from "./PrivateRoutes";
import PublicRoutes from "./PublicRoutes";
import HomePage from "../pages/HomePage";
import LoginPage from "../pages/auth/LoginPage";
import RegisterPage from "../pages/auth/RegisterPage";
import DashboardPage from "../pages/DashboardPage";
import TeamRequestsPage from "../pages/TeamRequestsPage";
import FriendRequestsPage from "../pages/FriendRequestsPage";
import TurfPage from "../pages/TurfPage";

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        {/* Public Routes (Accessible without authentication) */}
        <Route element={<PublicRoutes />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Route>

        {/* Private Routes (Require authentication) */}
        <Route element={<PrivateRoutes />}>
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/team-requests" element={<TeamRequestsPage />} />
          <Route path="/friend-requests" element={<FriendRequestsPage />} />
          <Route path="/turf" element={<TurfPage />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default AppRoutes;
