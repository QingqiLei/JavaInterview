



### Java  SE

#### 1. java C++

1. java 为解释性语言,C++ 为编译型语言. 程序源代码经过Java 编译器编译为字节码,然后由jvm解释执行, C++ 源代码经过编译和链接后生成可执行的二进制代码.java 执行速度比C++慢,但是Java 能跨平台.
2. java 纯面向对象
3. java 没有指针,没有多重继承,不需要管理内存,不支持运算符重载,没有goto
4. 面向对象三个特点: 封装, 继承, 多态

#### 2. main

```
static public void main(String[] args)
public static final void main(String[] args)
static public synchronized void main(String[] args)
```

同一个.java 文件中可以有多个man 方法, 虽然每个类中都可以定义main 方法,但只有与文件名相同的用public 修饰的类中的main方法才被执行.

#### java 程序初始化顺序

三个原则: 静态对象,父类优先于子类,按照成员变量的定义顺序

父类静态变量, 父类静态代码块, 子类静态变量, 子类静态代码快, 父类非静态变量, 父类非静态代码块, 父类构造函数, 子类非静态变量, 子类非静态代码快, 子类构造函数.

#### java 作用域

变量有三种: 成员变量, 静态变量, 局部变量

成员变量当类被实例化的时候,成员变量就会在内存中分配空间并初始化. 静态变量不依赖于特定实例.

|           | 当前类 | 同一package | 子类 | 其他package |
| --------- | ------ | ----------- | ---- | ----------- |
| public    |        |             |      |             |
| protected |        |             |      | 不          |
| default   |        |             | 不   | 不          |
| private   |        | 不          | 不   | 不          |

#### 构造函数

1. 必须与类有相同的名字,不能有返回值(不能为void)
2. 也可以有方法和构造函数重名, 可以overload

#### 接口

1. 接口中的方法都是抽象的, 都使用 public static void 修饰.

#### 值传递和引用传递

基本数据类型: 值传递

对象: 引用传递

#### 反射

得到一个对象所属的类,获取一个类的所有成员变量和方法,在运行时创建对象,在运行时调用对象的方法,

Class a = Class.forName("Sub")

Base b = (Base)c.newInstance();

#### 创建对象的方法

new 反射 clone() 反序列化

#### package

在不同package中的类可以有相同的名字

#### 继承

子类只能继承父类非私有方法

#### 组合和继承

组合是在新类中创建原有类的对象. 组合和继承都允许在新的类中设置子对象, 组合是显式的,继承是隐式.

car 是vehicle 的一种, tire 是轮胎对象,一个car 有四个tire.

除非两个类之间是is - a 关系, 否则不要轻易使用继承. 

#### 多态

1. overload 重名方法, 不同的参数个数, 不同的参数类型,不同的参数顺序, 不能以返回值来区分
2. override 方法的覆盖, 相同的函数名,参数,返回值,抛出更多异常,-

成员变量无法实现多态

```java
class Base{
    public int i = 1;
    
}
class Derived extends Base{
    public int i = 2;
}
public class Test{
    public static void main(String[] args){
        Base b = new Derived();
        System.out.println(b.i);  // 1
    }
}
```



b.i 指的是Base 类中定义的 i

```java
class Super{
    public int f(){
        return 1;
    }
    
}
public class SubClass extends Super{
    public fload f(){
        return 2f;
    }
}
public static void main(String[] args){
    Super s = new SubClass();
    System.out.println(s.f());
}
```

这个override 编译错误,不能以返回值区分.虽然父类和子类函数有着不同的返回值,但是函数名字相同.

#### 抽象类和接口

1. 只要包含一个抽象方法的类就必须声明为抽象类,
2. 如果抽象类的子类没有为父类提供所有抽象方法的具体实现, 那么也是抽象类.
3. 接口中所有方法都是抽象的, 可以通过接口间接实现多重继承, 
4. 接口中方法只有定义, 抽象类中方法可以有实现
5. 接口 has - a  , 抽象类  is - a
6. 接口中成员变量默认为public static final, 必须赋初值, 抽象类可有有自己的成员变量.
7. 抽象类的抽象方法不能用private, static, synchronized, native等修饰不能带花括号,要带分号.
8. 接口的成员变量默认是 public static final, 接口中的方法只能用关键字public abstract 来修饰.

#### 内部类

局部内部类(类中方法中的类), 静态内部类, 成员内部类, 匿名内部类

```
class outerClass{
    static class innerClass{}
    }
class outerClass{
    class innerClass{}
}
class outerClass{
    public void memberFunction(){
        class innerClass{}
    }
}


```



静态内部类: 被声明为static 的内部类, 不依赖于外部类实例而被实例化,只能访问外部类中的静态成员和静态方法.

如果静态内部类去掉static 关键字,就变为成员内部类.

