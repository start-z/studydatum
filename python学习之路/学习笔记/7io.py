# -*- coding: UTF-8 -*-   //设置中文编码

# 读取键盘输入
# Python提供了两个内置函数从标准输入读入一行文本，默认的标准输入是键盘。如下：
# raw_input  input
str = raw_input('请输入你的密码')
print '你输入的密码是' + str

# input([prompt]) 函数和 raw_input([prompt]) 函数基本类似，但是 input 可以接收一个Python表达式作为输入
#请输入：[x*5 for x in range(2,10,2)]
#你输入的内容是:  [10, 20, 30, 40]

#文件操作
txt = open("C:\\Users\\BJB-314\\Desktop\\test\\1231.txt", "r")
print txt.read()
