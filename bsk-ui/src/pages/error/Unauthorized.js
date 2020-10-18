import React from 'react';
import { Result, Button } from 'antd';


class Unauthorized extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }
    
    render() {
        return (
            <div>
                <Result
                    status="401"
                    title="401"
                    subTitle="抱歉，token过期，需要重新登录"
                    extra={<Button type="primary">登录</Button>}
                />,
            </div>
        );
    }
}
 
export default Unauthorized;