# -*- coding: UTF-8 -*-   //设置中文编码
a = 20

# 条件语句
if a < 1:
    print '小于'
else:
    print '大于'

# 循环语句
# 1while循环

while a > 1:
    print '小于'
    a -= 1
    if a == 10:
        print "跳过本次循环"
        continue
    if a < 10:
        print '终止循环'
        break

print 'goodbye'

# 2 for循环
#
b = '123'
for b in a:
    print b
