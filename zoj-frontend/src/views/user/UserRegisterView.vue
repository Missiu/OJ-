<template>
  <div id="userRegisterView">
    <h2 style="margin-bottom: 16px">用户注册</h2>
    <div class="RegisterForm">
      <a-form
        style="max-width: 480px; margin: 0 auto"
        label-align="left"
        auto-label-width
        :model="form"
        @submit="handleSubmit"
      >
        <a-form-item field="userAccount" label="账号">
          <a-input v-model="form.userAccount" placeholder="请输入账号" />
        </a-form-item>

        <a-form-item
          field="userPassword"
          tooltip="密码不少于 8 位"
          label="密码"
        >
          <a-input-password
            v-model="form.userPassword"
            placeholder="请输入密码"
          />
        </a-form-item>

        <a-form-item
          field="checkPassword"
          tooltip="确认二次密码一致"
          label="确认密码"
        >
          <a-input-password
            v-model="form.checkPassword"
            placeholder="请再次输入密码"
            @blur="validatePassword"
          />
        </a-form-item>

        <div style="margin-bottom: 10px">
          <a href="/user/login" style="float: right; margin-top: 8px"
            >已有账号？ 去登录
          </a>
        </div>

        <a-form-item>
          <a-button type="primary" html-type="submit" style="width: 120px">
            注册
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from "vue";
import { UserControllerService, UserRegisterRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

/**
 * 表单信息
 */
const form = reactive({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
} as UserRegisterRequest);

const router = useRouter();
const store = useStore();

/**
 * 提交表单
 * @param data
 */
const handleSubmit = async () => {
  if (form.userPassword !== form.checkPassword) {
    message.error("两次输入的密码不一致");
    return;
  }

  const res = await UserControllerService.userRegisterUsingPost(form);
  // 注册成功，跳转到登录页面
  if (res.code === 0) {
    await store.dispatch("user/getRegisterUser");
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    message.error("注册失败，" + res.message);
  }
};

/**
 * 检查两次密码是否一致
 */
const validatePassword = () => {
  if (form.checkPassword !== form.userPassword) {
    message.error("两次输入的密码不一致");
  }
};
</script>

<style>
.RegisterForm {
  margin-top: 5%;
}
</style>
