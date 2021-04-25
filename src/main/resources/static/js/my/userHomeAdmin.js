Vue.createApp({
    data() {
        return {
            response: {
                goodsManagement: {
                    report: {
                        page: {}
                    }
                },
                userManagement: {
                    user: {
                        page: {},
                        boundRoles: []
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
                },
                userManagement: {
                    user: {
                        page: {
                            page: {
                                current: 1,
                                size: 10
                            },
                            queryOptions: [
                            ]
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
            this.setUserManagementUserPage()
        },

        // region goodsManagement report
        setGoodsManagementReportPage() {
            axios.post('/home/admin/goods/report/page', this.request.goodsManagement.report.page).then(res => {
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
            axios.put(`/home/admin/goods/report/review/${this.request.goodsManagement.report.review.goodsReport.tbId}/${this.request.goodsManagement.report.review.pass}`).then(res => {
                if (res.data.success) {
                    $('#goodsManagementReportReviewModal').modal('hide')
                    this.setGoodsManagementReportPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        // endregion

        // region userManagement user
        setUserManagementUserPage() {
            axios.post('/home/admin/user/user/page', this.request.userManagement.user.page).then(res => {
                if (res.data.success) {
                    this.response.userManagement.user.page = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        userManagementUserPagePrev() {
            this.request.userManagement.user.page.page.current --
            this.setUserManagementUserPage()
        },
        userManagementUserPageNext() {
            this.request.userManagement.user.page.page.current ++
            this.setUserManagementUserPage()
        },
        userResetPassword(userTbId) {
            axios.put(`/home/admin/user/user/resetPassword/${userTbId}`).then(res => {
                if (res.data.success) {
                    Swal.fire('', '密码重置成功', 'success')
                    this.setUserManagementUserPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        userEnableOrDisable(userTbId, enableState) {
            axios.put(`/home/admin/user/user/enableOrDisable/${userTbId}/${enableState}`).then(res => {
                if (res.data.success) {
                    this.setUserManagementUserPage()
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        showUserRoleBindModal(userTbId) {
            axios.get(`/home/admin/user/user/boundRoles/${userTbId}`).then(res => {
                if (res.data.success) {
                    this.response.userManagement.user.boundRoles = res.data.data
                } else {
                    Swal.fire('', res.data.message, 'error')
                }
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
            $('#userRoleBindModal').modal('show')
        },
        userRoleBind() {
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