# JavaEE头歌

## SSM—Spring部分

### Spring—初体验

### Spring——AOP

#### 第1关：动态代理

LogProxy.java

```java
//创建三个参数：目标类的类加载器loader、目标类实现的接口的类类型interfaces、invocationhandler类型对象h
/*===============begin===============*/
ClassLoader loader = target.getClass().getClassLoader();
Class<?>[] interfaces = target.getClass().getInterfaces();
InvocationHandler h = new InvocationHandler() {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("The Method move 开始执行……");
        Object result = method.invoke(target,args);
        System.out.println("The method move 执行结束……");
        return result;
    }
};
//===============end===============//
```

Main.java

```java
// 创建Car目标类对象car；创建LogProxy对象logProxy；获取目标类对象car的代理对象movableProxy；代理类对象调用move方法
// ===============begin=============== //
Car car = new Car();
LogProxy logProxy = new LogProxy(car);
Movable movableProxy = (Movable)logProxy.getProxy();
movableProxy.move();
// ===============end===============//
```

#### 第2关：AOP面向切面编程

LoggingAspect.java

```java
//添加组件和切面注解
//===============begin===============
@Component
@Aspect
//================end================


//添加返回通知，切入点表达式为step2.ArithmeticCalculator下的所有方法，带返回值；
//通知信息为“INFO: The method 方法名 return result 返回值”
//===============begin===============
@AfterReturning(value = "execution(public int step2.ArithmeticCalculator.*(..))", returning="result")
public void afterMethodReturing(JoinPoint joinPoint,Object result){
    String methodName = joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();
    System.out.println("INFO: The method " + methodName + " return result " + result);
}
//================end================
```

Main.java

```java
//IoC容器中获取ArithmeticCalculatorImpl类型对象，调用add(10,20)方法
//===============begin===============
ArithmeticCalculator arithmeticCalculator = cxt.getBean("arithmeticCalculatorImpl",
                                                        ArithmeticCalculator.class);
arithmeticCalculator.add(10, 20);
//================end================
```

### Spring—JDBC

#### 第1关：使用外部属性文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">

	<!-- 获取外部属性文件中的参数，外部配置文件为db.properties -->
	<!-- ===============begin=============== -->
    <bean class="org.springframework.context.support.PropertySourcesPlaceholderConfigurer">
        <property name="location" value="classpath:db.properties"></property>
    </bean>
	<!-- ===============begin=============== -->
	
	<!-- bean配置：配置c3p0连接池数据源，id为ds，参数值参照db.properties外部配置文件 -->
	<!-- ===============begin=============== -->
	<bean id="ds" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driverclass}"></property>
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="user" value="${jdbc.user}"></property>
        <property name="password" value="${jdbc.password}"></property>
        <property name="initialPoolSize" value="${jdbc.initsize}"></property>
        <property name="maxPoolSize" value="${jdbc.maxpoolsize}"></property>
        <property name="minPoolSize" value="${jdbc.maxpoolsize}"></property>
    </bean>
	<!-- ===============begin=============== -->
</beans>
```

#### 第2关：JdbcTemplate

springjdbc/conf/spring-jdbctemplate.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
	<!-- 组件扫描 -->
	<context:component-scan base-package="step2"></context:component-scan>
	<!-- 获取外部属性文件中的参数 -->
	<context:property-placeholder location="classpath:db.properties"/>
	
	<!-- bean配置：配置连接池数据源 -->
	<bean id="ds" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="driverClass" value="${jdbc.driverclass}"></property>
		<property name="jdbcUrl" value="${jdbc.url}"></property>
		<property name="user" value="${jdbc.user}"></property>
		<property name="password" value="${jdbc.password}"></property>
		<property name="initialPoolSize" value="${jdbc.initsize}"></property>
		<property name="maxPoolSize" value="${jdbc.maxpoolsize}"></property>
		<property name="minPoolSize" value="${jdbc.minpoolsize}"></property>
	</bean>
	
	<!-- 配置JdbcTemplate的bean，id为jdbcTemplate，数据源为上面的c3p0连接池数据源 -->
	<!-- ===============begin=============== -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="ds"></property>
	</bean>
	<!-- ===============begin=============== -->
</beans>
```

**springjdbc/src/step2/UserDao.java**