局部内部类指的是定义在一个代码块内的类,只能访问方法中定义为final 的类型的局部变量,

匿名内部类 必须继承其他类或实现其他借口.



在非静态内部类中不能定义静态成员, 

```
public class OuterClass{
    private int dl = 1;
}


class InnerClass{
    public static int methoda(){return dl;}
}
public class InnerClass{
    static int methoda() { return dl;}
}
```

静态内部类不能访问外部类的非静态成员

```
static class InnerClass{
    protected int methoda(){return dl;}
}
```

#### 获取父类的类名

this.getClass().getSuperClass().getName()

#### super

当子类构造函数需要显示调用父类构造函数时,super() 必须为构造函数中的第一条语句.

#### 标识符

变量名,函数名,数组名都是标识符

字母,数字,_ $, 第一个字符不能是数字,

#### 跳出多重循环

```
out:
for(int i = 0; i < 5; i++){
    for(int j = 0; j < 5; j++){
        if(j >= 2)
        break out;
    }
}
```

#### final finally finalize

final 指的是引用的不变性,不关心指向对象内容的变化.所以被final修饰的变量必须初始化, 1. 定义时初始化,2. 初始化块中初始化,但不可在静态初始化块中初始化 3. 静态final 成员变量可以在静态初始化块中初始化, 但不可在初始化块中初始化. 4 .在类的构造器中初始化, 但静态final 成员变量不可以在构造器中初始化.当一个类被声明为final时, 不能被继承,所有方法不能被重写. 一个类不能既被声明为abstract 又被声明为final.

finally: 作为异常处理的一部分, 只能用在try/catch 语句中.

finalize 是Object 类的方法,在垃圾回收器执行时会调用被回收对象finalize 方法,可以覆盖这个方法来实现对其他资源的回收.

#### static

1. 为特定的数据或对象分配单一的存储空间
2. 实现某个方法或属性与类关联在一起(不和对象关联)

静态变量属于类,对于静态变量的引用有两种方式, 类.静态变量, 对象.静态变量.

static 方法,不用创建对象就能调用.

static 方法中不能使用this , super 关键字,不能调用非static方法.

static 代码块只会被执行一次.

不能在成员函数内部定义static 变量(包括静态函数内部)

#### switch

可以使用int, Integer, short, byte, short, char String

#### clone

在实际编程过程中, 经常要遇到这种情况, 有一个对象A, 在某个时刻A中包含了一些有效值, 此时刻可能会需要一个和A完全相同新对象B,  并且此后对B任何改动都不会影响到A中的值,也就是说, A与B是两个独立的对象,但B的初始值是由A对象确定的, 在java语言中, 用简单的赋值语句是不能满足这种需求的, 要满足这种需求虽然有很多途径, ranking实现clone() 是其中最简单, 也是最高效的手段

#### new 一个对象的过程和cline 一个对象过程的区别

new 操作符本意是分配内存, 程序执行到new 操作符时, 首先去看new 操作符后面的类型, 因为知道了类型, 才能知道要分配多大的内存空间, 分配完内存之后, 在调用构造函数, 填充对象的各个域, 这一步叫做对象的初始化, 构造方法返回后, 一个对象创建完毕, 可以把他的引用发布到外部, 在外部就可以使用这个引用操纵这个对象.

clone 在第一步是和new 相似的, 都是分配内存, 调用clone 方法时, 分配的内存和原对象相同, 然后再使用原对象中对应的各个域, 填充新对象的域, 填充完成后, clone 方法返回, 一个新的相同的对象被创建, 同样可以把这个新对象的引用发布到外部

#### clone 复制对象和复制引用, 深复制和浅复制



#### volatile

编译器会提高程序的运行效率,将经常被访问的值缓存起来,程序重复读取这个值的时候,会在缓存中读取,不会读取原始值. 

当遇到多线程时,变量的值可能被其他线程改变了,缓存的值不会改变,造成读取的值和实际的变量值不一致.

使用volatile 修饰的值,系统每次用到都会读取原始值

```
public class MyThread implements Runnable{
    private volatile Boolean flag;
    public void stop(){
        flag = false;
        
    }
    public void run(){
        while(flag){
            ;// do something
        }
    }
}
```

#### java 提供了哪些基本数据类型

int 4 0

short 2 0

long 8 0l

byte 1 0

float 4 0.0f

double 8 0.0

char 2 u0000

boolean 1 false

short int long double float

byte char boolean

java 默认声明的小数是double 类型的.不是float 类型.

Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE

#### 值传递,引用传递

