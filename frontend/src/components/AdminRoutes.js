import React from 'react';
import {Navigate, Outlet} from 'react-router-dom';

export default function AdminRoutes() {
    const role = localStorage.getItem("role");
    return (
        role === "ADMIN" ? <Outlet/> : <Navigate to="/"/>
    );
}
