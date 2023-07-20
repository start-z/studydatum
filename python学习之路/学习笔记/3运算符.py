# -*- coding: UTF-8 -*-   //设置中文编码
# 算数运算符  其中比较特殊的是**代表a的b次方，//代表返回除法后的整数,向下取整
a, b = 20, 49
print a + b, a - b, a / b, a * b, a % b, a ** b, a // b

# 比较运算符 python支持>=、<=、<>等直接计算的运算符，而java不可以。
print a == b, a != b, a > b, a < b, a >= b, a <> b, a < -b

# 取值运算符
c, d = 20, 40
c += d
c -= d
c *= d
c /= c + d
c %= c + d
c **= c + d
c //= c + d

# 逻辑运算符
print a and b, a or b, not a

# 身份运算符
print a is b, a is not b
