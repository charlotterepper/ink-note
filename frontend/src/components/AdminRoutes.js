import React from 'react';
import {Navigate, Outlet} from 'react-router-dom';

export default function AdminRoutes() {
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("role");
    return (
        token && role === "ADMIN" ? <Outlet/> : <Navigate to="/"/>
    );
}
