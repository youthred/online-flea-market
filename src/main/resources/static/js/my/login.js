Vue.createApp({
    data() {
        return {
            user: {
                username: 'jack',
                password: 'jack',
                rememberMe: false,
                imageCaptcha: ''
            },
            imageCaptchaUrl: '/imageCaptcha',
            imageCaptchaRemainingTime: 30
        }
    },
    mounted() {
        setInterval(() => {
            this.imageCaptchaRemainingTime --
        }, 1000)
    },
    methods: {
        refreshImageCaptcha() {
            this.imageCaptchaRemainingTime = 30
            this.imageCaptchaUrl = '/imageCaptcha?rd=' + Math.random()
        },
        login() {
            axios.post('/login', Qs.stringify(this.user)).then(res => {
                if (res.data.success) {
                    $(location).attr('href', '/');
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        }
    },
    watch: {
        imageCaptchaRemainingTime(n, o) {
            if (n === -1) {
                this.refreshImageCaptcha()
            }
        }
    }
}).mount('#loginVueApp')