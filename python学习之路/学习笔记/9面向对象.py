# coding=utf-8
class student:
    """学生类"""
    name = ''
    age = 0

    def getAge(self):
        print(self)
        print(self.age)

    # 实例化参数  构造方法
    def __init__(self, name, age):
        self.name = name
        self.age = age



#继承类
class  parentStudent(student):
    def __init__(self, name, age):
        super().__init__(name, age)
        print('123')


demo = student('200', 20)
demo.age = 30
print(demo.getAge())
print(parentStudent.getAge())