```
public class Test{
    public static void changeStrinbBuffer(StringBuffer ss1, StringBuffer ss2){
        ss1.append(" world");
        ss2 = ss1;
    }
    public static void main(Stirng[] args){
        Integer a = 1;
        Integer b = a;
        b++;
        System.out.println(a);  // 1
        System.out.println(b);   // 2
        StringBuffer s1 = new StringBuffer("hello");
        StringBuffer s2 = new StringBuffer("hello");
        changeStringBuffer(s1,s2)
        System.out.println(s1 +", "+s2);  // hello world, hello 
    }
}
```

在执行完b++ 时,会创建一个新值为2 的Integer 赋值给b, b 和a 已经没有任何关系了

执行完ss2 = ss1 时,修改ss2的值对s2 毫无影响.

#### byte相加

byte short char 运算, 会把这些类型的变量强制转换为int 类型. 最后得到的值也是int 类型.

`short s1 = 1; s1 = s1 +1` 错误

`short s1 = 1; s1 = (short) (s1 + 1)` 对

`short s1 =1; s1 += 1` 对

#### round cell floor

round 四舍五入

ceil 向上取整

floor 向下取整.

#### `>>` 和`>>>`

对于正数,相同,负数,不同.

`>>`负数, 高位补1, `>>>` 正数和负数,高位都补0

char byte short 类型移位的时候,会转化为int 类型.当移动位数超过32, 没有意义, 所以java 内部自动取32的余数 a >> n 等价于 a >> (n % 32 ) 

#### 汉字

```java
public class Test{
    public static void judgeChineseCharactor(String str){
        String regEx = "[\u4e00 - \u9fa5]";
        if(str.getBytes().length == str.length())
        	System.out.println("there is no Chinese letter");
        else {
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while(m.find()){
                System.out.println(m.group(0) + " ");
            }
        }
    }
}
```

#### new String("")

new String("a") 创建了几个对象, 一个或者两个.

#### == equals hashcode

==: 基本数据类型, 比较数值是否相同, 引用类型: 比较内存地址是否相同

equals 从Object 继承过来,默认使用 ==, String 类型, 比较的是string 的内容. 

hashCode 也是从object 继承过来.

如果equals 为true, 则hashcode 一定为true

如果hashcode 不同, equals 一定不同.

```java
String s = "abc";;
String s1 = "a" + "bc";
System.out.println(s == s1); // true
```

"a"+"bc"在编译器中就被变为 "abc "了

#### 数组初始化方式

```
int[] a = new int[5];
int[] a = {1,2,3,4,5};
int[] a = new int[]{1,2,3,4,5};
```

#### finally

1 finally 在return 前执行. 

2 如果finally 中有return , 会覆盖外部的return 语句.

3 在finally 块中改变return的值对返回值没有任何影响, 而对引用类型的数据会有影响.

#### Error Exception

java 提供两种异常类,分别为Error, Exception, 他们有共同的父类Throwable, 

Error 是严重的错误. outofMemoryError, ThreadDeath 

Exception 可恢复的异常.

出现运行时异常后, 系统会把异常一直往上层抛出, 直到遇到处理代码为止, 如果是多线程就用Thread.run() ,如果是单线程,就用main() 抛出.

ArithmeticException 属于运行时异常,编译器没有强制对其进行捕获并处理,编译可以通过, 如果是IOException, 属于检查异常, 编译器强制去捕获此类型的异常.

#### IO流

装饰者模式可以在运行时动态地给对象添加一些额外的职责,更具有灵活性.

 

```java
class MyOwnInputStream extends FilterInputStream{
    public MyOwnInputStream(InputStream in)
        super(in);
    public int read() throws IOException{
        int c = 0;
        if( (c = super.read()) != -1){
            if(Character.isLowerCase((char)c))
            return Character.toUpperCase((char)c);
            else if (Character.isUpperCase((char)c)
            return Character.toLowerCase((char)c);
            else return c;
        }
        else {
             return -1;
        }
                     }
}
                     
public class Test{
    public static void main(String[] args){
        int c;
        try{
            InputStream is = new MyOwnInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
            while((c = is.read()) >= 0)
                System.out.prinltn((char)c);
            is.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
```

常见的流有两种, 字节流和字符流, 字节流继承于 InputStream OutputStream

字符流继承于Reader, Writer

#### NIO (Nonblocking IO)

传统IO : 客户端对服务器端发起请求,accept 就会阻塞(阻塞指的是暂停一个线程的执行以等待某一条件的发生, 如某资源就绪) .如果连接成功, 数据还没有准备好, 对read的调用同样会阻塞.

#### java 程序执行过程

java 代码 -> 字节码-> 代码的装入->代码的校验->代码执行

装入代码由类装载器完成.

java 字节码的执行分为两种, 编译执行,解释执行, 编译执行: 将字节码编译成机器码,再执行机器码,

解释执行,每次解释并执行一小段代码.通常采用的是解释执行. 

#### 内存泄露

