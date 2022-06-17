/**
 *
 * 1 如果一个项目引用了一个jar，但是自身没有配置module-info.java，那么相当于使用了这个jar 的全部类
 * 2 如果一个项目引用了一个jar，但是自身配置module-info.java，那么必须在导入了这个 jar的 模块名才能使用里面的 暴露服务类
 *
 * @author ZHANGYUKUN
 * @date 2022/6/16
 */
module com.lomi.jdkTwo {
    requires com.lomi.jdkOne;
}

