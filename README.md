# StartupArch
项目目标：提供一个可复用的、供全新项目直接使用的、需要重构的项目可以参考的安卓示例项目。

架构设计上要达到的目标：

1，松耦合

2，应用分层

3，方便自动化测试


#### 引入的第三方库
#####   --依赖注入
    [dagger2](http://google.github.io/dagger/)

#####   --网络模块
    [okhttp3](https://github.com/square/okhttp)
    [retrofit 2](https://github.com/square/retrofit)

#####   --EventBus
    [otto](http://square.github.io/otto/)

#####   --数据库
    [greenDAO](https://github.com/greenrobot/greenDAO)


#### 扩展阅读
《Tasting Dagger 2 on Android》[原文](http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/) [译文](http://www.jianshu.com/p/c310618b23a6)
