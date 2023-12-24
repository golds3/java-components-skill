import { defineStore } from 'pinia'
import { ref } from 'vue'

// 用户模块
export const useUserInfoStore = defineStore(
    'user-info',
    () => {
        const token = ref('') // 定义 token
        const setToken = (t) => (token.value = t) // 设置 token

        return { token, setToken }
    },
    {
        persist: true // 持久化
    }
)
