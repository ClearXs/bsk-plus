import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import router from './router';
import AuthRouter from './AuthRouter';

function AppRouter() {
    return (
        <Router>
            {        
                router.map((route, o) => {
                    if (route.auth) {
                        return <AuthRouter {...route} />
                    } else {
                        return <Route path={route.path} component={route.component} render={(props) => route.render(props)}/>
                    }
                })
            }
        </Router>
    )
}

export default AppRouter;