import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
    expressionsList: [],
    tableList: [],
    count: 0,
    status: true,
    canDownload: false,
}

const mutations = {
    SET_EXPRESSIONS_LIST(state, data) {
        state.expressionsList = data
    },
    ADD(state, data) {
        state.count += data
    },
    SET_STATUS(state, data) {
        state.status = data
    },
    SET_CAN_DOWNLOAD(state, data) {
        state.canDownload = data
    },
    SET_TABLE_LIST(state, data) {
        state.tableList = data
    }
}

const getters = {
    expressionsList: function(state) {
        return state.expressionsList
    },
    count: function(state) {
        return state.count
    },
    status: function(state) {
        return state.status
    },
    canDownload: function(state) {
        return state.canDownload
    },
    tableList: function(state) {
        return state.tableList
    }
}

const actions = {
    setExpressionsList({commit}, data) {
        commit('SET_EXPRESSIONS_LIST', data)
    },
    add({commit}, data) {
        commit('ADD', data)
    },
    setStatus({commit}, data) {
        commit('SET_STATUS', data)
    },
    SET_CAN_DOWNLOAD({commit}, data) {
        commit('SET_CAN_DOWNLOAD', data)
    },
    SET_TABLE_LIST({commit}, data) {
        commit('SET_TABLE_LIST', data)
    }
}

const store = new Vuex.Store({
    state,
    mutations,
    getters,
    actions
})

export default store;