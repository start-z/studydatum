

#   html+css





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

![image-20220502161211126](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502161211126.png)

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

![image-20220502164218374](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502164218374.png)

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

![image-20220502170635306](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502170635306.png)









## 4网页定位

#### 相对定位（relative  参考点为自身的元素 不脱离文档流）

![image-20220502180832522](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502180832522.png)





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



#### 绝对定位（ absolute  参照物为开启了定位的父级元素   脱离文档流）

![image-20220502182255428](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502182255428.png)



#### 固定定位（ fixed 参照物为浏览器的窗口  不脱离文档流  常见于 页面头部随着页面滚动但是位置不变 ）

![image-20220502183519444](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502183519444.png)



#### 粘连定位（sticky 参照物为浏览器的窗口  不脱离文档流 常用于页面滚动条下拉后元素相对于浏览器固定在某个位置）

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



![image-20220502185506917](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502185506917.png)







### 6图标字体

![image-20220502192443185](C:%5CUsers%5C%E4%B8%80%E5%8F%B7%E7%BA%BF%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5Cimage-20220502192443185.png)

