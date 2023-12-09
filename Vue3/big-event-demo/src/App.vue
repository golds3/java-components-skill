<script setup>
import {useRoute, useRouter} from "vue-router";
import {useUserStore} from "@/stores/modules/user";

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()
const toList = ()=>{
  router.push('list')
}
</script>

<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<style scoped>

</style>
