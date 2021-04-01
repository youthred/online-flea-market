Vue.createApp({
    data() {
        return {
            response: {
                posted: {           // 我发布的
                    postedPage: {}
                },
                sold: {             // 我卖出的
                },
                got: {              // 我买到的
                },
                privateChat: {      // 我的私聊
                    chats: []
                },
                like: {             // 我的点赞
                },
                comments: {         // 我的评论
                }
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
            this.setPrivateChats()
        },
        // region 我发布的 posted
        setPostedPage() {
            axios.post('/home/my/posted/page', this.request.posted.postedRequest).then(res => {
                if (res.data.success) {
                    this.response.posted.postedPage = res.data.data;
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
            axios.put(`/home/my/posted/offShelf/${goodsTbId}`).then(res => {
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
            axios.put(`/home/my/posted/onShelf/${goodsTbId}`).then(res => {
                if (res.data.success) {
                    this.setPostedPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        // endregion

        // region 我卖出的 sold
        // endregion

        // region 我买到的 got
        // endregion

        // region 我的私聊 privateChat
        setPrivateChats() {
            axios.get('/home/my/privateChat/chats').then(res => {
                if (res.data.success) {
                    this.response.privateChat.chats = res.data.data;
                    console.log(this.response.privateChat.chats)
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        // endregion

        // region 我的点赞 like
        // endregion

        // region 我的评论 comments
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