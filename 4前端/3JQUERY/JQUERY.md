#  **jquery**

官网：https://jquery.com/

**简介**  ：顾名思义就是javascript和查询（query） 是辅助js开发的js类库。核心思想是 写的更少 做的更多

**使用方法**

导入jquery的文件

![img](http://inis.inis1719.cn/202206071424958.png)



##    1jquery的核心函数$

![img](http://inis.inis1719.cn/202206071424959.png)

 

## 2  jquery对象和dom对象的区分

![img](http://inis.inis1719.cn/202206071424960.png)

  其中  用到dom的属性就是dom对象

其中通过jquery查询时  id需要加#号  而 name不需要

 jquery对象的本质

本质其实就是dom对象的数组加上jquery对象的一系列函数

![img](http://inis.inis1719.cn/202206071424961.png)

他们不能互相对应的函数 

   他们如何互相转换呢

![img](http://inis.inis1719.cn/202206071424962.png)





## 3  选择器大全

![img](http://inis.inis1719.cn/202206071424963.png)

###  3.1   多层选择器

![img](http://inis.inis1719.cn/202206071424964.png)

![img](http://inis.inis1719.cn/202206071424965.png)

注意看大于小于符号

其中  空格表示所有的元素

+表示下一个元素

~表示后面的所有元素

大于表示选择元素

### 3.2  过滤选择器

first  第一个元素

last 最后一个元素

not  查找所有未选中的元素

![img](http://inis.inis1719.cn/202206071424966.png)

even表示获取偶数的元素

![img](http://inis.inis1719.cn/202206071424967.png)

odd  查找索引为单数的元素

![img](http://inis.inis1719.cn/202206071424968.png)

eqq 查找指定的索引的元素

![img](http://inis.inis1719.cn/202206071424969.png)

gt  查找比给定索引 大的值

![img](http://inis.inis1719.cn/202206071424970.png)

lt     查找比指定索引小的元素

![img](http://inis.inis1719.cn/202206071424971.png)

### 3.3  内容过滤选择器

contains （text）  找出指定的文本元素

![img](http://inis.inis1719.cn/202206071424972.png)

empty  查找不含子元素的空元素

![img](http://inis.inis1719.cn/202206071424973.png)

prant  查找非空的元素

![img](http://inis.inis1719.cn/202206071424975.png)

has  查找包含那个元素的父元素

![img](http://inis.inis1719.cn/202206071424976.png)

2.4  属性过滤器   列子  $（"input[acctrbiue=d]"）

attribute  匹配给定属性的元素

![img](http://inis.inis1719.cn/202206071424977.png)

attribute=！value

查找指定元素中的没有的元素

![img](http://inis.inis1719.cn/202206071424978.png)

attribut^/$=value

查找以xx开头/j结尾的元素

![img](http://inis.inis1719.cn/202206071424979.png)

![img](http://inis.inis1719.cn/202206071424980.png)

![img](http://inis.inis1719.cn/202206071424981.png)

[][][]  查找需要满足多个条件的元素

$（"input[name=a][#id=b]"）

![img](http://inis.inis1719.cn/202206071424982.png)

2.5表单过滤器     $（":文本框类型"）

  

![img](http://inis.inis1719.cn/202206071424983.png)

表单对象属性过滤

![img](http://inis.inis1719.cn/202206071424984.png)

disable表示不可用的    就是在input 中加入disabled  然后那个文本就不可以用了

 checked

![img](http://inis.inis1719.cn/202206071424985.png)

selected

![img](http://inis.inis1719.cn/202206071424986.png)

遍历

![img](http://inis.inis1719.cn/202206071424987.png)

在 jquery 有独特的遍历方式each（）

变量名.each(function

(alert(this.value)}))





## 4  元素的筛选   

元素的筛选是在括号外面使用 而   选择器实在括号里面使用

举例

$("input:last")   选择器

$(“input”).last    元素的筛选

![img](http://inis.inis1719.cn/202206071424988.png)

![img](http://inis.inis1719.cn/202206071424989.png)

![img](http://inis.inis1719.cn/202206071424990.png)



## 5jquery的属性操作





![img](http://inis.inis1719.cn/202206071424991.png)

html（）

![img](http://inis.inis1719.cn/202206071424992.png)

其中html 和val和text  都是不传参数的话就是获取  传了参数就是设置

另外val话可以多个选择（val用于表单项）

![img](http://inis.inis1719.cn/202206071424993.png)

其中val 话可以多次选择多个框  一次性操作到位   后面赋的值可以随机放   他会自动匹配

 jquery  中attr（）方法和prop（）方法

![img](http://inis.inis1719.cn/202206071424994.png)

![img](http://inis.inis1719.cn/202206071424995.png)

![img](http://inis.inis1719.cn/202206071424996.png)

其中attr和prop都可获取或者设置属性的值

attr获取到checked之类的属性时  他会说未定义属性  所以我们这个时候就需要使用prop

他们是互补的   、



## 6dom的增删改查

增加

![img](http://inis.inis1719.cn/202206071424997.png)

![img](http://inis.inis1719.cn/202206071424998.png)

appendto

prepend

![img](http://inis.inis1719.cn/202206071424999.png)

![img](http://inis.inis1719.cn/202206071424000.png)

![img](http://inis.inis1719.cn/202206071424001.png)

![img](http://inis.inis1719.cn/202206071424003.png)



## 7jquery操作动画

addclass (颜色)

removeclass（）

toggleclass（）

offset（）

 动画操作

![img](http://inis.inis1719.cn/202206071424004.png)

这种都是在括号外面使用的

他们都可以在括号中添加值  添加值的类型是毫秒

show（1000）  1000毫秒出来完

![img](http://inis.inis1719.cn/202206071424005.png)

![img](http://inis.inis1719.cn/202206071424006.png)

在这里以上的方法是都可以输入参数的  第一个参数是毫秒时间  第二个是函数

其中可以把函数写成自己 然后就可以无限循环

fadein（）

fadeout

fadeto

fadetoggle





## 8jquery事件操作

![img](http://inis.inis1719.cn/202206071424007.png)

 首先jquery比dom对象执行更快  其次dom对象会覆盖掉以前的对象 而juqery对象则不会

  jquery的事件处理对象

![img](http://inis.inis1719.cn/202206071424008.png)

​        7.1  click事件

![img](http://inis.inis1719.cn/202206071424009.png)

其中不传函数是触发

鼠标移入移出事件

![img](http://inis.inis1719.cn/202206071424010.png)

意思就是你鼠标到那个位置他就会触发、

find事件

![img](http://inis.inis1719.cn/202206071424011.png)

  常用的事件

![img](http://inis.inis1719.cn/202206071424012.png)

分别是mouseover 鼠标移入

mouseout  鼠标移出

bind   绑定多个事件

onbind  解除事件 和bind事件用

cilck  单击事件

one  一次绑定一个事件

live  

![img](http://inis.inis1719.cn/202206071424013.png)

其中在函数名输入event   可以判断使用的函数   



