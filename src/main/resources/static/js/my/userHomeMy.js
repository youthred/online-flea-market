Vue.createApp({
    data() {
        return {
            response: {
                posted: {           // 我发布的
                    postedPage: {}
                },
                sold: {},           // 我卖出的
                got: {},            // 我买到的
                privateChat: {},    // 我的私聊
                like: {},           // 我的点赞
                comments: {}        // 我的评论
            },
            request: {
                posted: {
                    offShelf: 0,
                    postedRequest: {
                        page: {
                            current: 1,
                            size: 5
                        },
                        queryOptions: [
                            {
                                key: "offShelf",
                                value: 0,
                                type: "eq"
                            }
                        ]
                    }
                },
                sold: {},
                got: {},
                privateChat: {},
                like: {},
                comments: {}
            }
        }
    },
    mounted() {
        this.init()
    },
    methods: {
        init() {
            this.setPostedPage()
        },
        // region posted
        setPostedPage() {
            axios.post('/home/my/postedPage', this.request.posted.postedRequest).then(res => {
                if (res.data.success) {
                    this.response.posted.postedPage = res.data.data;
                    console.log(this.response.posted.postedPage)
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        postedPagePrev() {
            this.request.posted.postedRequest.page.current --
            this.setPostedPage()
        },
        postedPageNext() {
            this.request.posted.postedRequest.page.current ++
            this.setPostedPage()
        },
        offShelf(goodsTbId) {
            axios.put(`/home/offShelf/${goodsTbId}`).then(res => {
                if (res.data.success) {
                    this.setPostedPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        onShelf(goodsTbId) {
            axios.put(`/home/onShelf/${goodsTbId}`).then(res => {
                if (res.data.success) {
                    this.setPostedPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        }
        // endregion
    },
    watch: {
        'request.posted.offShelf'(n, o) {
            this.request.posted.postedRequest.queryOptions[0].value = n
            this.request.posted.postedRequest.page.current = 1
            this.setPostedPage()
        }
    }
}).mount('#userHomeMyVueApp')