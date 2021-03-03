const goodsApp = Vue.createApp({
    data() {
        return {
            goodsTypeOptions: [],
            goodsPage: {},
            goodsPageRequest: {
                page: {
                    current: 1,
                    size: 10
                },
                queryOptions: [
                    {
                        key: "goodsTypeCode",
                        value: -1,
                        type: "eq"
                    }
                ]
            }
        }
    },
    methods: {
        init() {
            axios.get('/goods/goodsTypes').then(res => {
                if (res.data.success) {
                    let $goodsTypesSelector = $('#goodsTypesSelector');
                    let goodsTypes = res.data.data;
                    if (goodsTypes.length > 0) {
                        this.goodsPageRequest.queryOptions[0].value = goodsTypes[0].code
                        this.goodsTypeOptions = goodsTypes;
                        // $.each(goodsTypes, (index, item) => {
                        //     $goodsTypesSelector.append(`<option value="${item.code}">${item.desc}</option>`)
                        // })
                        this.setGoodsPage()
                    }
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
        onGoodsTypeChange() {
            this.goodsPageRequest.page.current = 1
            this.setGoodsPage()
        },
        prev() {
            this.goodsPageRequest.page.current --;
            this.setGoodsPage()
        },
        next() {
            this.goodsPageRequest.page.current ++;
            this.setGoodsPage()
        }
    }
}).mount('#goodsIndexVueApp')
goodsApp.init()