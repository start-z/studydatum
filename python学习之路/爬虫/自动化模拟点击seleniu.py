from chromedriver_py import binary_path

from selenium import webdriver
from selenium.webdriver.chrome.service import Service

options = webdriver.ChromeOptions()
options.add_experimental_option("detach", True)
chrome = webdriver.Chrome(options=options, service=Service(executable_path=binary_path))
chrome.get("https://www.baidu.com")
chrome.implicitly_wait(70)
baiduInput = chrome.find_elements(value="kw")
baiduInput[0].send_keys("python自动化测试")
chrome.find_element(value="su").click();
