# 深度解析Netty源码，助力Java开发人员升职加薪
## 第1章 课程介绍
- Netty是什么？
    - 异步事件驱动框架，用于快速开发高性能服务端和客户端
    - 封装了JDK底层BIO和NIO模型，提供高度可用的API
    - 自带编解码器解决拆包粘包问题，用户只用关心业务逻辑
    - 精心设计的reactor线程模型支持高并发海量连接
    - 自带各种协议栈让你处理任何一种通用协议都几乎不用亲自动手
- 有必要学吗？
    - 各大开源项目选择Netty作为底层通信框架
    - 更好地使用，少走弯路
    - 遇到bug？单机连接数上不去？性能遇到瓶颈？如何调优？
    - 详解reactor线程模型，实践中举一反三
    - 庞大的项目是如何组织的，设计模式，体验优秀的设计
    - 阅读源码其实没那么困难
- 怎么学？
    - 自己摸索不如前人指路
    - 对应socket编程，逐个切入
    - 踩过的坑，积累的经验总结毫无保留的分享，节省大量的时间
    - 调试，分析技巧
- 能达到什么水平？
    - 掌握Netty底层原理，轻松解决各类疑难杂症
    - 面试加分，互联网各大架构师的职位，升职加薪，以一当三
    - 给官方提issue
    - 实现一个简易版的Netty
    - 开启阅读源码之旅，根本停不下来
- 技术储备
    - 熟悉java基础，熟悉多线程
    - 熟悉tcp原理，nio
    - 使用过Netty
## 第2章 Netty基本组件
- Netty基本组件
    - NioEventLoop
    - Channel
    - Pipeline
    - ChannelHandler
    - ByteBuf
## 第3章 Netty服务端启动
- 两个问题
    - 服务端的socket在哪里初始化？
    - 在哪里accept连接？
- Netty服务端启动
    - 创建服务端Channel
    - 初始化服务端Channel
    - 注册selector
    - 端口绑定
- 服务端启动核心路径总结
    - newChannel
    - init
    - register
    - doBind
## 第4章 NioEventLoop
- 三个问题
    - 默认情况下，Netty服务端起多少线程？何时启动？
        - 两倍cpu核数
    - Netty是如何解决jdk空轮询bug的？
        - 如果当前阻塞的select操作，实际上并没有花那么长时间，那么有可能这次触发一次空轮询的bug，如果这样的参数达到一个阈值（521），则重建一个selector，将之前selector上所有的key移交到当前selector上
    - Netty如何保证异步串行无锁化？
        - 判断当前线程是否是当前事件循环的线程，如果不是当前事件循环的线程，则扔到任务队列中去执行
## 第5章 新连接接入
- 两个问题
    - Netty是在哪里检测有新连接接入的？
    - 新连接是这样注册到NioEventLoop线程的？
- Netty新连接接入处理逻辑
    - 检测新连接
    - 创建NioSocketChannel
    - 分配线程及注册selector
    - 向selector注册读事件
- Netty中的Channel的分类
    - NioServerSocketChannel
    - NioSocketChannel
    - Unsafe
## 第6章 pipeline
- 三个问题
    - netty是如何判断ChannelHandler类型的？
    - 对于ChannelHandler的添加应该遵循什么样的顺序？
    - 用户手动触发事件传播，不同的触发方式有什么样的区别？
## 第7章 ByteBuf
- 三个问题
    - 内存的类别有哪些
    - 如何减少多线程内存分配之间的竞争
    - 不同大小的内存是如何进行分配的
- ByteBuf分类
    - Pooled和Unpooled
    - Unsafe和非Unsafe
    - Heap和Direct
- 内存规格介绍
    - tiny 0-512B
    - small 512B-8k
    - normal 8k-16M
    - hug >16M
## 第8章 Netty解码
- 两个问题
    - 解码器抽象的解码过程
    - netty里面有哪些拆箱即用的解码器
- ByteToMessageDecoder解码步骤
    - 累加字节流
    - 调用子类的decode方法进行解析
    - 将解析到的ByteBuf向下传播
## 第9章 Netty编码及writeAndFlush()
- 一个问题
    - 如何把对象变成字节流，最终写到socket底层？
## 第10章 Netty性能优化工具类解析
- Netty两大性能优化工具类
    - FastThreadLocal
    - Recycler
## 第11章 Netty设计模式应用
## 第12章 Netty高性能并发优化
- Netty高并发性能调优
    - 单机百万连接调优
    - Netty应用级别性能调优
## 第13章 课程总结