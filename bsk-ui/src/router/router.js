import React from 'react';
import Login from '../pages/login/Login';
import Home from '../pages/home/home';
import Register from '../pages/login/Register';
import Unauthorized from '../pages/error/Unauthorized';
import { isLogin } from '../common/auth';
import { Redirect } from "react-router-dom";

const config = [
    { 
        path: '/', 
        exact: true,
        component: Home,
        render: (props) => {
            const token = isLogin();
            if (!token) {
                return <Redirect to="/login" />
            }
            return <Home {...props} /> 
        },
        auth: true,
    },
    { 
        path: '/register', 
        component: Register,
        auth: true,
    },
    { 
        path: '/login', 
        component: Login,
    },
    { 
        path: '/unauthorized', 
        component: Unauthorized
    },
]

export default config;
