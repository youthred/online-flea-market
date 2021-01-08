let socket
if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket
}
if (window.WebSocket) {
    socket = new WebSocket('ws://youthred.cn:8888/ws')
    socket.onmessage = function (event) {
        let resText = document.getElementById('resText')
        resText.value += event.data + '\r\n'
    };
    socket.onopen = function (event) {
        let resText = document.getElementById('resText')
        resText.value = '已连接\r\n'
    };
    socket.onclose = function (event) {
        let resText = document.getElementById('resText')
        resText.value = '已关闭\r\n'
    };
} else {
    alert('您的浏览器不支持WebSocket协议！')
}

function send(msg) {
    if (!window.WebSocket) {
        return
    }
    if (socket.readyState === WebSocket.OPEN) {
        socket.send(msg)
        document.getElementById('msgInput').value = ''
    } else {
        alert('WebSocket连接建立失败！')
    }
}