Vue.createApp({
    data() {
        return {
            user: {
                username: '',
                password: '',
                rePassword: '',
                nickname: '',
                email: '',
                imageCaptcha: ''
            },
            validate: {
                rePassword: ''
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
        register() {
            axios.post('/register', this.user).then(res => {
                if (res.data.success) {
                    Swal.fire({
                        title: '注册成功',
                        type: 'success',
                        html: `[模拟邮箱验证] 请点击<a href="${res.data.data}">激活链接</a>以启用账户`
                    })
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        validatePassword() {
            if (String.isNotBlank(this.user.password) && String.isNotBlank(this.user.rePassword)) {
                if (this.user.password !== this.user.rePassword) {
                    this.validate.rePassword = '两次输入密码不匹配'
                } else {
                    this.validate.rePassword = ''
                }
            }
        }
    },
    watch: {
        imageCaptchaRemainingTime(n, o) {
            if (n === -1) {
                this.refreshImageCaptcha()
            }
        },
        'user.password'(n, o) {
            this.validatePassword()
        },
        'user.rePassword'(n, o) {
            this.validatePassword()
        }
    }
}).mount('#registerVueApp')