```java
/*
	 * 数据库服务器: 	mysql5.5
	 * 数据库表名: 		test.users
	 * 表的字段分别为:	id(int类型，primary key  auto_increment)
	 * 	          	name(varchar)
	 *            	password(varchar)
	 */
//1.根据id查询用户
//================begin================
public User getUserById(Integer id){
    String sql = "select * from test.users where id=?";
    RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
    User user = jdbcTemplate.queryForObject(sql,rowMapper,id);
    return user;
}
//=================end=================

//2.查询所有用户
//================begin================
public List<User> getAllUsers(){
    String sql = "select * from test.users";
    RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
    List<User> users =jdbcTemplate.query(sql,rowMapper);
    return users;
}
//=================end=================

//3.批量插入新记录，id字段自动生成，(name，password)两字段分别为("骆宾王","鹅鹅鹅")，("王勃","海内存知己")，顺序不能颠倒
//================begin================
public void addUsers(){
    String[] sqls= {"insert into test.users(name,password) values('骆宾王','鹅鹅鹅')"," insert into test.users(name,password) values('王勃','海内存知己')"};
    jdbcTemplate.batchUpdate(sqls);
}
//=================end=================

//4.参数为user，根据user属性值修改test.users表中的指定记录
//================begin================
public void updateUser(User user){
    String sql = "update test.users set name=?,password=? where id=?";
    jdbcTemplate.update(sql,user.getName(),user.getPassword(),user.getId());
}
//=================end=================

```

#### 第3关：Spring声明式事务

**springjdbc/conf/spring-tx.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd">
	<!-- 组件扫描 -->
	<context:component-scan base-package="step3"></context:component-scan>
	<!-- 获取外部属性文件中的参数 -->
	<context:property-placeholder location="classpath:db.properties"/>
	
	<!-- bean配置：配置连接池数据源 -->
	<bean id="ds" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="driverClass" value="${jdbc.driverclass}"></property>
		<property name="jdbcUrl" value="${jdbc.url}"></property>
		<property name="user" value="${jdbc.user}"></property>
		<property name="password" value="${jdbc.password}"></property>
		<property name="initialPoolSize" value="${jdbc.initsize}"></property>
		<property name="maxPoolSize" value="${jdbc.maxpoolsize}"></property>
		<property name="minPoolSize" value="${jdbc.minpoolsize}"></property>
	</bean>
	
	<!-- 配置JdbcTemplate的bean，id为jdbcTemplate，数据源为上面的c3p0连接池数据源 -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="ds"></property>
	</bean>
	
	
	<!-- 配置事务管理器==》配置开启事务注解==》配置aspectj自动代理-->
	<!-- ===============begin=============== -->
	<bean id = "transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="ds"/>
    </bean>
        <tx:annotation-driven transaction-manager="transactionManager"/>
        <aop:aspectj-autoproxy expose-proxy="true"/>
	<!-- ===============begin=============== -->
</beans>

