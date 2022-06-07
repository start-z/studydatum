#  **jquery**

官网：https://jquery.com/

**简介**  ：顾名思义就是javascript和查询（query） 是辅助js开发的js类库。核心思想是 写的更少 做的更多

**使用方法**

导入jquery的文件

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cc13cc1012b2d45e0b134bbfffe87978e%5Cclipboard.png)



##    1jquery的核心函数$

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cbe2161ee455e4494a5dc183a4b5d178f%5Cclipboard.png)

 

## 2  jquery对象和dom对象的区分

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ca2a36c10cad34f2499c5d018b9188521%5Cclipboard.png)

  其中  用到dom的属性就是dom对象

其中通过jquery查询时  id需要加#号  而 name不需要

 jquery对象的本质

本质其实就是dom对象的数组加上jquery对象的一系列函数

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C5c6fdc11285d4ea38460029cccc4997e%5Cclipboard.png)

他们不能互相对应的函数 

   他们如何互相转换呢

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C4d3efd8d426d45bf9202f636738558e5%5Cclipboard.png)





## 3  选择器大全

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C9bd770d27ccc4126b41b37aa18123b07%5Cclipboard.png)

###  3.1   多层选择器

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C4c695b570d964688b9b5f9c1d85a488b%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd394a2fb357847d69ee3d908b1ecd526%5Cclipboard.png)

注意看大于小于符号

其中  空格表示所有的元素

+表示下一个元素

~表示后面的所有元素

大于表示选择元素

### 3.2  过滤选择器

first  第一个元素

last 最后一个元素

not  查找所有未选中的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C23fba247357d4f9dacf6db9aac772a4a%5Cclipboard.png)

even表示获取偶数的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd3fecc5e4bfb4c4dab2c37114e0c5849%5Cclipboard.png)

odd  查找索引为单数的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf4f4ab06ccea4d1d981a91393e39f57b%5Cclipboard.png)

eqq 查找指定的索引的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cb1f563a6e77e4d1280a5994c6af4258d%5Cclipboard.png)

gt  查找比给定索引 大的值

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf11c3245a3d04aea844bb34e0dd46785%5Cclipboard.png)

lt     查找比指定索引小的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd841d32f00b1466eac0b92816c515b05%5Cclipboard.png)

### 3.3  内容过滤选择器

contains （text）  找出指定的文本元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cc7f90d6143e048d3a12e170122512e09%5Cclipboard.png)

empty  查找不含子元素的空元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C9d59babaf8e6469690d2086d96b2a059%5Cclipboard.png)

prant  查找非空的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cbcdf4968788d4e3e918c8b50ae5d333a%5Cclipboard.png)

has  查找包含那个元素的父元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf3d5aeed02c24f7d89fe817d1ad77b6d%5Cclipboard.png)

2.4  属性过滤器   列子  $（"input[acctrbiue=d]"）

attribute  匹配给定属性的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C052aa39525ad44a78753f5e96907cbec%5Cclipboard.png)

attribute=！value

查找指定元素中的没有的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C527e55d766a647548a4316516020aff2%5Cclipboard.png)

attribut^/$=value

查找以xx开头/j结尾的元素

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cdb86f21974b64a91939d7212e06dd63f%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd7e76e33671b4b58a12d97cbd66453b0%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C42958a557008408fadfa4c42c6245f4c%5Cclipboard.png)

[][][]  查找需要满足多个条件的元素

$（"input[name=a][#id=b]"）

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd3fa4b244b78491380a8c7f20f9d9e34%5Cclipboard.png)

2.5表单过滤器     $（":文本框类型"）

  

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cfaa4294b69cc43ce9f6dc3774287e227%5Cclipboard.png)

表单对象属性过滤

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C13686404a460416d9c8dcc371f9eff20%5Cclipboard.png)

disable表示不可用的    就是在input 中加入disabled  然后那个文本就不可以用了

 checked

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C3ef801539e8745d6a581302321c692e7%5Cclipboard.png)

