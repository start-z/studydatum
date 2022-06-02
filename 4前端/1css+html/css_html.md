

#     												html+css





## 1像素

px 表示像素值

em是按照font-size的大小设置的 默认1em=16字体大小

![img](http://inis.inis1719.cn/202206021241528.png)

颜色单位就是后面可以设置，分为单词和rgb和rgbe

rgb可以tiaose，rgbe可以设置透明度

**文档流**

![img](http://inis.inis1719.cn/202206021241530.png)

块元素  默认占一行，默认大小取决于你放的东西的撑满度

行元素  由左向右排序

## 2**盒子模型api**



![img](http://inis.inis1719.cn/202206021241531.png)

![img](http://inis.inis1719.cn/202206021241532.png)

**1.1边框设置border**

![img](http://inis.inis1719.cn/202206021241533.png)

![img](http://inis.inis1719.cn/202206021241534.png)

![img](http://inis.inis1719.cn/202206021241535.png)

以上还可以简写

直接border： 指定值即可

![img](http://inis.inis1719.cn/202206021241536.png)

**1.2内边框padding**

![img](http://inis.inis1719.cn/202206021241537.png)

**1.3外边框margin**

![img](http://inis.inis1719.cn/202206021241538.png)

总结如下

1border边框和内容区和内边距padding设置会改变盒子的大小

2外边框会影响盒子在页面上的位置

3另外在这四个元素都有共同的方向属性

上top 下buttom 左right 右left

4border边框属性中的样式有solid 实线  dotted点状虚线   dashed 虚线  double 双线

5这几个属性都可以进行简写。直接属性后面加参数





## 3网页的布局

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>网页布局</title>
</head>
<body>
    <header>头部</header>
    <main>
<nav>中间区域左边</nav>
<article>中间区域中间</article>
<aside>中间区域右边</aside>

    </main>
    <footer>尾部</footer>
</body>
<style>
nav{
background-color: blanchedalmond;


}
article{
    background-color: blue;
}

aside{
    background-color: brown;
}
nav,article,aside{
    height: 200px;
    float: left;
}   
header{
    margin: 0  20px auto;
    height: 200px;
    text-align: center;
    background-color: aliceblue;
    
}

main{
    margin: 0 auto;
    height: 200px;
    background-color: aqua;
    text-align: center;
}

footer{
    margin: 0  auto;
    height: 200px;
    text-align: center;
    background-color: beige;
}

</style>
</html>
```

![image-20220502161211126](http://inis.inis1719.cn/202206021241539.png)

在常用的布局当中 常常会遇到高度塌陷的问题（即子元素超出父元素的范围） 我们的解决办法是开启BFC模式



开启BFC后的特点：

1. 不会被浮动元素覆盖
2. 不会被父元素的外边距影响
3. 可以包含浮动的子元素

如何开启BFC呢：

1. 父子元素都设置浮动（宽度丢失 不推荐）
2. 将父元素设置行内块元素（宽度丢失 不推荐）
3. 设置overflow非默认值（常常使用hidden ）



clear（清除浮动元素带给自身元素的影响）

![image-20220502164218374](http://inis.inis1719.cn/202206021241540.png)

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>clear</title>
</head>
<body>
    <div class="box1">1</div>
    <div class="box2">2</div>
</body>
<style>
.box1,.box2{
width: 200px;
height: 200px;
background-color: yellow;
}
.box1{
    float: left;
}
.box2{
background-color: beige;
clear: left;
}

</style>
</html>
```





使用after伪类解决高度塌陷

```
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>after</title>
</head>

<body>

    <div class="box1">
        <div class="box2"></div>
    </div>
</body>
<style>

    .box1 {

        border: 20px   salmon solid;
      
    }

    .box2 {
        background-color: aquamarine;
        width: 200px;
        height: 200px;
        float: left;
        
    }
    //通过伪元素处理高度塌陷问题
    .box1::after{
        content: '';
        clear: both;
      display: block;
    }
</style>

</html>
```

clearfix解决父子元素外内边框重叠的问题

![image-20220502170635306](http://inis.inis1719.cn/202206021241541.png)









## 4网页定位

#### 相对定位

（relative  参考点为自身的元素 不脱离文档流）

![image-20220502180832522](http://inis.inis1719.cn/202206021241542.png)





```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>相对定位</title>
</head>
<body>
    <h1>需求是将2移动到1的旁边</h1>
其中margin-top是可以做到的但是需要修改多个元素 不方便  于是我们可以采用相对定位的方式去实现
    <div class="div1">1</div>
    <div class="div2">2</div>
    <div class="div3">3</div>
</body>
<style>
.div1,.div2,.div3{
    width: 200px;
    height: 200px;
}
.div1{
    background-color: aliceblue;
  
}

.div2{
    background-color:antiquewhite;
    position: relative;
    /* 相对定位 */
    left: 200px;
    top: -200px;
}

.div3{
    background-color:yellow;
}
</style>



</html>
```



#### 绝对定位

（ absolute  参照物为开启了定位的父级元素   脱离文档流）

![image-20220502182255428](http://inis.inis1719.cn/202206021241543.png)



#### 固定定位

（ fixed 参照物为浏览器的窗口  不脱离文档流  常见于 页面头部随着页面滚动但是位置不变 ）

![image-20220502183519444](http://inis.inis1719.cn/202206021241544.png)



#### 粘连定位

（sticky 参照物为浏览器的窗口  不脱离文档流 常用于页面滚动条下拉后元素相对于浏览器固定在某个位置）

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>相对定位</title>
</head>
<body>
    <h1>需求是将2移动到1的旁边</h1>
其中margin-top是可以做到的但是需要修改多个元素 不方便  于是我们可以采用相对定位的方式去实现
    <div class="div1">1</div>
    <div class="div2">2</div>
    <div class="div3">3</div>
</body>
<style>
    body{
        height: 2000px;
    }
.div1,.div2,.div3{
    width: 200px;
    height: 200px;
}
.div1{
    background-color: aliceblue;
  
}

.div2{
    background-color:antiquewhite;
    /* //绝对定位 */
    /* position: absolute; */
    /* 相对定位 */
    /* position: relative; */
    /* 粘连定位 */
    position: sticky;
    top: 0;
    

}

.div3{
    background-color:yellow;
        width: 100%;
}
</style>



</html>
```



### 5元素的层级（z-index）



![image-20220502185506917](http://inis.inis1719.cn/202206021241545.png)







### 6图标字体

![image-20220502192443185](http://inis.inis1719.cn/202206021241546.png)





### 7字体设置

![image-20220505102524483](http://inis.inis1719.cn/202206021241547.png)

#### 字体加粗设置和倾斜设置

![image-20220505102853058](http://inis.inis1719.cn/202206021241548.png)



#### 文本的水平对齐

text-align



![image-20220505103327871](http://inis.inis1719.cn/202206021241549.png)

#### 字体元素垂直对齐

vertical-align

（设置字体对齐  可使用于图片大小与外边框不一致的问题）

![image-20220505103228261](http://inis.inis1719.cn/202206021241550.png)



#### 文本下划线

text-decoration





![image-20220505103404800](http://inis.inis1719.cn/202206021241551.png)



#### 文字溢出内容显示省略号

![image-20220505103652097](http://inis.inis1719.cn/202206021241552.png)





### 8图片设置

![image-20220505110601900](http://inis.inis1719.cn/202206021241553.png)





渐变设置

background-image: linear-gradient(red,yellow);

![image-20220505163339152](http://inis.inis1719.cn/202206021241554.png)



径向渐变（中心点向外圈扩散）

![image-20220505163634854](http://inis.inis1719.cn/202206021241555.png)







### 9过渡效果

(需样式发生对应的数据变化后才触发）

transition

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>兔子</title>
</head>
<body>
    
<div class="box1"></div>



</body>
<style>
    .box1{
        height: 271px;
        width: 132px;
        margin: 0  auto;
        background-image: url('./兔子.png');
        background-position: 0 0 ;
        transition: 0.9s steps(3);
    }

    .box1:hover{

        background-position:  527px  0;
    }
</style>
</html>
```







### 10动画效果

（自动触发）

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>过渡</title>
</head>
<body>
    

    <div class="box">
<div class="box1">
<div class="box2">



</div>

</div>


    </div>
</body>

<style>

*{
    margin: 0 0;
    padding: 0 0;
}
.box1{
    margin: 0 auto;
    width: 400px;
    height: 600px;
    background-color: aqua;
}
/* .box1:hover .box2{
    transition: all  2s;
    width: 200px;
height: 300px;
background-image: linear-gradient(red,yellow);
margin-left: 499px;
} */
.box2{
    width: 50px;
        height: 100px;
    background-color: black;
    animation-name: test;
    animation-duration: 2s;
    animation-iteration-count: infinite;
    animation-direction: alternate;

}
@keyframes  test{
    form{
        margin-left: 0px;
    }
    to{
        margin-left: 100%;
    }
}

</style>
</html>
```





### 11平移

transform(水平平移 x  y   z)

![image-20220506163202284](http://inis.inis1719.cn/202206021241556.png)



### 12旋转  

![image-20220506163257924](http://inis.inis1719.cn/202206021241557.png)





### 13   less

less是css的增强版 

![image-20220507093314680](http://inis.inis1719.cn/202206021241558.png)





### 14弹性盒子flex

flex是css的一种布局    主要可用来替代浮动float（浮动可兼容老版本浏览器  但是flex有可能不行）

flex盒子的元素随着页面的大小而改变



```
display：flex
```



![image-20220507142744118](http://inis.inis1719.cn/202206021241559.png)





![image-20220507143106140](http://inis.inis1719.cn/202206021241560.png)



设置弹性元素是否自动换行

flex-wrap

![image-20220507143320236](http://inis.inis1719.cn/202206021241561.png)



### 15 媒体查询
