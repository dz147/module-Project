<template>
	<div class="app">
		<el-table
    :data="tableData2" 
    style="width: 100%"
    :row-class-name="tableRowClassName">
    <el-table-column
      prop="id"
      label="编号"
      width="80">
    </el-table-column>
		<el-table-column
			prop="name"
			label="名称"
			width="150">
		</el-table-column>
    <el-table-column
      prop="detail"
      label="详细"
     >
    </el-table-column>
    <el-table-column
      prop="createdAt"
      label="添加时间"  width="145">
    </el-table-column>
		<el-table-column
			prop="finishedAt"
			label="计划完成日期" width="145">
		</el-table-column>
		<el-table-column
			prop="status"
			label="完成状态" width="80">
		</el-table-column>
		<el-table-column
			prop="memo"
			label="备忘录" width="140">
		</el-table-column>
		<el-table-column
			label="操作" width="80">
			<template slot-scope="scope">
				<el-button
					size="mini"
					type="danger"
					@click="handleDelete(scope.$index, scope.row)">删除</el-button>
			</template>
		</el-table-column>
  </el-table>
	</div>
</template>

<script>
	 export default  {
		name:'app',
		data() {
			return {
				tableData2: []
			}
		},
		created() {
			this.name = '添加任务';
			fetch('http://localhost:8082/todo', {
                 method: 'get'
                 //body: JSON.stringify(this.todo)
             }).then(resp => resp.json())
               .then(data => {
                  for (var i = 0; i < data.length; i++) {
										data[i].createdAt = this.timestampToTime(data[i].createdAt);
										data[i].finishedAt = this.timestampToTime(data[i].finishedAt);
                  	this.tableData2.push(data[i]);
                  }
               });
		}, methods: {
      tableRowClassName({row, rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
      },
			 timestampToTime(timestamp) {
        var date = new Date(timestamp);
				var Y = date.getFullYear() + '-';
				var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
				var D = date.getDate() + ' ';
				var h = date.getHours() + ':';
				var m = date.getMinutes() + ':';
				var s = date.getSeconds(); 
				 return Y+M+D+h+m+s;
    },handleDelete(index, row) {
        //console.log(index, row.id);//index获得当前行的下标，row获得当前行的数据
				fetch('http://localhost:8082/delTask/'+row.id, {
									method: 'delete'
							}).then(resp => resp.json())
								.then(data => {
										alert(data.msg);
										this.refresh();
								});
      },
			refresh(){
				fetch('http://localhost:8082/todo', {
									method: 'get'
									//body: JSON.stringify(this.todo)
							}).then(resp => resp.json())
								.then(data => {
									this.tableData2 = [];
										for (var i = 0; i < data.length; i++) {
											data[i].createdAt = this.timestampToTime(data[i].createdAt);
											data[i].finishedAt = this.timestampToTime(data[i].finishedAt);
											this.tableData2.push(data[i]);
										}
								});
			}
    }
	};
</script>

<style>
	
	#app {
	  font-family: 'Avenir', Helvetica, Arial, sans-serif;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	  text-align: center;
	  color: #2c3e50;
	  margin-top: 60px;
	}
.el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