selected

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C4876b13d24b8492db6159a3339e9924b%5Cclipboard.png)

遍历

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C7878ae9e80634941a4b1a3d213114d17%5Cclipboard.png)

在 jquery 有独特的遍历方式each（）

变量名.each(function

(alert(this.value)}))





## 4  元素的筛选   

元素的筛选是在括号外面使用 而   选择器实在括号里面使用

举例

$("input:last")   选择器

$(“input”).last    元素的筛选

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd2b1d1d1e4614dc797776dbb2029dbb1%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C24eb63b89e6947a6b9bd007d8c014b3a%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cc90fdab7446d4c37b8f84a667a10f277%5Cclipboard.png)



## 5jquery的属性操作





![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cab55c5e47ce04b08b5620d1fd9a21792%5Cclipboard.png)

html（）

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C3ca78d9a930143beb75882783edefb6d%5Cclipboard.png)

其中html 和val和text  都是不传参数的话就是获取  传了参数就是设置

另外val话可以多个选择（val用于表单项）

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C5679877caa654012b93c7e85dd8e75b1%5Cclipboard.png)

其中val 话可以多次选择多个框  一次性操作到位   后面赋的值可以随机放   他会自动匹配

 jquery  中attr（）方法和prop（）方法

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C3f357bfbcdbd4cc0a7c2ce3aa31ac172%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf87a810e1dea4c26aeaabb1d7fec74a7%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C99a63a22320c4861acd1fc2cb4312019%5Cclipboard.png)

其中attr和prop都可获取或者设置属性的值

attr获取到checked之类的属性时  他会说未定义属性  所以我们这个时候就需要使用prop

他们是互补的   、



## 6dom的增删改查

增加

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C6440ef4554034ce18d9436d6394e6555%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C2b7e642323184ceb8a1f1a6371026be5%5Cclipboard.png)

appendto

prepend

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C491846e79bf0452883495175e00c20e0%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cbc07bbdf199649af99e1b2f252dae39d%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C6dbc05493584451d97fd0618d30949a8%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C12dd255de5f245dca879166c1f628c20%5Cclipboard.png)



## 7jquery操作动画

addclass (颜色)

removeclass（）

toggleclass（）

offset（）

 动画操作

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C37bd87718c29407cbf648eecfeffe2b3%5Cclipboard.png)

这种都是在括号外面使用的

他们都可以在括号中添加值  添加值的类型是毫秒

show（1000）  1000毫秒出来完

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ceddd0ae91e424fe5be8dd51fae6274be%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C35db4e0f5e9c40be8c2fe02b14aa8fca%5Cclipboard.png)

在这里以上的方法是都可以输入参数的  第一个参数是毫秒时间  第二个是函数

其中可以把函数写成自己 然后就可以无限循环

fadein（）

fadeout

fadeto

fadetoggle





## 8jquery事件操作

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C843be097fc4d44cd9a2abba14870e4de%5Cclipboard.png)

 首先jquery比dom对象执行更快  其次dom对象会覆盖掉以前的对象 而juqery对象则不会

  jquery的事件处理对象

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C904692c61f5845a5a24291fbefe14321%5Cclipboard.png)

​        7.1  click事件

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C3cedc4b3ef7641d580671a1303f6bc9f%5Cclipboard.png)

其中不传函数是触发

鼠标移入移出事件

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C84f3824db459403da0d7993ed759232b%5Cclipboard.png)

意思就是你鼠标到那个位置他就会触发、

find事件

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C266f58aee31643029956ec9eea293e8b%5Cclipboard.png)

  常用的事件

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cba29dae4781b4d0a93c8f2e684c7a958%5Cclipboard.png)

分别是mouseover 鼠标移入

mouseout  鼠标移出

bind   绑定多个事件

onbind  解除事件 和bind事件用

cilck  单击事件

one  一次绑定一个事件

live  

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf309cbb64b4446a8806f963978d8c087%5Cclipboard.png)

其中在函数名输入event   可以判断使用的函数   



