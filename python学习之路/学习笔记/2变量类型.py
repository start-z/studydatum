# -*- coding: UTF-8 -*-   //设置中文编码
# Python有五个标准的数据类型：Numbers（数字  String（字符串 List（列表）Tuple（元组）Dictionary（字典）
a = 1  # 定义number类型
s = '测试12345'  # 定义string类型
list = [1, 2, 3, 4, 5]  # list数组可随时改变
notChangeList = (1, 2, 3, 4, 5)  # 原数组，即里面的数据不可改变

keyValue = {"1": 2, "2": 3}  # 字典类型 key-value键值对 对应java中的map

print s, list, list[2], notChangeList
# 输出1-3的元组数据
print notChangeList[1:3]
print keyValue["1"]
del a, s  # 删除引用
