const goodsApp = Vue.createApp({
    data() {
        return {
            goodsTypeCode: -1,
            goodsTypeOptions: [],
            goodsPage: {},
            goodsPageRequest: {
                page: {
                    current: 1,
                    size: 30
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
            axios.get("/goods/goodsTypes").then(res => {
                if (res.data.success) {
                    let $goodsTypesSelector = $('#goodsTypesSelector');
                    let goodsTypes = res.data.data;
                    if (goodsTypes.length > 0) {
                        let goodsTypeForFirst = goodsTypes[0];
                        this.goodsTypeCode = goodsTypeForFirst.code
                        this.goodsPageRequest.queryOptions[0].value = goodsTypeForFirst.code
                        this.goodsTypeOptions = goodsTypes;
                        // $.each(goodsTypes, (index, item) => {
                        //     $goodsTypesSelector.append(`<option value="${item.code}">${item.desc}</option>`)
                        // })
                        this.setGoodsPage()
                    }
                } else {
                    alert(res.data.message)
                }
            }).catch(err => {
                alert(err)
            })
        },
        setGoodsPage() {
            axios.post("/goods/page", this.goodsPageRequest).then(res => {
                if (res.data.success) {
                    this.goodsPage = res.data.data;
                } else {
                    alert(res.data.message)
                }
            }).catch(err => {
                alert(err)
            })
        }
    }
}).mount('#goods')
goodsApp.init()