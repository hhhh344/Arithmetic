<template>
    <div class="box">
        <el-container>
            <el-header>
                <el-popover
                    ref="popoverNumber"
                    placement="bottom"
                    title="输入生成个数"
                    width="120"
                    trigger="focus"
                    content="范围:1~10000">
                </el-popover>     
                <el-input v-popover:popoverNumber v-model="numberInput" placeholder="输入生成个数"></el-input>
                <el-popover
                    ref="popoverRange"
                    placement="bottom"
                    title="输入取值范围"
                    width="120"
                    trigger="focus"
                    content="范围:2~10000">
                </el-popover>
                <el-input v-popover:popoverRange v-model="rangeInput" placeholder="输入取值范围"></el-input>
                <el-button round type="primary" :disabled="!status" @click="getExpressionsList">确认</el-button>
                <el-button type="success" class="download" @click="download('Exercises.txt')" :disabled="!canDownload">下载表达式文件</el-button>
                <el-button type="success" class="download" @click="download('Answers.txt')" :disabled="!canDownload">下载答案文件</el-button>
            </el-header>
            <el-main>
                <el-table 
                    :data="expressionsList"
                    height="100%"
                    border>
                    <el-table-column
                        prop="num"
                        label="题号"
                        width="100">
                    </el-table-column>
                    <el-table-column
                        prop="expression"
                        label="表达式">
                    </el-table-column>
                    <el-table-column
                        prop="answer"
                        label="答案"
                        width="180">
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
    </div>
</template>

<script>
import {mapState, mapMutations, mapGetters} from 'vuex'
export default {
    data() {
        return {
            numberInput: '',
            rangeInput: ''
        }
    },
    methods: {
        ...mapMutations({
            setExpressionsList: 'SET_EXPRESSIONS_LIST',
            setStatus: 'SET_STATUS',
            setCanDownload: 'SET_CAN_DOWNLOAD'
        }),

        getExpressionsList() {
            if(!this.status) {
                return;
            }
            
            let that = this;
            if(!this.paramIsLegal()) {
                this.$notify.info({
                    title: '操作失败',
                    message: '输入的参数格式不正确或超出范围'
                });
                return;
            }
            this.setStatus(false);
            this.axios.get('/api/generateExpression', {
                params: {
                    number: this.numberInput,
                    range: this.rangeInput,
                }
            })
            .then(function(response) {
                if(response.data === "0") {
                    that.$notify.info({
                        title: '操作失败',
                        message: '生成表达式时出现错误'
                    })
                    that.setStatus(true);
                    return;
                }
                that.setExpressionsList(response.data);
                that.$notify.success({
                    title: '操作成功',
                    message: ''
                })
                that.setStatus(true);
                that.setCanDownload(true);
            })
            .catch(function(error) {
                console.log(error);
                that.$notify.info({
                    title: '操作失败',
                    message: '获取数据超时'
                })
                that.setStatus(true);
            })
        },

        download(val) {
            window.open("/api/download?fileName="+val)
        },
        
        paramIsLegal() {
            //必须是非0开头的数字
            if(this.numberInput.match("^[1-9][0-9]*$") && this.rangeInput.match("^[1-9][0-9]*$")) {
                if(this.numberInput > 10000 || this.rangeInput < 2 || this.rangeInput > 10000) {
                    return false;
                }
                return true;
            }
            return false;
        }
    },
    computed: {
        ...mapGetters([
            'expressionsList',
            'status',
            'canDownload'
        ])
    }
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
}

.el-header {
    position: relative;
}

.el-container {
    height: 100%;
}

.el-table {
    min-width: 750px;
}

.el-input {
    width: 120px;
    position: relative;
    top: 50%;
    transform: translateY(-50%);
    margin-left: 20px;
}

.el-button {
    position: relative;
    top: 50%;
    transform: translateY(-50%);
    margin-left: 20px;
}

.download {
    width: 120px;
    height: 40px;
}

.el-main {
    padding: 0 20px;
}
    
</style>