```java
Vector v = new Vector(10);
for(int i = 1; i < 10; i++){
    Object o = new Object();
    v.add(o);
}
```

#### 堆和栈

栈的存取速度更快,栈的大小和生存期必须是确定的,因此缺乏一定的灵活性.而堆可以在运行时动态分配内存,生存期不用告诉编译器.这个导致存取速度慢.

#### hashmap hashtable treemap weakhashmap

hashmap 允许键值 为null

hashtable 不允许键 或者 值 为null 

#### 进程和线程

各个线程之间共享程序的内存空间,以及一些进程级的资源,但是各个线程拥有自己的栈空间.

与进程相比,县城的建立和切换开销更小

多CPU 或者多核CPU 本身就具有执行多线程的能力.

#### 多线程

1. 继承Thrad 类, 重写run() 方法
2. 实现Runnable 借口, 实现run() 方法   (推荐)

#### start run

start() 能够异步地调用run 方法,但是直接调用run() 是同步的.

如果使用run() 那么t1.run() 后面的System.out.println() 代码只能在线程结束后才能够执行.

#### 多线程同步的方法

1. synchronized 方法,每个对象都有一个对象锁与之相关联, 该锁表明对象在任何时候只允许被一个线程所拥有,当一个县城调用对象的一段sybchronized代码时, 需要先获取这个锁,然后去执行相应的代码,执行完后释放锁.
2. wait() notify():  当一个线程执行synchronized 代码的时候,调用对象的wait() 方法, 释放对象锁, 进入等待状态,并且可以调用notify() 方法或者notifyAlly() 通知等在等待的线程.
3. Lock jdk 5 后提供了一个ReentrantLock 锁.

#### sleep() wait()

sleep() 让线程暂停执行一段时间,时间一到,自动恢复.比如时钟1s 打印一次当前时间. 不会释放自己所占有的锁

wait() 会使当前拥有对象锁的进程等待,直到其他线程调用notify() 方法. 不过也可以指定一个时间,让他自动醒过来. 会释放自己所占有的锁.

由于sleep() 不会释放锁,更容易发生死锁,

Thread.wait() 可以设置时间

sleep(1000), 当睡眠时间结束后,线程会返回到可运行状态, 不是运行状态,还需要等待CPU 调度执行,sleep() 方法不能保证线程水边到期后开始执行.

#### synchronized lock

synchronized 使用Object 对象本身的notify wait notifyAll 调度机制, 而Lock 使用Condition 进行线程之间的调度.

1. 用法不同, synchronized 既可以加在方法上,也可以在特定代码块上,Lock需要显示指定起始位置和终止位置.
2. synchronized不会因为异常而没有释放锁,Lock 需要手动解锁,在finally 块中释放 

#### 事务处理

禁止自动提交setAutoCOmmit(false), 然后就可以把多个数据库操作的表达式作为一个事务,在操作完成后调用commit() 方法实现整体提交,如果其中一个表达式操作失败,就会抛出异常而不会调用commit() 方法.在这种情况下,就可以在异常捕获的代码块中调用rollback() 方法进行事务回滚.

1. TRANSACTION_NONE JOB 不支持事务
2. TRANSACTION_READ_UNCOMMITTED 未提交读,读脏数据
3. TRANSACTION_READ_COMMITTED  已提交读,读取未提交数据是不允许的.
4. TRANSACTION_REPEATABLE_READ  可重复读
5. TRANSACTION_SERIALIZABLE 可序列化, 

原子性: 原子性要求事务必须被完整执行

一致性: 

隔离性

持久性

#### Class.forName

作用是把类加载到JVM中,它返回一个相关的Class对象,并加载这个类,JVM会执行该类的静态代码块.

#### 数据库连接池

建立与数据库的链接是一个耗时的操作,,数据库连接个数是有数的,用户经常经理连接却忘记释放.

#### IOC

控制反转,依赖注入,是一种降低对象之间耦合关系的设计思想.



### 海量数据处理

#### Hash

hash 主要用来进行快速存取, 在O(1)时间内查找到目标元素,或者判断是否存在.

#### bit-map

找到最大值, 创建一个数组, 便利数据,把数据作为下标,在数组中找到的下标,设置为1.

#### bloom filter

m 位的数组, 定义k个hash函数,每个函数都可以将元素映射到数组中的某一位,当向集合中插入一个元素,将求出k 个hash 值,将数组中k 个hash 值下标设置为1, 当查找某个元素是否在集合中的时候,先计算k个hash 值,如果k 个hash 值下标在数组中都为1, 则有可能在集合中,如果有的不是1, 则一定不在集合中.

#### 倒排索引

主要用在 文档检索

正向索引: 存储每个文档的单词的列表

倒排索引: 单词指向了包含它的文档.

#### 外排序

当需要排序的对象很多,可以一部分一部分地调入内存处理.



