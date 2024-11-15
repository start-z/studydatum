# 																JVM

## **1jvm的概述**
参考地址:https://www.processon.com/view/link/5eea141cf346fb1ae56a44e7

其实就是一台虚拟的计算机，用来执行一系列的操作系统指令，虚拟机可分为系统虚拟机（例如vm）和程序虚拟机（Java技术核心）（执行java的操作命令解析）。

程序虚拟机优点：

- 具有自动垃圾回收
- 一次编译，到处运行。 

### **1.1jvm的位置**

![img](http://inis.inis1719.cn/202206062249346.png)

### **1.2jvm的整体结构**

![img](http://inis.inis1719.cn/202206062249348.png)

### **1.3JVM的架构模型**

编译器的架构可分为

- 基于**栈**的指令集架构（java 时基于栈的指令集来实行的（可移植性强））
- 基于**寄存器**的指令集架构

区别在于

- 栈指令**可移植性**强 
- 栈**实现**简单（大部分0地址指令）而寄存器使用（1 2 3  地址指令） 
- 栈指令相对于寄存器 指令的**性能**稍逊色
- 栈指令不需要**硬件支持**。

### **1.4jvm的生命周期**

#### **1.4.1启动**

bootstarp  class cloader 创建的初始类（initial class） 来完成   可自定义加载继承（Classloader）

#### **1.4.2执行**

执行的时候其实是执行了一个java虚拟机的进程

![img](http://inis.inis1719.cn/202206062249349.png)

#### **1.4.3退出**

- 程序异常退出
- Runtime.exit(底层调用hait方法)或者System.exit或者 Runtime.halt

![img](http://inis.inis1719.cn/202206062249350.png)

### **1.5市面上常见的虚拟机**

#### **1.5.1Classvm**

- 解释器（响应速度快，执行慢）
- jit（执行速度快，响应慢）

java编译与c++和c的区别

在加载方面的话java采用jit执行引擎的加载速度会更快**性能**会更加的优化，但是存在的问题就是暂停的时间过长，所以java引擎执行的时候不能只使用jit来运行（性能快但是暂停时间过长）；

java在使用翻译器的时候通过采用执行引擎既不能太快也不能太慢 （太快的话只使用jit编译的话中间的暂停时间太长，）

![img](http://inis.inis1719.cn/202206062249351.png)

#### **1.5.2exact vm**

![img](http://inis.inis1719.cn/202206062249352.png)

#### **1.5.3hospot vm(主流  商用)**

#### **1.5.4jrockit  vm(世界上最快的jvm，内部不在乎解释器实现，专注与服务器 商用)**

#### **1.5.5j9 vm （商用）**

#### **1.5.6 graal vm** 	

## **2内存结构概述**

### **2.1类加载器和类的加载顺序**

类加载器子系统负责加载class文件但是不负责类的运行，而类的运行则是由ExecutionEngine决定。

![img](http://inis.inis1719.cn/202206062249353.png)

![img](http://inis.inis1719.cn/202206062249354.png)

![img](http://inis.inis1719.cn/202206062249355.png)

#### **2.1类的加载过程**

##### **1 load**

1得到类文件的二进制流.

2将静态存储结构转换为数据结构。

3生成class文件，作为访问接口。

##### **2linking**

**验证**

确保类加载不会危害虚拟机（其中class文件的特定开头标识就是caffbabe）并检验是否符合当前虚拟机。

验证方式分为文件格式、元数据、字节码、符号引用。

**准备**

为类变量赋予初始值，然后在赋予你自己给的值。

**解析**

将常量池的符号转为直接引用。 

##### **3初始化**

执行类clinit初始化的过程

收集类变量的赋值过程和静态代码快（如果没有赋值或者静态代码快就会直接跳过)

如若存在父类变量则先加载父类的变量。另外init方法在多线程中是被加锁的。

 

自定义加载器

![img](http://inis.inis1719.cn/202206062249356.png)

加载方式

1继承classload 实现findclass方法或者实现loadclass方法（已过时）

2简单的加载器可以直接继承urlclassload

#### **2.2双亲委派机制**

在加载过程中会判断是否存在父类，如果存在父类则会先加载父类的加载器，如果父类之上还有父类则会一直递归加载，如果父类加载器成功就返回。如果失败就记载子类的加载器。

举例如下：

比如我创建了lang包下的Stirng对象且结构和系统的String对象是一制的，那么在调用过的时候会调用我自己的String类嘛？

不会

#### **2.3沙箱安全机制**

保护源码不被随意篡改

#### **2.4判断2个对象是否相同的依据**

1类的包结构和名称相同

2对应的加载类classloader相同

#### **2.5运行时数据区**

![img](http://inis.inis1719.cn/202206062249357.png)

##### **2.5.1pc寄存器**

每个线程独有一份

作用：储存下一条指令的地址，也就是即将执行的代码，由执行引擎读取下一条指令。

![img](http://inis.inis1719.cn/202206062249358.png)

![img](http://inis.inis1719.cn/202206062249359.png)

堆和方法区是会被垃圾回收的，而pc寄存器(程序计数器)是不会垃圾回收的。

**pc寄存器既没有GC也没有OOM（异常）**

**pc寄存器存储字节码指令地址有什么用？**

因为cpu需要来回的不停切换线程，在切换后需要知道在哪里执行。

**为什么要使用pc寄存器来存储，pc寄存器为什么线程私有？**

jvm的字节码解析器需要i解析来确定下一条的字节指令。

为了让线程单独读取指令且不会产生混淆 所有设置线程私有。

![img](http://inis.inis1719.cn/202206062249360.png)

##   **3  虚拟机栈**

**什么是栈?**

在每个**线程**创建的时候都会生成一个虚拟机栈，而栈内部存放一个个的**栈帧且线程私有。**

他的生命周期和线程一致。

存放java的变量。

栈的优点

- 跨平台
- 指令集小
- 编译器容易实现

栈的缺点

- 性能下降
- 实现同样的指令需要更多的操作

内存中的栈与堆

- 栈时运行时的单位
- 堆时存储时的单位

### **3.1栈的储存结构和运行原理**

**栈里面存储什么？**

每个线程会创建独有的栈，另外栈中会存放栈帧，栈帧中存放方法的变量

栈的操作有**出栈**和**进栈,**在**栈顶**的栈帧我们称之为**当前栈帧,**而对应的方法我们称之为**当前方法,**所对应的类我们称之为**当前类.**

**栈帧的内部结构**

- **局部变量表**  存放变量
- **操作数栈**
- **方法返回地址**
- **动态连接**  (指向常量池的动态引用)

**栈帧的局部变量表（建立在栈上  由于栈不会被线程共享，而局部变量表又属于栈帧 ，所以不会存在线程安全的问题）**

定义为一个数组吗,可存放基本类型

局部变量表不存在线程安全因为他的上级栈帧是私有的

局部变量表的大小是在编译期就确定好的。

局部变量表的方法只在当前方法有效，方法结束（栈帧结束）也会随之销毁

**插槽slot（其实局部变量表中的插槽是可以被重复利用的）**

栈帧中的局部变量表中的槽位是可以重复利用的，如果一个局部变量过了其作用域，那么在其作用域之后申明的新的局部变量就很有可能会复用过期局部变量的槽位，从而达到节省资源的目的。	

代码如下：

public void getTest() {    int a = 0;     //1    {        int b = 0;   //2        b = a + 1;     }    int c = 1;   //3 }

this占0号、a单独占1个槽号、c重复使用了b的槽号

局部变量表中存放的单元就叫插槽

32位类型占一个插槽，64占用2个。、

**静态变量与局部变量的区别与对比**

静态变量： 默认是在类加载的link阶段（2.1）加载

局部变量： 在类创建以后  加载（设计到栈的优化和调优  垃圾回收根节点  被局部变量间接和直接引用的对象都不会被回收）

**a++与++a的区别：**

a++先将操作数栈的的a加入到局部变量表中计算 后 压栈

++a先将a引入到局部变量表，进行压栈后才进行计算

**栈顶缓存技术**

解决 频繁的入栈出栈操作

将**栈顶**的元素全部缓存到物理机的cpu中以减少对应的入栈出栈操作。提高执行效率。

**动态链接**

每一个栈帧都包含一个指向该运行常量运行池的该栈帧所属方法的引用。（包含的目的就是实现动态连接）、

为什么需要常量池：提供一些符号和变量  便于指令的识别。

**方法的调用**

静态调用（早期绑定）：

在字节码方法进入到jvm内部的时候,如果被调用的方法在编译器是可知的，且运行时保持不变的话，在调用常量池的符号直接转换为直接引用 ，称之为 静态引用。

动态调用（晚期绑定）： 

被调用的方法在编译期无法确认 ，只能在运行期间确定符号的引用，称之为动态引用。

**虚方法和非虚方法：**

 非虚方法:  在编译期期间就确定了不可变 （静态方法、私有方法、final方法、构造器方法、父类方法）

​     虚方法：在编译期间无法确定是否可变

![img](http://inis.inis1719.cn/202206062249361.png)

**java重写方法的本质：**

![img](http://inis.inis1719.cn/202206062249362.png)

当然 不可能每次都去寻找 对应的方法，于是为了性能优化，就建立了**虚方法表**：方法的指向

 				**帧数据区**

**方法返回地址**

存放的是该方法pc寄存器的值（pc寄存器存放的是下一条字节指令）

**常见问题解析**

垃圾回收机制是否会影响虚拟机栈？  不会 

**本地方法接口（Navite关键字修斯）**  

定义：  	一个本地方法调用非java 方法（融合其他语言）。

为什么要使用本地方法： 1 各种语言的交互    2在某些层级的方法上提高效率。

**本地方法栈（线程私有）：**

定义： 管理本地方法。

## **4堆**

每一个jvm实例都存在一个堆内存。

数组和栈都会存放在堆中。

### **1堆空间：**

现代垃圾收集器大多采用分代收集理论，

堆空间分为：新生区、养老区、元空间。

jdk8以前的并不叫元空间而是叫永久区。

堆指令大小设置：

-Xms  设置初始内存

-Xmx 设置最大内存

通常将这2个设置为同样的值，为的是回收的时候不需要再去计算剩余内存大小   达到性能优化的目的.

堆指令查看:

jpc  查看堆

jpc -进程id 查看指定的堆  

### **2年轻代和老年代**

存储在jvm中的类可分为2类：

年轻代： 生命周期短 迅速死亡

老年代： 生命周期较长

如何调节新老年代的比例占比

![img](http://inis.inis1719.cn/202206062249363.png)

-Xmn  设置新生代的空间大小。

### **3对象的分配过程**

![img](http://inis.inis1719.cn/202206062249364.png)

![img](http://inis.inis1719.cn/202206062249365.png)

### **4minior Gc（伊甸园区 YGC）、Major Gc(老年代 gc)、Full Gc()**

jvm在进行垃圾回收的时候主要是回收（新生代、老年代、方法区） 其中大部分回收的都是新生区。

针对gc的实现可分为部分收集和整堆收集（Full Gc）

![img](http://inis.inis1719.cn/202206062249366.png)

年轻代触发机制

![img](http://inis.inis1719.cn/202206062249367.png)

老年代触发机制

![img](http://inis.inis1719.cn/202206062249368.png)

Full触发机制

![img](http://inis.inis1719.cn/202206062249369.png)

## **5 堆空间分代思想**

1为什么需要分代？

大多数对象的生命周期较短  ,分代的唯一理由就是性能优化.

## **6 总结内存分配策略**

![img](http://inis.inis1719.cn/202206062249370.png)

## **7对象分配过程s:TLAB(被线程私有的空间)**

![img](http://inis.inis1719.cn/202206062249371.png)

在多线程的操作中会涉及到对应的对应的线程安全问题,为了避免这个情况发生,就需要加锁  但是加锁又会影响效率 所以推出了 TLAB  (原理是让每一个线程都拥有自己的伊甸园区)  一旦TLAB失败的话就会实现加锁机制来达到原子性

## **8 堆空间参数的设置**

常用指令

![img](http://inis.inis1719.cn/202206062249372.png)

## **9堆是分配对象储存的唯一选择吗**

![img](http://inis.inis1719.cn/202206062249373.png)

如何判断是否发生逃逸  ?(最根本的列子其实就是在外表调用方法变量);

举例如下:

public class TaoYi {    //会发生逃逸现象  因为外部有可能会调用内部的变量    public StringBuffer   taoyi1(){        StringBuffer buffer = new StringBuffer();        return  buffer;    } }

## **10逃逸分析 :代码优化  -DoescapeAnalysic**

### **1栈上分配**

![img](http://inis.inis1719.cn/202206062249374.png)

### **2同步省略**

![img](http://inis.inis1719.cn/202206062249375.png)

![img](http://inis.inis1719.cn/202206062249376.png)

### **3分离对象或标量替换**

![img](http://inis.inis1719.cn/202206062249377.png)

![img](http://inis.inis1719.cn/202206062249378.png)

## **11方法区**

方法区可看作是一块独立于java堆的内存空间

### 1方法区参数设置：

![img](http://inis.inis1719.cn/202206062249379.png)

### 2 方法区的内部结构

![img](http://inis.inis1719.cn/202206062249380.png)

![img](http://inis.inis1719.cn/202206062249381.png)

常量池的理解

![img](http://inis.inis1719.cn/202206062249382.png)

**运行常量池**

  jvm中每个类都会拥有一块运行常量池  ，池中的数据像数组一样通过索引访问。

**方法区的演变**

jdk1.6  存在永久代  静态变量存放在永久带上

jdk1.7  存在永久代  字符串常量池 、静态变量  保存在堆中

jdk1.8  不存在永久代 、 类型信息、字段、方法、常量存放在元空间中， 字符串常量池、静态变量依旧在堆中。

**为什么要删除永久代？**

1 永久代设置空间大小不固定。（永久代受限于虚拟机 而元空间受限于宿主的本地内存    可调节）

2 对永久代调优较难

综上所述  其实就是替换后减少fullGc的次数。

**StringTable为什么迁移到堆中？**

其实是垃圾回收的效率底下，在jdk7以前隶属于永久代  而永久代进行垃圾清理的时候需要full GC   ，但是full gc 触发的条件则是老年代和永久代 空间不足，但是开发中我们又会频繁的创建字符串  所以我们就将他放在堆中 提高效率，及时回收内存。

**方法区的垃圾收集主要回收：**

常量池中废弃的常量和不再使用的类型。

p101 总结

## **12对象实例化的方式**

### **12.1对象创建的步骤**

1.  判断类是否加载（加载 、 链接、初始化）
2. 为对象分配内存

- - 内存是否规整  ----指针碰撞（中间以指针为分界  用过的内存为一边，没用过的则是另一边）
  - 内存不规整      

- - - 虚拟机维护列表
    - 空间列表分配

1. 处理并发问题

- - 采用CAS失败重试  保证原子性
  - 每个线程分配一块CLAB 

1. 初始化分配到的空间
2. 设置类的对象头（将对象元数据信息、对象的hashcode和GC信息 、锁信息存储到对象的信息头中） 具体设置方式有jvm决定
3. 执行init初始化方法进行初始化

### **12.2对象的内存布局**

对象的具体存放是在堆空间中

1. 对象头		

- 运行时元数据

- -    哈希值
  - 对象分代年龄
  - 锁状态标志
  - 线程锁
  - 偏向线程id
  - 偏向时间戳	

- 类型指针

- - 指向元数据的class文件  确定该对象的类

\2. 实例数据
