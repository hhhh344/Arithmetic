<template>
    <div class="box">
        <el-container>
            <el-header height="100px" ref="header">    
                <el-row :gutter="20">
                    <el-col :span="5"><div>
                        <el-upload
                            ref="upload"
                            class="upload-demo"
                            action="/api/upload"
                            :data="{filename: 'UploadExpressionsFile.txt'}"
                            :before-remove="beforeRemove"
                            :limit="1"
                            :on-exceed="handleExceed"
                            accept=".txt"
                            :file-list="fileList"
                            :on-remove="handleRemove"
                            :on-success="handleSuccess">
                            <el-button type="success" round>上传表达式</el-button>
                        </el-upload>
                    </div></el-col>
                    <el-col :span="5"><div>
                        <el-upload
                            ref="upload"
                            class="upload-demo"
                            action="/api/upload"
                            :data="{filename: 'UploadAnswersFile.txt'}"
                            :before-remove="beforeRemove"
                            :limit="1"
                            :on-exceed="handleExceed"
                            accept=".txt"
                            :file-list="fileList"
                            :on-remove="handleRemove"
                            :on-success="handleSuccess">
                            <el-button type="success" class="uploadAns" round>上传答案</el-button>
                        </el-upload>
                    </div></el-col>
                    <el-col :span="14"><div>
                        <el-button type="warning" round :disabled="count!=2" @click="verify">校验答案</el-button>
                    </div></el-col>
                </el-row>
            </el-header>
            <el-main>
                <el-table
                    :data="tableList"
                    height="100%"
                    border>
                    <el-table-column
                        prop="num"
                        label="题号"
                        width="100"
                        sortable>
                    </el-table-column>
                    <el-table-column
                        prop="expression"
                        label="表达式"
                        >
                    </el-table-column>
                    <el-table-column
                        prop="answer"
                        label="文件答案"
                        width="150">
                    </el-table-column>
                    <el-table-column
                        prop="result"
                        label="正确答案"
                        width="150">
                    </el-table-column>
                    <el-table-column
                        prop="correctness"
                        label="正确性"
                        width="100"
                        sortable>
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
            fileList: []
        }
    },
    methods: {
        ...mapMutations({
            setCount: 'ADD',
            setTableList: 'SET_TABLE_LIST'
        }),
        handleRemove() {
            this.setCount(-1);
            console.log(this.count)
        },
        handleSuccess() {
            this.setCount(1);
            console.log(this.count)
        },
        beforeRemove() { 
        },
        handleExceed() {
            this.$notify.info({
                title: '操作失败',
                message: '只能上传一个文件'
            })
        },
        verify() {
            this.axios.get('/api/verify').then((res) => {
                this.setTableList(res.data)
            })
        }
    },
    computed: {
        ...mapGetters([
            'count',
            'tableList'
        ])
    }
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
}

.el-row {
    width: 800px;
}

.el-row, .el-col, .el-col>div {
    height: 100%;
}

.el-container {
    height: 100%;
    min-width: 800px;
}

.el-header {
    position: relative;
    padding: 0 20px;
}

.el-button {
    width: 120px;
    margin-top: 10px;
}

.el-main {
    padding: 0 20px;
}
</style>