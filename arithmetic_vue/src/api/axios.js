import axios from 'axios'
let url = '/api/'
let AXIOS = axios.create() //创建axios实例

export function sendMsg(type,content) {
    AXIOS.get(url+type,{
        params: {
            CONTENT: content
        }
    })
    .then(function(response) {
        return response
    })
}