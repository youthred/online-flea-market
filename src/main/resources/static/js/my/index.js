const goodsApp = Vue.createApp({
    data() {
        return {
            goodsPage: {},
            goodsPageRequest: {
                page: {
                    current: 1,
                    size: 10
                },
                queryOptions: [
                    {
                        key: "desc",
                        value: '',
                        type: "like"
                    }
                ]
            },
            searchInput: ''
        }
    },
    mounted() {
        this.setGoodsPage()
    },
    methods: {
        setGoodsPage() {
            axios.post('/goods/page', this.goodsPageRequest).then(res => {
                if (res.data.success) {
                    this.goodsPage = res.data.data;
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        prev() {
            this.goodsPageRequest.page.current --;
            this.setGoodsPage()
        },
        next() {
            this.goodsPageRequest.page.current ++;
            this.setGoodsPage()
        },
        search() {
            // this.goodsPageRequest.queryOptions.push({
            //     key: "desc",
            //     value: this.searchInput,
            //     type: "like"
            // })
            this.goodsPageRequest.queryOptions[0].value = this.searchInput
            this.setGoodsPage()
        }
    }
}).mount('#goodsIndexVueApp')