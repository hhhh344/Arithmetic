<template>
  <div class="box">
    <el-container>
      <el-header>
        <el-menu mode="horizontal" default-active="generateExpression" :router="true">
          <el-menu-item index="generateExpression">生成表达式</el-menu-item>
          <el-menu-item index="checkAnswers">校验答案</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer><a href="http://github.com/hhhh344/Arithmetic.git" target="_blank">github.com/hhhh344/Arithmetic.git</a></el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  user-select: none;
}

.box, .el-container {
  height: 100%;
}

.el-menu-item {
  margin-left: 20px;
}

.el-footer {
  background: lightgray;
  text-align: center;
  line-height: 60px;
  color: #fff;
}

a:link { text-decoration: none; color: #fff}
a:active { text-decoration:blink}
a:hover { text-decoration:none; color: #eee}
a:visited { text-decoration: none; color: #eef}

</style>
