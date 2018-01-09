##启动RabbitMQ：
 + net stop RabbitMQ && net start RabbitMQ

## RabbitMQ
### Exchange
 + Direct 直接式
 + Fanout 广播式
 + Topic  主题式
 
## Publisher Confirms 发布确认
 + 当一个瞬间消息进入消息队列时，说明RabbitMQ确定已收到该发布消息。
 + 当一个持久化消息被保存到磁盘，或者在每个队列上被消费时，说明RabbitMQ确定已收到该发布的持久消息。
 + 当一个瞬间消息被发现无法路由时，RabbitMQ直接确定已收到该发布消息。