```

**springjdbc/src/step3/service/BookShopServiceImpl.java**

```java
@Override
//编写买书方法，注解此方法为事务
//====================begin====================
@Transactional
public void buyBook(String isbn){
    //1.查询价格
    int price = bookShopDao.getPrice(isbn);
    //2.更新库存
    bookShopDao.updateBookStock(isbn);
    //3.更新账户
    bookShopDao.updateAccount("Tom",price);
}
//====================begin====================
```

###  Spring—IoC


## SSM—MyBatis部分

### MyBatis—初体验
#### 第1关：MyBatis helloworld 简单版
Main.Java
```java
//使用namespace.id方式执行指定的sql语句：根据UserMapper.xml,补全下面代码
//=====================begin======================
User user = sqlSession.selectOne("suibian.getUserById", 2);
//======================end=======================
```
#### 第1关. MyBatis helloword 接口版
helloworld/conf/EmployeeMapper.xml
``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 配置select语句：根据id在test.employees中查询
    1.sql映射文件与接口文件绑定: namespace为EmployeeMapper接口全类名
    2.id：  为接口中定义的方法名
    3.resultType：  为查询结果返回类型——Employee全类名
 -->
<!--================begin================-->
<mapper namespace="step2.EmployeeMapper">
	<select id="getEmployeeById" resultType="step2.Employee">
        select *
        from test.employees
        where id = #{id}
    </select>
</mapper>
<!--=================end=================-->
```
Main.Java
```java
/*3 获取EmployeeMapper接口的代理类对象mapper
 *  调用getEmployeeById(1001),返回对象名为employee
 *  将employee输出显示
*/
//================begin================
EmployeeMapper mapper =session.getMapper(EmployeeMapper.class);
// 4.代理类对象调用方法
Employee employee = mapper.getEmployeeById(1001);
System.out.println(employee);
//=================end=================
```
### MyBatis—增删改查
#### 通用UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace:与接口绑定，接口的全类名 -->
<mapper namespace="mapper.UserMapper">
	<!-- 第一关 查询
    select语句: 
			id : 方法名 selectUser
			resultType： user 或 bean.User
			sql语句:从test.users表中查询指定id的记录
	 -->
    <!--==================begin======================-->
    <select id="selectUser" resultType="user">
        select *
        from test.users
        where id = #{id}
    </select>
    <!--===================end=======================-->

	<!-- 第二关 插入
    insert语句: 
			id : 方法名 insertUser
			sql语句:向test.users表中插入一条新记录
	 -->
    <!--==================begin======================-->
    <insert id="insertUser" useGeneratedKeys="true" keyProperty="id">
        insert into test.users(user_name, password) value (#{userName}, #{password})
    </insert>	
    <!--===================end=======================-->
	
	<!-- 第三关 更新
    update语句: 
			id : 方法名 updateUser
			sql语句:根据id修改test.users表中记录的password字段值
	 -->
    <!--==================begin======================-->
    <update id="updateUser">
        update test.users set password=#{1} where id=#{0}
    </update>
    <!--===================end=======================-->
	
	<!-- 第四关 删除
    delete语句: 
			id : 方法名 deleteUser
			sql语句:根据user_name删除test.users表中的记录
	 -->
    <!--==================begin======================-->
    <delete id="deleteUser">
        delete from test.users where user_name=#{n}
    </delete>
    <!--===================end=======================-->
	
	<select id="selectAllUsers" resultType="user">
		select * from test.users
	</select>
</mapper>
```
#### 第1关查询
```java
// 查询测试：调用selectUser方法，查询id为3的user，并输出显示
// ================begin================
User user1 = userMapper.selectUser(3);
System.out.println(user1);
// =================end=================
```
#### 第2关插入
```java
// 插入测试：调用insertUser，插入用户user,用户的userName为“欧阳修”，用户的password为“人约黄昏后”
// ================begin================
User user2  =new User();
user2.setUserName("欧阳修");
user2.setPassword("人约黄昏后");
userMapper.insertUser(user2);
// =================end=================	
```
#### 第3关更新
```java
// 更新测试：调用updateUser方法，修改id为1记录的password为"曲项向天歌"
// ================begin================
userMapper.updateUser(1,"曲项向天歌");  
// =================end=================
```
#### 第4关删除
```java
// 删除测试：调用deleteUser方法，删除user_name为"卢照邻"的记录
// ================begin================
int count=userMapper.deleteUser("卢照邻");
// =================end=================	
```
### MyBatis—参数传递
#### 通用UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace:与接口绑定，接口的全类名 -->
<mapper namespace="mapper.UserMapper">
	<!-- 第一关：单参普通类型
		方法：User getUserByName(String name);
		参数：单个参数，普通类型（基本数据类型、包装类型和字符串类型
		参数获取方式：#{任意名} 习惯用参数名  #{name}
		要求：在test.users表中查询user_name 为 name的记录，返回类型为bean.User
	 -->
	<!-- ================begin================ -->
    <select id="getUserByName" resultType="bean.User">
        select * from test.users where user_name= #{user_name}
    </select>
	<!-- =================end================= -->
	
	<!-- 第二关：多参封装Bean类型
		方法：void changPasswordByUser(User user);
		参数：多个参数，手动封装到User中
		参数获取方式：#{User的属性名}
		要求：更新test.users表，根据id修改密码password
	 -->
	<!-- ================begin================ -->
	<update id="changPasswordByUser">
        update test.users set password=#{password} where id=#{id}
    </update>
	<!-- =================end================= -->
	
	<!-- 第三关：多参封装Map类型——自动
		方法：void changePasswordByIdAndPassword(Integer id,@Param("pwd")String password);
		参数：多个参数，自动封装到Map中
		参数获取方式：#{key}
		key： 0 1 2 3 ……  
			  或  param1  param2  param3…… 
			  或  @Param指定的key
		要求：更新test.users表，根据id修改密码password
	 -->
	<!-- ================begin================ -->
	<update id="changePasswordByIdAndPassword">
        update test.users set password=#{param2} where id=#{param1}
    </update>   
	<!-- =================end================= -->
	
	<!-- 第四关：多参封装Map类型——手动
		方法：void changePasswordByMap(Map<String,Object> map);
		参数：多个参数，手动封装到Map中
		参数获取方式：#{key}
		要求：更新test.users表，根据id修改密码password
	 -->
	<!-- ================begin================ -->
	<update id="changePasswordByMap">
        update test.users set password=#{password} where id=#{id}
    </update>
	<!-- =================end================= -->
	
	<!-- 第五关：多参封装Collection类型——手动
		方法：User getUserByList(List<Integer> ids);
		参数：多个参数，手动封装到Collection、List、set集合 或 数组中
		参数获取方式：索引初始值为0
			Collection集合   	#{collection[索引]}
			List集合 		#{list[索引]} 或  #{collection[索引]}
			Set集合			#{set{索引}} 或 #{collection[索引]}
			数组				#{array[索引]}
		要求：在test.users表中，根据集合中的索引值为3的元素id，查找User，返回类型为bean.User
	 -->
	<!-- ================begin================ -->
    <select id="getUserByList" resultType="bean.User">
        select * from test.users where id= #{list[3]}
    </select>
	<!-- =================end================= -->
	<select id="getUserById" resultType="bean.User">
		select * from test.users where id=#{id}
	</select>
</mapper>
```
#### 第1关：单参数—普通类型
```java
//调用getUserByName方法，查找表中姓名为"王勃"的记录，返回User类型对象，并输出显示
//================begin================
User user=userMapper.getUserByName("王勃");
System.out.println(user);
//=================end=================
```
#### 第2关：多参数—手动封装到bean
```java
//调用changPasswordByUser方法，将需要修改密码的用户的id和密码封装到一个User类型对象里
//修改id为1的用户密码为"曲项向天歌"
//调用getUserById(Integer id)方法，查询id为1的用户，输出显示修改后效果
//================begin================
User user1=new User();
user1.setId(1);
user1.setPassword("曲项向天歌");
userMapper.changPasswordByUser(user1);
User user=userMapper.getUserById(1);
System.out.println(user);
//=================end=================
```
#### 第3关：多参数—自动封装到Map
```java
// 调用changePasswordByIdAndPassword方法,修改id为2的用户密码为"得成比目何辞死"
// 调用getUserById(Integer id)方法，查询id为2的用户，输出显示修改后效果
// ================begin================
userMapper.changePasswordByIdAndPassword(2,"得成比目何辞死");
User user=userMapper.getUserById(2);
System.out.println(user); 
// =================end=================
```
#### 第4关：多参数—手动封装到Map
```java
/* 调用changePasswordByMap方法,修改id为3的用户密码为"天涯若比邻"
	* 调用getUserById(Integer id)方法，查询id为3的用户，输出显示修改后效果
    */
// ================begin================
Map<String,Object> map=new HashMap<String,Object>();
map.put("id",3);
map.put("password","天涯若比邻");
userMapper.changePasswordByMap(map);
User user=userMapper.getUserById(3);
System.out.println(user);
// =================end=================
```
#### 第5关：多参数—手动封装到集合或数组
```java
//调用getUserByList(List<Integer> ids)方法查询用户，返回值为User类型，并将查询结果输出显示
//================begin================
List<Integer> ids = new ArrayList<Integer>();
ids.add(1); ids.add(2); ids.add(3); ids.add(4);
User user=userMapper.getUserByList(ids);
System.out.println(user);
//=================end=================
```
### MyBatis—查询返回值

####  通用UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.UserMapper">

	<!-- 第一关：查询单行数据返回单个对象
        方法：User getUserById(Integer id);
        表名：test.users
        resultType: bean.User
     -->
    <!--================begin================-->
	<select id="getUserById" resultType="bean.User">
        select * from test.users where id=#{id}
    </select>
    <!--=================end=================-->
	
	<!--第二关：查询多行数据返回多个对象集合
        方法：List<User> getAllUsers();
        表名：test.users
        resultType:bean.User
     -->
	<!--================begin================-->
	<select id="getAllUsers" resultType="bean.User">
        select * from test.users
    </select>
    <!--=================end=================-->
    
	
	<!-- 第三关：查询单行数据返回Map
        方法：Map<String, Object> getUserMap(Integer id);
        表名：test.users
        resultType:map
    -->
    <!--================begin================-->
	<select id ="getUserMap" resultType="map">
        select * from test.users where id =#{id}
    </select>
    <!--=================end=================-->
	
	<!-- 第四关：查询多行数据，返回Map
        方法：@MapKey("userName")
	        Map<String,User> getUsersMap();
        表名：test.users
        resultType:bean.User
    -->
	<!--================begin================-->
	<select id="getUsersMap" resultType="bean.User">
        select * from test.users 
    </select>
    <!--=================end=================-->
	
    <!-- 第五关：查询返回单个普通类型
        方法：int getCount();
        表名：test.users
        resultType:int
    -->
	<!--================begin================-->
	<select id="getCount" resultType="int">
        select count(*) from test.users
    </select>
    <!--=================end=================-->
</mapper>
```

#### 第1关：查询单行数据返回单个对象

```java
//调用getUserById方法，查询id为2的用户，并输出显示
//================begin================
System.out.println(mapper.getUserById(2));
//=================end=================	
```

#### 第2关：查询多行数据返回多个对象的集合

```java
//调用getAllUsers()方法，返回users表中所有记录，返回类型为List<User>
//将返回值遍历输出
//================begin================
List<User> user = mapper.getAllUsers();
for(User u:user){
    System.out.println(u);
}
//=================end=================
```

#### 第3关：查询单行数据返回Map

```java
//调用getUserMap方法，查询id为3的用户，返回值类型为Map<String,Object> 
//key:字段名 value：字段值
//将返回值直接输出显示
//================begin================
Map<String,Object> map = mapper.getUserMap(3);
System.out.print(map);
//=================end=================
```

#### 第4关：查询多行数据返回Map

```java
//调用getUsersMap方法，查询users表中所有记录，返回值类型为Map<String,User> key:为userName
//将返回值遍历输出
//================begin================
Map<String,User> user4 = mapper.getUsersMap();
for(String s: user4.keySet()){
    System.out.println(s+":"+user4.get(s));
}
//=================end=================
```

#### 第5关：查询返回普通类型单值

```java
//调用getCount方法，查询统计users表中记录数，返回值类型为int
//将返回值输出显示
//================begin================
int count = mapper.getCount();
System.out.print("记录数："+count);
//=================end=================
```

### MyBatis—自定义映像

#### **通用UserMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.UserMapper">	
	<!-- 第一关：级联方式    查询结果字段名（column）<==>User属性名（property）
		方法： User getUserById1(Integer id);
	 -->
	 <!-- =======================begin======================= -->
    <select id="getUserById1" resultMap="userResultMap1">
       select u.id,u.user_name,u.age,a.id aid,a.province,a.city from test.user u,test.address a
        where u.aid=a.id and u.id=#{id}
    </select>
    <resultMap id="userResultMap1" type="bean.User">
        <id column="id" property="id"></id>
        <result column="user_name" property="userName"></result>
        <result column="age" property="age"></result>
        <result column="aid" property="address.id"></result>
        <result column="province" property="address.province"></result>
        <result column="city" property="address.city"></result>
    </resultMap>
	<!-- =========================end======================== -->
	
	<!-- 第二关：association    查询结果字段名（column）<==>User属性名（property）
		方法： User getUserById2(Integer id);
	 -->
	 <!-- =======================begin======================= -->
    <select id="getUserById2" resultMap="userResultMap2">
       select u.id,u.user_name,u.age,a.id aid,a.province,a.city from test.user u,test.address a
        where u.aid=a.id and u.id=#{id}
    </select>
    <resultMap id="userResultMap2" type="bean.User">
        <id column="id" property="id"></id>
        <result column="user_name" property="userName"></result>
        <result column="age" property="age"></result>
        <association property="address" javaType="bean.Address">
            <id column="aid" property="id"/>
            <result column="province" property="province"/>
            <result column="city" property="city"/>
       </association>
    </resultMap>
	<!-- =========================end======================== -->
	
	<!-- 第三关：分步查询    查询结果字段名（column）<==>User属性名（property）
		方法： User getUserById3(Integer id);
	 -->
	 <!-- =======================begin======================= -->
    <select id="getUserById3" resultMap="userResultMap3">
        select id,user_name,age,aid from test.user where id=#{id}
    </select>
    <resultMap id="userResultMap3" type="bean.User">
        <id column="id" property="id"></id>
        <result column="user_name" property="userName"></result>
        <result column="age" property="age"></result>
        <association property="address" select="mapper.AddressMapper.getAddressById" column="aid">
        </association>
    </resultMap>
	<!-- =========================end======================== -->
	
</mapper>
```

#### 第1关：自定义映射——级联方式

#### 第2关：自定义映射——association方式

#### 第3关：association分步查询

## SSM—SpringMVC