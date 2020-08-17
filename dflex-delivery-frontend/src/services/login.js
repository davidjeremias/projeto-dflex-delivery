export default{
    login:(login) => {
        var params = new URLSearchParams();
        params.append('grant_type', 'password');
        params.append('client', 'app-u2d-client');
        params.append('username', login.username);
        params.append('password', login.password);
        return this.$http({
            method: 'post',
            url:'oauth/token',
            auth:{username: 'app-u2d-client', password: 'u2d'},
            headers: {"Content-type": "application/x-www-form-urlencoded"},
            data: params
        })
    }
}