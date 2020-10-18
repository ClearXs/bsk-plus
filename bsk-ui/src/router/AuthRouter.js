import React from 'react';
import { Route, Redirect } from "react-router-dom";
import { isLogin } from '../common/auth';

class AuthRouter extends React.Component {
    constructor(props) {
        super(props);
        this.setState = {

        }
    }

    render() {
        const route = this.props
        if (isLogin()) {
            if (route.exact) {
                return <Route path={route.path} exact component={route.component} render={(props) => route.render(props)}/>
            } else {
                return <Route path={route.path} component={route.component} render={(props) => route.render(props)}/>
            }
        } else {
            return <Redirect exact from={'/'} to={'/login'}/>
        }
    }

}

export default AuthRouter;