import React, { Component } from 'react';
import { Form, Input, Checkbox, Button, PageHeader, Row, Col } from 'antd';
import { UserOutlined, LockOutlined, QqOutlined, WechatOutlined, WeiboOutlined } from '@ant-design/icons';
import './login.css';
import store from '../../store';
import { toLogin } from '../../store/actionCreators'

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
        this.login = this.login.bind(this);
        store.subscribe(this.onChangeState);
    }

    login = (values) => {
        store.dispatch(toLogin({
            username: values.account,
            password: values.password,
            rememberMe: values.remember ? values.remember : false
        }, () => {
            this.props.history.push('/')
        }));
    }

    onChangeState = () => {
        this.setState(store.getState);
    }

    render() { 
        return (
            <div className='login'>
                <div>
                    <div className='loginTop'>
                        <PageHeader className='site-page-header' title='bsk系统'></PageHeader>
                    </div>
                    <div className='loginForm'>
                        <Form onFinish={(values) => this.login(values)}>
                            <Form.Item label='账号' name='account' rules={[{required: true, message: '请输入账号!'}]} >
                                <Row>
                                    <Col span={22}>
                                        <Input className='loginInput' prefix={<UserOutlined className='site-form-item-icon'/>} placeholder='账号'/>
                                    </Col>
                                </Row>
                            </Form.Item>
                            <Form.Item label='密码' name='password' rules={[{required: true, message: '请输入密码!'}]}>
                                <Row>
                                    <Col span={22}>
                                        <Input prefix={<LockOutlined className='site-form-item-icon'/>} type='password' placeholder='密码'/>
                                    </Col>
                                </Row>
                            </Form.Item>
                            <Form.Item name="remember" valuePropName="checked" style={{textAlign: 'center'}}>
                                <Checkbox>记住我</Checkbox>
                                <a className="login-form-forgot" href="">
                                    忘记密码?
                                </a>
                            </Form.Item>
                            <Form.Item style={{textAlign: 'center'}}>
                                <Button type="primary" htmlType="submit" className="login-form-button">
                                    登录
                                </Button>
                                
                                <Button type="default" className="login-form-button" onClick={() => {this.props.history.push('/register')}}>
                                    注册
                                </Button>
                            </Form.Item>
                        </Form>
                    </div>
                    <div className='loginFoot'>
                        <div className='footFont'>
                            <hr style={{width: '10%'}}></hr>
                            <label style={{fontSize: '12px', color: '#999999'}}>社交账号登录</label>
                            <hr style={{width: '10%'}}></hr>
                        </div>
                        <div className='footIcon'>
                            <a><QqOutlined /></a>
                            <a><WechatOutlined /></a>
                            <a><WeiboOutlined /></a>
                        </div>
                    </div>
                </div>
            </div>
         );
    }
}
 
export default Login;