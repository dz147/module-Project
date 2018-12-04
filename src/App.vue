<template>
  <div id="app">
    <div id="nav">
      <router-link to="/home">首页</router-link> |  
			<router-link to="/addTask">添加任务</router-link> | 
			<router-link to="/taskList">任务完成情况</router-link>
    </div>	
			<router-view/>
  </div>
</template>

<script>
	 export default  {
		name:'app',
		data() {
			return {
				
			}
		},
		created() {
			
		}, methods: {
      
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

</style>
