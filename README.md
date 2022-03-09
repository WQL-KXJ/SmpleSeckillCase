## 秒杀案例
#### 涉及到的技术框架有：SpringBoot，Redis，MybatisPuls，RabbitMQ，Thmeleaf，Shiro，Mysql
#### 解决的秒杀问题：
##### 1，库存超卖：使用Redis的事务
##### 2，高并发：① 使用RabbitMQ进行流量削峰具体的数据库写操作载Consumer端进行实现  ② 对秒杀接口做限流(自定义注解实现)
##### 3，恶意请求：① 将实际的秒杀进行隐藏，每一个用户都有唯一的地址(UUID+MD5)  ② 验证码识别
#### 性能优化问题：
##### 1，对象缓存：将数据对象有Redis做中间缓存
##### 2，数据库和缓存一致性问题：①每一次写操作把对应的缓存删除 ② 设置过期时间 
##### 3，模板页面缓存：项目用Thymeleaf模板引擎，使用ThymeleafEngine进行手动渲染，然后保存到Redis中
##### 4，Mybatis二级缓存：实现了Mybatis提供Cache接口，使用Redis作为缓存数据库
##### 5，Shiro缓存：使用Shiro整合Redis
#### 注：项目主要作为案例使用
####
