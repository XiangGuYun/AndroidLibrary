Java 多线程（六） synchronized关键字详解
https://www.cnblogs.com/mengdd/archive/2013/02/16/2913806.html
case1：在多线程下比较synchronize的方法与普通方法的区别
case2: 一个对象有同步方法A和B，当同步方法A被线程1执行时，线程2既无法访问同步方法A，也无法访问同步方法B。
说明同步方法获得的是对象的锁。
case3: 静态同步方法获得的是类的锁，即使是这个类的不同对象，也能起到互斥效果。
case4: 同步代码块与同步方法的主要区别是同步控制效果的粗细粒度不同。

java多线程（五）synchronized关键字修饰代码块
https://www.cnblogs.com/dolphin0520/p/3923167.html
case5：同步代码块（sync）拿到this（代码块执行对象）的锁
case6：同步代码块（sync）拿到class的锁
case7：同步代码块（sync）拿到object的锁

Java线程(八)：锁对象Lock-同步问题更完美的处理方式
http://blog.csdn.net/ghsau/article/details/7461369/
case8:Lock和读写锁的基本用法。
