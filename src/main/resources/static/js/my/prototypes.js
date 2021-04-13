Date.prototype.format = function (fmt) {
    const o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
    for (const k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)))
    }
    return fmt
}
Date.prototype.defaultFormat = function () {
    return this.format('yyyy-MM-dd HH:mm:ss')
}

String.isNotBlank = function (str) {
    return str !== null && str !== undefined && !/^\s*$/.test(str)
}
String.isBlank = function (str) {
    return !this.isNotBlank(str)
}

Array.isNotEmpty = function (arr) {
    return arr != null && arr.length > 0
}
Array.isEmpty = function (arr) {
    return !this.isNotEmpty(arr)
}

Object.isEmpty = function (obj) {
    return Object.keys(obj).length === 0
}
Object.isNotEmpty = function (obj) {
    return !this.isEmpty(obj)
}