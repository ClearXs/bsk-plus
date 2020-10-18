import React, { Component } from 'react';
import './register.css'
import { PageHeader, Form, Row, Col, Button, Input, Select, DatePicker, Checkbox } from 'antd';
import { UserOutlined, LockOutlined, MailOutlined, QqOutlined, WechatOutlined, WeiboOutlined  } from '@ant-design/icons';

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        const formItemLayout = {
            labelCol: {
              xs: { span: 10 },
              sm: { span: 6 },
            },
            wrapperCol: {
              xs: { span: 20 },
              sm: { span: 16 },
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
              xs: {
                span: 8,
                offset: 10,
              },
              sm: {
                span: 16,
                offset: 6,
              },
            },
        };
        const { Option } = Select;
        const prefixSelector = (
            <Form.Item name="prefix" noStyle>
              <Select style={{ width: 70 }}>
                <Option value="86">+86</Option>
                <Option value="87">+87</Option>
              </Select>
            </Form.Item>
        );
        return ( 
            <div className='register'>
                <div>
                    <PageHeader onBack={() => {this.props.history.push('/login')}} className='site-page-header' title='bsk系统'></PageHeader>
                </div>
                <div className='registerForm'>
                    <Form {...formItemLayout}>
                        <Form.Item label='账号'  name='account' rules={[{required: true, message: '请输入账号!'}]}>
                            <Input prefix={<UserOutlined className='site-form-item-icon'/>} placeholder='账号'/>
                        </Form.Item>
                        <Form.Item label='密码'  name='password' rules={[{required: true, message: '请输入密码!'}]}>
                            <Input prefix={<LockOutlined className='site-form-item-icon'/>} type='password' placeholder='密码'/>
                        </Form.Item>
                        <Form.Item label='确认密码'  name='confirmPassword' rules={[{required: true, message: '请输入确认密码!'}]}>
                            <Input  prefix={<LockOutlined className='site-form-item-icon'/>} type='password' placeholder='确认密码'/>
                        </Form.Item>
                        <Form.Item label='昵称'  name='nickName' rules={[{required: true, message: '请输入昵称!'}]}>
                            <Input  prefix={<UserOutlined className='site-form-item-icon'/>} placeholder='昵称'/>
                        </Form.Item>
                        <Form.Item label='邮箱'  name='email' rules={[{required: true, message: '请输入邮箱地址!'}]}>
                            <Input  prefix={<MailOutlined className='site-form-item-icon'/>} placeholder='邮箱'/>
                        </Form.Item>
                        <Form.Item name="phone" label="电话号码" rules={[{ required: true, message: '请输入电话号码!' }]}>
                            <Input addonBefore={prefixSelector} style={{ width: '100%' }} />
                        </Form.Item>
                        <Form.Item label='生日'  name='birthday' rules={[{required: true, message: '请输入生日日期!'}]}>
                            <DatePicker />
                        </Form.Item>
                        <Form.Item label="验证码" rules={[{required: true}]}>
                            <Row gutter={8}>
                                <Col span={10}>
                                    <Input placeholder='验证码'/>
                                </Col>
                                <Col span={6}>
                                    <Button>短信验证码</Button>
                                </Col>
                            </Row>
                        </Form.Item>

                        
                        <Form.Item
                            name="agreement"
                            valuePropName="checked"
                            rules={[{ validator:(_, value) => value ? Promise.resolve() : Promise.reject('Should accept agreement') },]}{...tailFormItemLayout}
                        >
                            <Checkbox>
                               我已经阅读 <a href="">同意</a>
                            </Checkbox>
                            <Button type="primary" htmlType="submit">
                                注册
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
                <div className='registerFoot'>
                    <div className='footFont'>
                        <hr style={{width: '10%'}}></hr>
                        <label style={{fontSize: '12px', color: '#999999'}}>社交账号注册</label>
                        <hr style={{width: '10%'}}></hr>
                    </div>
                    <div className='footIcon'>
                        <a><QqOutlined /></a>
                        <a><WechatOutlined /></a>
                        <a><WeiboOutlined /></a>
                    </div>
                </div>
            </div>
         );
    }
}
 
export default Register;