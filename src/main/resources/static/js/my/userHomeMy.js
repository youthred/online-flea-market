let userHomeMyVueApp = Vue.createApp({
    data() {
        return {
            common: {
                soldOrBought: {
                    goodsSnapshot: {}
                },
                buyerOrSeller: {
                    isBuyer: true,
                    info: {}
                }
            },
            response: {
                posted: {           // 我发布的
                    postedPage: {},
                    cityTree: {
                        data: {
                            name: "请选择",
                            radioDisabled: true,
                            children: []
                        }
                    }
                },
                sold: {             // 我卖出的
                    soldPage: {}
                },
                bought: {              // 我买到的
                    boughtPage: {}
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
                    offShelf: '0',
                    postedRequest: {
                        page: {
                            current: 1,
                            size: 10
                        },
                        queryOptions: [
                            {
                                key: "offShelf",
                                value: '0',
                                type: "eq"
                            }
                        ]
                    },
                    saveOrUpdateGoods: {
                        tbId: '',
                        desc: '',
                        pics: '',
                        price: '',
                        cityId: '',
                        cityName: '未选择'
                    }
                },
                sold: {
                    soldPage: {
                        page: {
                            current: 1,
                            size: 10
                        }
                    }
                },
                bought: {
                    boughtPage: {
                        page: {
                            current: 1,
                            size: 10
                        }
                    }
                },
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
            // posted
            this.setPostedPage()
            this.setCityTree()

            // sold
            this.setSoldPage()

            // bought
            this.setBoughtPage()

            // privateChat
            this.setPrivateChats()
        },

        // regine common
        /**
         * 商品快照
         */
        soldOrBoughtGoodsSnapshot(goods) {
            this.common.soldOrBought.goodsSnapshot = goods
            $('#soldOrBoughtGoodsSnapshotModal').modal('show')
        },
        // endregion

        // region 我发布的 posted
        setPostedPage() {
            axios.post('/home/my/posted/page', this.request.posted.postedRequest).then(res => {
                if (res.data.success) {
                    this.response.posted.postedPage = res.data.data
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
            axios.put(`/goods/offShelf/${goodsTbId}`).then(res => {
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
            axios.put(`/goods/onShelf/${goodsTbId}`).then(res => {
                if (res.data.success) {
                    this.setPostedPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        // 商品新增/修改请求体重新设置为默认值
        defaultSaveOrUpdateGoods() {
            this.request.posted.saveOrUpdateGoods.tbId = ''
            this.request.posted.saveOrUpdateGoods.cityId = ''
            this.request.posted.saveOrUpdateGoods.desc = ''
            this.request.posted.saveOrUpdateGoods.pics = `https://source.unsplash.com/random?rd=${Math.random()}`
            this.request.posted.saveOrUpdateGoods.price = ''
            this.request.posted.saveOrUpdateGoods.cityName = '未选择'
            this.cancelCityTreeRadioChecked()
        },
        // 设置商品新增/修改请求体
        setSaveOrUpdateGoods(goods) {
            this.request.posted.saveOrUpdateGoods.tbId = goods.tbId
            this.request.posted.saveOrUpdateGoods.desc = goods.desc
            this.request.posted.saveOrUpdateGoods.price = goods.price
            this.request.posted.saveOrUpdateGoods.pics = goods.pics
            this.request.posted.saveOrUpdateGoods.cityId = goods.cityId
            this.request.posted.saveOrUpdateGoods.cityName = goods.cityName
        },
        // 取消cityTree已选中
        cancelCityTreeRadioChecked() {
            $('input:radio[name=cityId]:checked').prop('checked',false)
        },
        postNewGoods() {
            axios.post('/goods/save', this.request.posted.saveOrUpdateGoods).then(res => {
                if (res.data.success) {
                    Swal.fire('', '新增成功', 'success')
                    $('#saveOrUpdateGoodsModal').modal('hide')
                    this.defaultSaveOrUpdateGoods()
                    this.setPostedPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        async showUpdateGoodsModal(goods) {
            // this.defaultSaveOrUpdateGoods()
            // 同步执行，等待cityName赋值完成后再执行下一步
            await axios.get(`/city/${goods.cityId}`).then(res => {
                if (res.data.success) {
                    goods.cityName = res.data.data.name
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
            this.setSaveOrUpdateGoods(goods)
            // cityTree选中
            this.cancelCityTreeRadioChecked()
            $(`#${goods.cityId}`).prop('checked', true)
            $('#saveOrUpdateGoodsModal').modal('show')
        },
        updateGoods() {
            axios.put('/goods/update', this.request.posted.saveOrUpdateGoods).then(res => {
                if (res.data.success) {
                    Swal.fire('', '修改成功', 'success')
                    $('#saveOrUpdateGoodsModal').modal('hide')
                    this.setPostedPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        deleteGoods(goodsTbId) {
            axios.delete(`/goods/delete/${goodsTbId}`).then(res => {
                if (res.data.success) {
                    Swal.fire('', '删除成功', 'success')
                    this.setPostedPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        setCityTree() {
            axios.get('/city/treeDeep1').then(res => {
                if (res.data.success) {
                    this.response.posted.cityTree.data.children = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        // endregion

        // region 我卖出的 sold
        setSoldPage() {
            axios.post('/home/my/sold/page', this.request.sold.soldPage).then(res => {
                if (res.data.success) {
                    this.response.sold.soldPage = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        soldPagePrev() {
            this.request.sold.soldPage.page.current --
            this.setSoldPage()
        },
        soldPageNext() {
            this.request.sold.soldPage.page.current ++
            this.setSoldPage()
        },
        /**
         * 买家信息
         */
        buyerInfo(buyer) {
            this.common.buyerOrSeller.isBuyer = true
            this.common.buyerOrSeller.info = buyer
            $('#buyerOrSellerModal').modal('show')
        },
        // endregion

        // region 我买到的 bought
        setBoughtPage() {
            axios.post('/home/my/bought/page', this.request.sold.soldPage).then(res => {
                if (res.data.success) {
                    this.response.bought.boughtPage = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        boughtPagePrev() {
            this.request.bought.boughtPage.page.current --
            this.setBoughtPage()
        },
        boughtPageNext() {
            this.request.bought.boughtPage.page.current ++
            this.setBoughtPage()
        },
        /**
         * 卖家信息
         */
        sellerInfo(seller) {
            this.common.buyerOrSeller.isBuyer = false
            this.common.buyerOrSeller.info = seller
            $('#buyerOrSellerModal').modal('show')
        },
        // endregion

        // region 我的私聊 privateChat
        setPrivateChats() {
            axios.get('/home/my/privateChat/chats').then(res => {
                if (res.data.success) {
                    this.response.privateChat.chats = res.data.data
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
})

userHomeMyVueApp.component("city-tree", {
    template: `
        <li>
            <div class="form-check form-check-inline">
                <input
                    class="form-check-input"
                    type="radio"
                    name="cityId"
                    :id="item.id"
                    :value="item.id"
                    @click="cityRadioBind(item)"
                    :disabled="item.radioDisabled">
                <label class="form-check-label" :for="item.id"
                    :class="{bold: isFolder}"
                    @click="toggle">
                    {{item.name}}
                    <span v-if="isFolder">[{{ isOpen ? '-' : '+' }}]</span>
                </label>
            </div>
            <ul v-show="isOpen" v-if="isFolder">
                <city-tree
                    class="item"
                    v-for="(child, index) in item.children"
                    :key="index"
                    :item="child"
                ></city-tree>
            </ul>
        </li>
    `,
    props: {
        item: Object
    },
    data: function() {
        return {
            isOpen: false
        }
    },
    computed: {
        isFolder: function() {
            return this.item.children && this.item.children.length
        }
    },
    methods: {
        toggle: function() {
            if (this.isFolder) {
                this.isOpen = !this.isOpen
            }
        },
        cityRadioBind: function (node) {
            vm.request.posted.saveOrUpdateGoods.cityId = node.id
            vm.request.posted.saveOrUpdateGoods.cityName = node.name
        }
    }
})

let vm = userHomeMyVueApp.mount('#userHomeMyVueApp')