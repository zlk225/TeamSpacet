/**
 * author： zhanglk
 * time：2017-12-25
 */
define(['jquery', 'socketio','configyml'], function($, io){

    var doSocketAPI = function(user,roomid,finalCallback){
        var socket_host = global_config["host"];
        var socket_real_host = global_config["socket-real-ip"];
        var socket_port = global_config["port"];
        var system_id = global_config["system-id"];
        var description = user.description || (new Date()).getTime();
        var real_roomid = "appId_" + system_id + "_"+roomid;

        var socket_url = "";
        if(socket_host == socket_real_host){
            socket_url = "http://" + socket_host + ":" + socket_port + "?user_code=" + user.user_code + "&user_name=" + user.user_name + "&roomid=" + real_roomid + "&description=" + description;
        }else{ //访问地址与真实ip地址不同，说明访问地址为域名，不再追加端口
            socket_url = "http://" + socket_host + "?user_code=" + user.user_code + "&user_name=" + user.user_name + "&roomid=" + real_roomid + "&description=" + description;
        }
        console.log(socket_url);
        var socket = io.connect(socket_url);
        socket.on('connect',function() {   //建立连接
            socket.emit('join info', real_roomid); //加入房间
            console.log("Client has connected to the server!");
        });
        socket.on('disconnect',function() {
            console.log("The client has disconnected!");
            socket.disconnect();
        });

        var res = {
            on: function(_callbackFun) {     //默认 broadcast event 事件监听
                socket.on('broadcast event', function(data) {
                    if (_callbackFun) _callbackFun(data);
                });
            },
            onByEvent: function(eventname, _callbackFun) {   //指定 eventname 事件名称监听
                socket.on(eventname, function(data) {
                    if (_callbackFun) _callbackFun(data);
                });
            },
            emit: function(_data) {
                var args = arguments;
                if (args.length > 1) {
                    var new_msg = assembleMessage(args[1],user);
                    socket.emit(args[0], new_msg);
                } else {
                    var new_msg = assembleMessage(args[0],user);
                    socket.emit('broadcast event', new_msg);
                }
            },
            disconnect: function () {
                socket.disconnect();
            }
        };
        if(typeof finalCallback === "function"){
            finalCallback(res);
        } else{
            return res;
        }
    };

    //组装消息体
    var assembleMessage = function (msg,user){
        var new_msg = {
            publisher:user.user_code,       //消息发送人
            type:"noaction",        //消息类型，可根据type调用后台不同的业务操作
            content:"",         //消息内容
            time:(new Date()).getTime()       //发送消息的时间
        };
        if(typeof msg === "object"){
            new_msg.publisher = msg.publisher || new_msg.user_code;
            new_msg.type = msg.type || new_msg.type;
            new_msg.content = msg.content || msg;
            new_msg.time = msg.time || new_msg.time;

        }else {
            new_msg.content = msg;
        }
        return new_msg;
    }


    return {
        doSocketAPI: doSocketAPI
    }
});