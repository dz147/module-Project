<template>
	<div class="hello">
		<el-form ref="form" :model="form" label-width="80px">
			<el-form-item label="任务名称">
				<el-input v-model="form.name"></el-input>
			</el-form-item>
			<el-form-item label="任务要求">
				<el-input type="textarea" v-model="form.detail"></el-input>
			</el-form-item>
			<el-form-item label="提交时间">
				<el-col :span="11">
					<el-date-picker type="date" placeholder="选择日期" v-model="form.finishedAt" style="width: 100%;"></el-date-picker>
				</el-col>
			</el-form-item>
			<el-form-item label="其它">
				<el-input type="textarea" v-model="form.memo"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="onSubmit">立即创建</el-button>
				<el-button>取消</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	 export default {
    data() {
      return {
        form: {
					name:'',
					detail: '',
					finishedAt: '',
					memo: ''
        }
      }
    },
    methods: {
      onSubmit() {
        fetch('http://localhost:8082/addTask', {
        					method: 'post',
									headers:{
										'Content-Type': 'application/json',
										'Accept':'application/json'
									},
        					body: JSON.stringify(this.form)
        			}).then(resp => resp.json())
        				.then(data => {
									this.form = {};
        						alert(data.msg);
        				});
      }
    }
	}
</script>

<style>
 .hello{
	 width: 400px;
	 height: 800px;
	 margin: auto;
 }
</style>
