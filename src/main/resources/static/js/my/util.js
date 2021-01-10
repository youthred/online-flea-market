function isNotBlank(str) {
    return str !== null && str !== undefined && !/^\s*$/.test(str)
}
function isBlank(str) {
    return !isNotBlank(str)
}