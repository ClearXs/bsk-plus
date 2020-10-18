import _ from 'lodash';

export function getToken() {
    return sessionStorage.getItem('X-Authorization-access_token')
}

export function saveToken(token) {
    sessionStorage.setItem('X-Authorization-access_token', token);
}

export function destroy() {
    sessionStorage.setItem('X-Authorization-access_token', '');
}

export function isLogin() {
    if (!_.isEmpty(getToken())) {
        return true;
    } else {
        return false;
    }
}

