## JacocoX Android增量代码覆盖率工具

代码覆盖率工具可以让我们查看，程序运行过的痕迹，所以可以在测试过程中查看测试的覆盖范围。
而Jacoco是Android gradle插件内置的一个全量代码覆盖率工具，它会对我们所有打包到apk的class文件进行插桩，然后回对我们的数据会生成一个html文件，打开会是这样，绿色的表示执行过了

![img_3.png](img_2.png)

但是我们每个版本也只会写一部分代码，之前的代码没有动过我们其实可以不用关心他的测试情况，毕竟全部输出可能有几百几千个文件，所以这里我们就要过滤当前版本的代码数据。

JacocoX就是一个会根据git diff来获取增量代码的插件，可以对比不同分支或相同分支的不同版本（自己配置），想了解[JacocoX的原理](JacocoX.md)

### 如何使用

#### 1、在工程目录的 build.gradle添加

```groovy
    classpath "io.github.ckenergy:jacocox-plugin:$version"
```
#### 2、 baseJacoco.gradle 、 jacoco.gradle 将这两个文件添加到app目录下

like：

![img.png](img.png)

#### 3、在 app build.gradle 添加
```groovy
apply from: 'jacoco.gradle'
```
#### 4、添加 jacocoreport 到工程目录

like：

![img_1.png](img_1.png)

#### 5、添加 git compareBranch 或 compareTag，只能添加一个，都有的情况下compareBranch优先级更高，如果都没写，默认对比master或main分支
```groovy
jacocoX {
    //compareBranch = "master"
    compareTag = "****"
    infoFile = jacocoInfoFile
    printLog = true
    enable = true//插件内部判断了debug才会生效，所以这里一直写true也没关系
}
```

#### 6、将jacocoreport文件内的文字替换你项目的applicationId 

![img_2.png](img_2.png)

#### 然后就可以运行你的项目和测试了, 测试完毕后输入在命令行输入./jacocoreport , 就会生成报告
![img_3.png](img_3.png)

想了解[JacocoX的原理](JacocoX.md)