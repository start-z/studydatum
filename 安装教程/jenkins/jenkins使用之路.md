官网：
# 
# 1安装jenkins
将本文前例官网打开进入下载可下载war包用jar执行
![image.png](https://cdn.nlark.com/yuque/0/2022/png/29343762/1666940290546-7785d6ef-ff40-41aa-a0d8-3b266b026be7.png#clientId=u792e5929-b646-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=504&id=ua57f3975&margin=%5Bobject%20Object%5D&name=image.png&originHeight=630&originWidth=840&originalType=binary&ratio=1&rotation=0&showTitle=false&size=26111&status=done&style=none&taskId=u0e10ec74-1cf8-41db-abf7-e3f8a7f3679&title=&width=672)
```
-Dhudson.model.DownloadService.noSignatureCheck  //对应校验
-Dhudson.util.ProcessTree.disable //取消子进程构建完自动取消
 java -jar -Dhudson.model.DownloadService.noSignatureCheck=true   -Dhudson.util.ProcessTree.disable=true     ./jenkins.war  --httpPort=5657
```
