# Vue学习之路

官网地址：https://vuejs.org/guide/introduction.html

## 1入门使用



```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>  //注意地址为国外网站 访问比较慢  可翻墙
</head>

<body>
<div id="app">
{{message}}
</div>
</body>

<script>
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})

</script>
</html>
```



## 2标签大全

### v-model

双向绑定

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   <script src="./js/vue.js"></script>
</head>

<body>
<div id="app">
<input type="text" v-model:value="message"  name="usernmae" id="useraname">
</div>
</body>

<script>
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})



</script>
</html>
```



### v-bind

单向绑定   可简写为：

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   <script src="./js/vue.js"></script>
</head>

<body>
<div id="app">
<input type="text" v-bind:value="message"  name="usernmae" id="useraname">
</div>
</body>

<script>
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})



</script>
</html>
```



### v-text

设置value值  类似于{{value}}



### v-html

与v-text不同的是 自己支持html语法的解析。

![image-20220708120554725](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220708120554725.png)



### v-cloak

在vue示例接管容器的时候才进行渲染

![image-20220708122324607](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220708122324607.png)



### v-once

获取当前元素的值 且不会在改变（一次性）

![image-20220708122517718](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220708122517718.png)



### v-pre

![image-20220708122633012](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220708122633012.png)



## 3数据代理

Objects.

```

```

## 4事件处理v-on

![image-20220701152538655](http://inis.inis1719.cn/202207011525851.png)



### 事件

```

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="../js/vue.js"></script>
    </head>

    <body>
        <div id="app">
            <button  class="demo2" @click="showinfo">common</button>
        </div>
    </body>

 <script  >
new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  },
  methods: {
    showinfo(){
       alert("1111");
  }
}
})

    </script>

    </html>


```



### 按键事件

![image-20220701154050110](http://inis.inis1719.cn/202207011540182.png)

```

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="../js/vue.js"></script>
    </head>

    <body>
        <div id="app">
            <input  class="demo2" @keyup.enter="showinfo"></input>
        </div>
    </body>

 <script  >
new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  },
  methods: {
    showinfo(e){
      console.log(e.keyCode);  //按键编码
      console.log(e.key);  //按键名
      alert(e.target.value)
  }
}
})

    </script>

    </html>

```



## 5计算属性

```

    <!DOCTYPE html>
    <html lang="en">



    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="../js/vue.js"></script>
    </head>

    <!-- 需求设计  设计三个输入框 其中2个为姓、名  第三个为他们的字符串拼接 -->

    <body>
        <div id="app">
            姓：<input class="demo1" :value="name"></input><br>
        名：<input class="demo2" :value="name2"></input><br>
        拼接：<input class="demo3" :value="fullname"></input><br>
        </div>
    </body>

    <script>
        new Vue({
    el: '#app',
            data: {
 name:" 张", 
 name2:"三" }, 
 methods: { 
    showinfo(e){
         console.log(e.keyCode); //按键编码 console.log(e.key); //按键名
            alert(e.target.value) }}, 
            
    computed:{ //计算属性
        fullname:{
        
             get(){
                debugger;
                
                return this.name+"-"+this.name2; },
              set(){ return "set" ; } } } }) 
    </script>

    </html>

```



## 6监视属性

```

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="../js/vue.js"></script>
    </head>

    <body>
        <div id="app">
            <input type="text" v-model:value="message">
            <input  class="demo2" @keyup.enter="showinfo"></input>
        </div>
    </body>

 <script  >
new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  },
  methods: {
    showinfo(e){
      console.log(e.keyCode);  //按键编码
      console.log(e.key);  //按键名
      alert(e.target.value)
  }
},
watch:{
    message:{
    
        handler(){
        immediate：true，//初始化调用
       deep：true,//监视属性多级对象变化
            alert("当前watch生效")
        }
}
}
})

    </script>

    </html>

```



测试案例 列表数据过滤

```

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="../js/vue.js"></script>
    </head>

    <body>
        <div id="app">
<input type="text"      placeholder="请输入过滤条件"  v-model="keyword">

            <ul>
         <li  v-for="(person,index)  of personsCopy"  :key="person.name">
            {{person.name}}
         </li>
        </ul>
        </div>
    </body>

 <script  >
new Vue({
  el: '#app',
    data() {
        return {
            keyword:"",
            persons:[
                {
                    name:"张三",
                    age:"20"
                },
                {
                    name:"张四",
                    age:"20"
                }
            ],
            personsCopy:[]
        }
    },
watch:{
         keyword:{
            immediate:true,
            handler(value){
            this.personsCopy=  this.persons.filter((p)=>{
                return p.name.indexOf(value)!==-1;
             })
         }}   
}
})

    </script>

    </html>

```

 

## 7过滤器属性

![image-20220708120331405](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220708120331405.png)

对函数进行加工

![image-20220708120002654](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220708120002654.png)



注册全局过滤器

![image-20220708120212873](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220708120212873.png)





## 8自定义指令