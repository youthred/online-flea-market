Vue.createApp({
    data() {
        return {
            response: {
                goodsManagement: {
                    report: {
                        page: {}
                    }
                }
            },
            request: {
                goodsManagement: {
                    report: {
                        reviewed: '0',
                        page: {
                            page: {
                                current: 1,
                                size: 10
                            },
                            queryOptions: [
                                {
                                    key: "reviewed",
                                    value: '0',
                                    type: "eq"
                                }
                            ]
                        },
                        review: {
                            pass: false,
                            goods: {},
                            goodsReport: {}
                        }
                    }
                }
            }
        }
    },
    mounted() {
        this.init()
    },
    methods: {
        init() {
            this.setGoodsManagementReportPage()
        },

        // region goodsManagement report
        setGoodsManagementReportPage() {
            axios.post('/home/admin/goodsManagement/report/page', this.request.goodsManagement.report.page).then(res => {
                if (res.data.success) {
                    this.response.goodsManagement.report.page = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        goodsManagementReportPagePrev() {
            this.request.goodsManagement.report.page.page.current --
            this.setGoodsManagementReportPage()
        },
        goodsManagementReportPageNext() {
            this.request.goodsManagement.report.page.page.current ++
            this.setGoodsManagementReportPage()
        },
        showGoodsManagementReportReviewModal(goodsReport) {
            axios.get(`/goods/${goodsReport.goodsTbId}`).then(res => {
                if (res.data.success) {
                    this.request.goodsManagement.report.review.goods = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
            this.request.goodsManagement.report.review.goodsReport = goodsReport
            $('#goodsManagementReportReviewModal').modal('show')
        },
        goodsManagementReportReview() {
            axios.put(`/home/admin/goodsManagement/report/review/${this.request.goodsManagement.report.review.goodsReport.tbId}/${this.request.goodsManagement.report.review.pass}`).then(res => {
                if (res.data.success) {
                    this.response.goodsManagement.report.page = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        // endregion
    },
    watch: {
        'request.goodsManagement.report.reviewed'(n, o) {
            this.request.goodsManagement.report.page.queryOptions[0].value = n
            this.setGoodsManagementReportPage()
        }
    }
}).mount('#userHomeAdminVueApp')