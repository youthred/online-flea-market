Vue.createApp({
    data() {
        return {
            github: {},
            commitsRequest: {
                per_page: 20,
                page: 1
            },
            commits: [],
            nextAble: true
        }
    },
    mounted() {
        this.init()
    },
    methods: {
        init() {
            this.setGithubInfo().then(r => this.setCommitsPage())
        },

        async setGithubInfo() {
            await axios.get('/common/appInfo').then(res => {
                this.github = res.data.data.github
            })
        },
        setCommitsPage() {
            axios.get(`https://api.github.com/repos/${this.github.owner}/${this.github.repo}/commits`, {
                params: this.commitsRequest
            }).then(res => {
                this.commits = res.data
                this.nextAble = Array.isNotEmpty(this.commits) && this.commits.length === this.commitsRequest.per_page
            }).catch(err => {
                Swal.fire('', err.toString(), 'error')
            })
        },
        prev() {
            this.commitsRequest.page --;
            this.setCommitsPage()
        },
        next() {
            this.commitsRequest.page ++;
            this.setCommitsPage()
        },
        getShortSha(sha) {
            return sha.substr(0, 7)
        }
    },
    watch: {
    }
}).mount('#devLogVueApp')