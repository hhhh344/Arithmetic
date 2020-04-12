import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import generateExpression from '@/components/generateExpression'
import checkAnswers from '@/components/checkAnswers'
Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      children: [
        {
          path: 'generateExpression',
          name: 'generateExpression',
          component: generateExpression
        },
        {
          path: 'checkAnswers',
          name: 'checkAnswers',
          component: checkAnswers
        },
        {
          path: '*',
          redirect: 'generateExpression'
        }
      ]
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
