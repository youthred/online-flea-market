let socket
if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket
}
let $resText = $('#resText')
let $msgInput = $('#msgInput');
let $sendMsgBtn = $('#sendMsgBtn');
axios.get('/chat/nettyHost').then(res => {
    if (res.data.success) {
        if (window.WebSocket) {
            socket = new WebSocket(res.data.data)
            socket.onmessage = function (event) {
                $resText.append(`<h3><span class="badge badge-pill badge-primary">${event.data}</span></h3>`)
            };
            socket.onopen = function (event) {
                $resText.append(`<p class="text-muted text-center"><small><i class="fas fa-link"></i>${new Date().defaultFormat()}</small></p>`)
            };
            socket.onclose = function (event) {
                $resText.append(`<p class="text-muted text-center"><small><i class="fas fa-unlink"></i>${new Date().defaultFormat()}</small></p>`)
            };
        } else {
            Swal.fire('', '您的浏览器不支持WebSocket协议！', 'error')
        }
    } else {
        Swal.fire('', res.data.message, 'error')
    }
}).catch(err => {
    Swal.fire('', err.toString(), 'error')
})

function send(msg) {
    if (!window.WebSocket) {
        return
    }
    if (socket.readyState === WebSocket.OPEN) {
        socket.send(msg)
        $msgInput.val('')
        $sendMsgBtn.attr('disabled', 'disabled')
        $msgInput.focus()
    } else {
        Swal.fire('', 'WebSocket连接建立失败！', 'error')
    }
}

/**
 * 监听输入框以改变按钮可用状态
 */
$(function () {
    $msgInput.bind('input propertychange', function () {
        if (isBlank($msgInput.val())) {
            $sendMsgBtn.attr('disabled', 'disabled')
        } else {
            $sendMsgBtn.removeAttr('disabled')
        }
    })
})

/**
 * 给输入框绑定回车输入发送信息事件
 */
$(function () {
    $msgInput.bind('keypress', function (event) {
        if (event.keyCode === '13') {
            send($msgInput.val())
        }
    })
})
