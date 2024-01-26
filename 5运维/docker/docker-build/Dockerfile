#拉取centeros镜像
FROM centos:centos7
#设置作者
MAINTAINER zhouhelong

#设置工作目录
WORKDIR /usr/local
#拷贝nginx文件
COPY ./nginx-1.24.0.tar.gz   .
COPY ./jdk-8u401-linux-x64.tar.gz   .

RUN  echo "开始解压文件"

# 解压文件
RUN tar -zxvf nginx-1.24.0.tar.gz -C . \
    && tar -zxvf jdk-8u401-linux-x64.tar.gz -C . \
    &&    yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel pcre-devel vim  \
    &&  rm  ./nginx-1.24.0.tar.gz \
    &&  rm  ./jdk-8u401-linux-x64.tar.gz

RUN  echo "解压文件成功"

RUN  cd nginx-1.24.0    \
     && ./configure --prefix=/usr/local/nginx \
     && make && make install

#设置java环境变量
ENV JAVA_HOME /usr/local/jdk1.8.0_401
#将bin变量添加到path环境变量前面
ENV PATH $JAVA_HOME/bin:$PATH

RUN java -version
# 启动Nginx
CMD ["/usr/local/nginx/sbin/nginx", "-g", "daemon off;"]
