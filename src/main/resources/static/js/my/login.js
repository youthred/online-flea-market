Vue.createApp({
    data() {
        return {
            user: {
                username: '',
                password: '',
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
                    alert(res.data.message)
                }
            }).catch(err => {
                alert(err)
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