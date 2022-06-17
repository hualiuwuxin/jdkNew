/**
 *
 * 1 一个项目，或者说一个jar包只能被属于一个模块，
 * 2 如果没有配置模块就是全部使用(没有配置包含2种，引用方和被引用方)
 * 3 使用模块相当于值暴露一部分需要对外提供服务的类，没有暴露的类不可见（我有两个包，A,B，但是值暴露A，那么引用方如果也使用模块化管理，就只能看到A，看不到B）
 * 4 jdk9 以后 所有的  java jar包前面都爆了一层 moduleName
 * 5 可以认为模块是 一个jar包下面，一组对外暴露服务的包 的别名
 * 6 一般模块的命名就是 这些包的公共前缀，或者公共前缀+能代表这些包的一个名字
 *
 * @author ZHANGYUKUN
 * @date 2022/6/16
 */
module com.lomi.jdkOne {
    exports com.lomi.jdk9One1;
}

