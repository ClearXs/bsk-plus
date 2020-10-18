import React, { Component, Fragment } from 'react';
import { Layout, Menu } from 'antd';
import { BrowserRouter as Router, Route } from "react-router-dom";
import { getToken } from '../../common/auth'
import Login from '../login/Login';
import Register from '../login/Register';
import './home.css';
import { queryUserAccordingToken, toLogout } from '../../store/actionCreators'
import {
    CloudSyncOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
  } from '@ant-design/icons';
import store from '../../store';

const { Header, Content, Footer, Sider } = Layout;
const _ = require('lodash');

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = store.getState()
        this.state.collapsed = false;
        this.changeUser = this.changeUser.bind(this);
        store.subscribe(this.onChangeState);
        if (_.isEmpty(getToken())) {
            this.props.history.push('/login')
        }
        this.logout = this.logout.bind(this);
    }

    componentDidMount = () => {
        store.dispatch(queryUserAccordingToken())
    }

    logout = () => {
        store.dispatch(toLogout(), () => {
            window.location.reload();
        });
    }

    toggle = () => {
        this.setState({
          collapsed: !this.state.collapsed,
        });
    };

    changeUser = () => {
        const { SubMenu } = Menu;
        let rightContainer = [];
        if (this.state.hasOwnProperty('user')) {
            rightContainer.push(
                <Menu key="user" mode="horizontal">
                    <SubMenu
                        title={
                            <Fragment>
                                <span style={{ color: '#999', marginRight: 4 }}>
                                </span>
                                <span>{this.state.base.user.account}</span>
                            </Fragment>
                        }
                    >
                        <Menu.Item key="SignOut" onClick={() => this.logout()}>
                            登出
                        </Menu.Item>
                    </SubMenu>
                </Menu>
            )
        }

        return rightContainer;
    }

    onChangeState = () => {
        this.setState(store.getState);
    }

    render() { 
        const { SubMenu } = Menu;
        return ( 
            <Router>
                <Layout>
                    <Sider trigger={null} collapsible collapsed={this.state.collapsed}>
                        <div className="logo"><CloudSyncOutlined style={{marginRight: '10px'}}/><span style={this.state.collapsed ? {visibility: 'hidden'} : {visibility: 'visible'}}>bsk后台管理系统</span></div>
                        <div style={{height: 'calc(100vh - 64px)'}}>
                            <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
                                <SubMenu
                                    title={
                                        <Fragment>
                                            <CloudSyncOutlined style={{marginRight: '10px'}}/>
                                            <span>系统管理</span>
                                        </Fragment>
                                    }
                                >
                                <Menu.Item key="1" icon={<UserOutlined />}>
                                    用户管理
                                </Menu.Item>
                                <Menu.Item key="2" icon={<VideoCameraOutlined />}>
                                    角色管理
                                </Menu.Item>
                                <Menu.Item key="3" icon={<UploadOutlined />}>
                                    权限管理
                                </Menu.Item>
                                </SubMenu>

                            </Menu>
                        </div>
                    </Sider>
                    <Layout>
                        <Header className="site-layout-background" style={{ padding: 0,display: 'inline-flex', width: 'calc(100%)' }}>
                            { React.createElement(this.state.collapsed ? MenuUnfoldOutlined : MenuFoldOutlined, {
                                className: 'trigger',
                                onClick: this.toggle,
                            })}
                            <div style={{display: 'flex', alignItems: 'center', position: 'absolute', right: '50px'}}>
                                {this.changeUser()}
                            </div>
                        </Header>
                        <Content>
                            <div className='site-layout-content'>
                                <Route path='/login' component={Login} />
                                <Route path='/register' component={Register} />
                            </div>
                        </Content>
                        <Footer className='site-layout-foot'>
                            <div style={{textAlign: 'center'}}>
                                bsk ©2020 Created by jw
                            </div>
                        </Footer>
                    </Layout>
                </Layout>
            </Router>
        );
    }
}
 
export default Home;