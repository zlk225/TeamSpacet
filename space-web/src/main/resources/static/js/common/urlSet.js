
var T = {
    //获取url参数
    getUrlParams: function (name, str) {
        str = str || window.location.search.substr(1);
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = (str + '').match(reg);
        //	if (r!=null) return unescape(r[2]); return null;
        if (r != null) return decodeURI(r[2]);
        return null;
    },
    ajax:function (url,params,successFun, errorFun) {
        params = params || {};
        $.ajax({
            url: url,
            data: params,
            type: params.ajaxType || "get",
            dataType: params.dataType || "json",
            timeout: 100000,
            async: true,
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {
                if (typeof successFun == "function"){
                    successFun(data);
                }
            },
            error: function (data) {
                if (typeof errorFun == "function"){
                    errorFun(data);
                }
            }
        });
    },

};

var _host = "";

var urlSet = {
    httpName: _host,
    PAGE_LOGIN: _host + "login",   //前往登录页面
    PAGE_REGISTER: _host + "register",   //前往注册页面
    LOGIN: _host + "auth/login",  //登录接口
    EXIT: _host + "oauth/revoke-token",//退出接口
    PAGE_INDEX: _host + "index",   //前往首页
    //socket
    SOCKET: {
        VIEW_SOCKET: _host + "chat/viewSocket",  //socket连接列表
    }
};




