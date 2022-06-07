# 																		js和jquery

## 1JS初步调用

### **简介**

  介绍：**js是一种基于对象的脚本语言，它不需要经过Web服务器就可以对用户的输入做出响应，并且它不依赖于操作系统，仅需要浏览器的支持，是一类非常方便、简单、实用的语言。**



### 1.1使用步骤

#### 在html页面中加入 

```
<script  type="text/javascript">   </script>
```

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C027b25da1634428bbcc427076240ee93%5Cclipboard.png)

#### 在外部引入自己的js

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C8d294f7b324d475a8860d9fdda79c5f2%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cbeb750cec15c4e69950418ef7e78d44e%5Cclipboard.png)







### **1.2js中的变量定义**

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C90ab24a1139246a3884104a3bf462efa%5Cclipboard.png)

另外script也可以像css那样使用

其中的运算

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cfc83a81e47544011acc3af78efd6c19b%5Cclipboard.png)

​                               



### **1.3  函数的定义  function**



![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C3108b30966a94e4592995fe7d8321a93%5Cclipboard.png)

#### **使用方式**



![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C0460112832414d6aba36a60fa7707c00%5Cclipboard.png)



![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C6def75b3c9784ea2a004774d2aff0c58%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C3f6cfaec7e354cab81f7e87917b4f3c3%5Cclipboard.png)

#### 注意事项

function函数是不允许重载的（重载就是方法名相同，参数不同）

原因：是因为function函数有默认的参数rgments

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cbd66ace9185d4dd6aef6d61524961903%5Cclipboard.png)



### **1.4  js中自定义对象**

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C69d1329c00674ede966ff2c5597998f1%5Cclipboard.png)

 同样也可以new  类   使用非常简单

加入自己的属性其实就是自己的类名直接以.属性名然后赋值即可。

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ce94f5c0860bf40ef97c718dd73f372a7%5Cclipboard.png)

2   花括号使用

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ce3980746d62246f4b54998f194378148%5Cclipboard.png)

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

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C4454e48cdfa14386a5b1ee238ab6ce74%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd48274a1ae124672a06c781fd2f96687%5Cclipboard.png)

#### 动态注册

获取对象属性后使用

window.函数名 =  函数

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C4169f9f759954cd28c25562ea803bce1%5Cclipboard.png)





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

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C1addea9176cd48b09ecf54786121e631%5Cclipboard.png)

最常见的是定义一个变量  格式为   var  xxx=/{xxx}/ 表示验证是否包含这个元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cc1ffbb82db7840daa29286a101562391%5Cclipboard.png)

​           3.3    全选  反选  全不选

运用的是checked这个选项    

到最后使用遍历即可    全选是遍历为true

全不选是遍历为flase

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ccf52d04ee5d440ce95920b51ad0e5398%5Cclipboard.png)

3.3  document获取的方法

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ccc7c4a9e47254943adf848118dc25f76%5Cclipboard.png)

如果有id就可以通过id获取

如果有id就可以通过name获取

如果没有id， name就可以通过标签名获取

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf64aea8ebd5e426c92b0b0c6f6c8c9b0%5Cclipboard.png)

 3.4   节点

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd4a41b7d91834d1aaf8d1efcaacd49e6%5Cclipboard.png)

​         