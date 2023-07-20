import json

import requests
from bs4 import BeautifulSoup

# re使用正则解析太麻烦了  改用BeautifulSoup
# 使用requests发送请求爬取信息
# 爬虫功能:获取当当网的5星数据
# 爬取地址:

pageSum = 1
bookList = []
while pageSum <= 3:

    url = 'http://bang.dangdang.com/books/fivestars/01.00.00.00.00.00-recent30-0-0-1-' + str(pageSum)
    response = requests.get(url)
    if response.status_code == 200:
        # 获取返回的html页面然后解析
        html = response.text
        sop = BeautifulSoup(markup=html, features="html.parser")
        # 找到ul标签并且class = bang_list clearfix bang_list_mode
    ulList = sop.find('ul', attrs={"class": "bang_list clearfix bang_list_mode"})
    liClass = ulList.findAll("li")
    for li in liClass:
        # 重新封装dom节点
        liDom = BeautifulSoup(li.__str__(), "html.parser")
        # 打折后的金额
        bookList.append({
            # 打折后的价格
            "price_r": liDom.find(attrs={"class": "price_r"}).text,
            # 原价
            "price_n": liDom.find(attrs={"class": "price_n"}).text,
            "name": liDom.find(attrs={"class": "name"}).text,
            "author": liDom.findAll(attrs={"class": "publisher_info"})[0].text,
            "press": liDom.findAll(attrs={"class": "publisher_info"})[1].text,
        })
    pageSum += 1

# 将信息写入到txt文档当中
print('开始写入文件')
file = open("C:\\Users\\BJB-314\\Desktop\\test\\book.txt", 'w+')
file.write(json.dumps(bookList))
