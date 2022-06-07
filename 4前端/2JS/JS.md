# 																		js和jquery

## 1JS初步调用

### **简介**

  介绍：**js是一种基于对象的脚本语言，它不需要经过Web服务器就可以对用户的输入做出响应，并且它不依赖于操作系统，仅需要浏览器的支持，是一类非常方便、简单、实用的语言。**



### 1.1使用步骤

#### 在html页面中加入 

```
<script  type="text/javascript">   </script>
```

![img](http://inis.inis1719.cn/202206071412568.png)

#### 在外部引入自己的js

![img](http://inis.inis1719.cn/202206071412569.png)

![img](http://inis.inis1719.cn/202206071412570.png)







### **1.2js中的变量定义**

![img](http://inis.inis1719.cn/202206071412571.png)

另外script也可以像css那样使用

其中的运算

![img](http://inis.inis1719.cn/202206071412573.png)

​                               



### **1.3  函数的定义  function**



![img](http://inis.inis1719.cn/202206071412574.png)

#### **使用方式**



![img](http://inis.inis1719.cn/202206071412575.png)



![img](http://inis.inis1719.cn/202206071412576.png)

![img](http://inis.inis1719.cn/202206071412577.png)

#### 注意事项

function函数是不允许重载的（重载就是方法名相同，参数不同）

原因：是因为function函数有默认的参数rgments

![img](http://inis.inis1719.cn/202206071412578.png)



### **1.4  js中自定义对象**

![img](http://inis.inis1719.cn/202206071412579.png)

 同样也可以new  类   使用非常简单

加入自己的属性其实就是自己的类名直接以.属性名然后赋值即可。

![img](http://inis.inis1719.cn/202206071412580.png)

2   花括号使用

![img](http://inis.inis1719.cn/202206071412581.png)

和第一个差不多  只不过赋值是在括号中







##         	2js事件



### 2.1事件举例

1. onload     页面加载完成后渲染事件
2. onblur	 页面单击事件
3. onsumbit  页面表单提交事件
4. onchange   页面内容发生改变 
5. onblue  （鼠标失去焦点事件）



####               onload事件 

（完成事件，当你的页面加载完后，会执行事件）

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>onload页面加载事件</title>
</head>
<body  onload="onloadTest()">


<script>
    function onloadTest() {
        alert("hello,我是onload事件")

    }


</script>

</body>
</html>
```

####           onclick事件   

经常用于在表单提交中

（点击事件，点击指定的区域会弹出你设置的事件）

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>onload页面加载事件</title>
</head>
<body>

<div  id="demo"  onclick="onclickDemo()">
    hello
</div>


<script>
    function onclickDemo() {
        alert("hello,我是onclick事件")

    }


</script>

</body>
</html>
```

####     onblur  失去焦点事件   

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>onblur失去焦点事件</title>
</head>
<body >
<div>
    <input  type="text"  onblur="onblurDemo()">
</div>

<script>
    function onblurDemo() {
        alert("hello,我是onblur事件")

    }
</script>



</body>
</html>
```

####           onchange事件 

 常用于下拉框

（内容发生改变，触发事件）

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>onchange内容发生改变事件</title>
</head>
<body >
<div>
    <input  type="text"  value="200"  onchange="onchangeDemo()">
</div>

<script>
    function onchangeDemo() {
        alert("hello,我是onchange事件")

    }
</script>



</body>
</html>
```

####            onsubmit事件 

常用于提交检验是否合法

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>onsubmit时间提交事件</title>
</head>
<body>

    <form  method="get"  action="www.baidu.com" onsubmit=" return  onsubDemo()">
        <button  value="hello" type="text">
    </form>


<script>
    function onsubDemo() {
        alert("hello,我是onsubDemo事件")
return false;
    }
</script>


</body>
</html>
```



记住一个点   用onsubmit事件后不合法  怎么让他不提交

就是前面加上return

 



### 2.2事件注册



#### 静态注册 

直接在html中使用

![img](http://inis.inis1719.cn/202206071412582.png)

![img](http://inis.inis1719.cn/202206071412583.png)

#### 动态注册

获取对象属性后使用

window.函数名 =  函数

![img](http://inis.inis1719.cn/202206071412584.png)





### 3document

####   

操作html的dom元素

代码：

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<input   type="text"    value="100"  id="hello"    onclick="hello()">
</body>
<script>
    function hello() {
        document.getElementById("hello").value="my  name is zhou"

    }


</script>
</html>
```

   3 .2  正则表达式

![img](http://inis.inis1719.cn/202206071412585.png)

最常见的是定义一个变量  格式为   var  xxx=/{xxx}/ 表示验证是否包含这个元素

![img](http://inis.inis1719.cn/202206071412586.png)

​           3.3    全选  反选  全不选

运用的是checked这个选项    

到最后使用遍历即可    全选是遍历为true

全不选是遍历为flase

![img](http://inis.inis1719.cn/202206071412587.png)

3.3  document获取的方法

![img](http://inis.inis1719.cn/202206071412588.png)

如果有id就可以通过id获取

如果有id就可以通过name获取

如果没有id， name就可以通过标签名获取

![img](http://inis.inis1719.cn/202206071412589.png)

 3.4   节点

![img](http://inis.inis1719.cn/202206071412590.png)

​         