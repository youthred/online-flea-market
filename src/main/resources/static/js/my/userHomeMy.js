Vue.createApp({
    data() {
        return {
            response: {
                posted: {},         // 我发布的
                sold: {},           // 我卖出的
                got: {},            // 我买到的
                privateChat: {},    // 我的私聊
                like: {},           // 我的点赞
                comments: {}        // 我的评论
            },
            request: {
                posted: {
                    offShelf: 0
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
    },
    methods: {
    },
    watch: {
        'request.posted.offShelf'(n, o) {
            console.log(n, o)
        }
    }
}).mount('#userHomeMyVueApp')