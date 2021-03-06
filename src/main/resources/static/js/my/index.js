Vue.createApp({
    data() {
        return {
            usedBookTypes: [],
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
                    },
                    {
                        key: "type",
                        value: 'USED_BOOK_TYPE',
                        type: "eq"
                    },
                    {
                        key: "typeCode",
                        value: '000',
                        type: "eq"
                    }
                ]
            },
            usedTypeSelected: '000',
            searchInput: ''
        }
    },
    mounted() {
        this.setUsedBookTypesSelector()
        this.setGoodsPage()
    },
    methods: {
        setUsedBookTypesSelector() {
            axios.get('/goods/usedBookTypes').then(res => {
                if (res.data.success) {
                    this.usedBookTypes = res.data.data;
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
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
    },
    watch: {
        'usedTypeSelected'(n, o) {
            this.goodsPageRequest.queryOptions[2].value = n
            this.setGoodsPage()
        }
    }
}).mount('#goodsIndexVueApp')