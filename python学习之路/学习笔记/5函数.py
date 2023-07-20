# -*- coding: UTF-8 -*-   //设置中文编码

# 打印指定字符串
def printStr(str):
    print str


def empty():
    pass


printStr('hello, my  world')
empty()


# 不定长参数， 函数定义参数前面加上星号
# 可写函数说明
def printinfo(arg1, *vartuple):
    "打印任何传入的参数"
    print "输出: "
    print arg1
    for var in vartuple:
        print var
    return


# 调用printinfo 函数
printinfo(10)
printinfo(70, 60, 50)

# lamada代替def定义函数
sum = lambda a, b: a + b

print sum(1, 2)


# return 语句如果存在参数值则返回retrun的值，否则返回none
def returnOne(str):
    return str


def returnEmpty():
    return


print returnOne('123213')
print returnEmpty()
