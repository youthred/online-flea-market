Vue.createApp({
    data() {
        return {
            disabled: true,
            searchQ: '',
        }
    },
    watch: {
        searchQ(n, o) {
            String.isBlank(n) ? this.disabled = true : this.disabled = false
        }
    }
}).mount('#indexSearchVueApp')