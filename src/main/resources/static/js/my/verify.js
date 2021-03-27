Vue.createApp({
    data() {
        return {
            verifyResult: [[${verifyResult}]]
        }
    },
    mounted() {
        console.log(this.verifyResult)
    },
    methods: {
    }
}).mount('#verifyVueApp')