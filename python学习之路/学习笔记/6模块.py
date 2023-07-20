# -*- coding: UTF-8 -*-   //设置中文编码
#模块引入使用import  一个模块只会被导入一次，不管你执行了多少次import。这样可以防止导入模块被一遍又一遍地执行。
import sixExample
#from…import 语句
sixExample.print_func('123')

#from…import* 语句
#把一个模块的所有内容全都导入到当前的命名空间也是可行的，只需使用如下声明：

#from modname import *
