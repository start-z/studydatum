
# -*- coding: UTF-8 -*-

import _thread
import threading
import time

# 为线程定义一个函数
def print_time( threadName, delay):
    count = 0
    while count < 5:
        time.sleep(delay)
        count += 1
        print("%s: %s" % ( threadName, time.ctime(time.time()) ))

# 创建两个线程
try:
    _thread.start_new_thread( print_time, ("Thread-1", 2, ) )
    _thread.start_new_thread( print_time, ("Thread-2", 4, ) )
#线程同步
    threadlock = threading.Lock()
    #上锁
    threadlock.acquire()
    #解锁
    threadlock.release()
except:
    print("Error: unable to start thread")

while 1:
    pass



