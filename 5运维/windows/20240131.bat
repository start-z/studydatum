@echo off
rem 设置编码
chcp 65001
title 2024-01-31-编写脚本
rem 输出目录结构
tree F:\zhou\开源项目or个人项目\windows-shell-study  /f  /a >tree.txt
for %%f  in (.) do echo %%f
echo  "我输出完目录了 请查看tree.txt文件"
if not exist ./tree.txt (
echo 文件创建失败
) else (
echo 文件创建成功  
type tree.txt
)
rem 标记循环
:while_loop
set /p username="请输入你的名字"
echo %username%
if %username% == zhou (
echo 输入正确
echo cmd窗口将在3秒后关闭
timeout /t 3
exit
) else (
echo 输入错误 
goto :while_loop
 )