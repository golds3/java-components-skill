import './assets/main.scss'
import persist from 'pinia-plugin-persistedstate'


import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(createPinia().use(persist))

app.mount('#app')
