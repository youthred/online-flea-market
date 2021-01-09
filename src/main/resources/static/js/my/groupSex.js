let socket
if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket
}
axios.get("/chat/nettyHost").then(res => {
    if (res.data.success) {
        if (window.WebSocket) {
            socket = new WebSocket(res.data.data)
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
    } else if (!res.data.success) {
        alert(res.data.message)
    }
}).catch(err => {
    alert(err)
})

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
