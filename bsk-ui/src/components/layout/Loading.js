import { Spin } from 'antd';
import React, { Component } from 'react';

// class Loading {
//     constructor () {
//         this.loadingTag = 0;
//         this.subscriptions = [];
//     }
//     add () {
//         this.loadingTag++;
//         this.subscriptions.forEach(f => f(this.loadingTag));
//     }
//     sub () {
//         this.loadingTag--;
//         this.subscriptions.forEach(f => f(this.loadingTag));
//     }
//     get () {
//         return this.loadingTag;
//     }
//     subscribe (f) {
//         this.subscriptions.push(f);
//     }
// }

// let loadingPublisher = new Loading();

class Loading extends Component {
    constructor (props) {
        super(props);
        this.state = {
            globalLoading: false
        };
    }
    componentDidMount () {
        // 增加订阅loading状态更新
        loadingPublisher.subscribe((loadingTag)=>{
            if (this.state.globalLoading !== !!loadingTag) {
                this.setState({globalLoading: !!loadingTag});
            }
        });
    }
    render () {
        return <Spin spinning={this.state.globalLoading} tip= '加载中...' />;
    }
}

export default Loading