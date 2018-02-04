1. 在项目中添加com.tryin包
	在该包中添加WebServer类,并定义基本结构.
	实现与客户端(浏览器)的连接.使用ServerSocket

2. 浏览器在连接服务端后,会发送HTTP请求数据过来.
	在ClientHandler中,通过Socket获取输入流,然后
	读取客户端发送过来的Http请求并输出到控制台查看
	内容.(要求熟悉HTTP协议中请求的格式与规则).

3. 完成相应客户端页面的工作
	- 在项目目录下新建一个目录webapps用来存放所有资源
	- 在webapps下新建一个子目录myweb,用来存放测试页面
	- 在ClientHandler中通过HttpRequest获取客户端要请求的资源,
	并将该资源响应给客户端   
	(这一步需要掌握HTTP协议中的响应格式). 

4. 将webapps目录作为所有资源目录的根 
	比如客户端在地址栏输入的地址为:
	http://localhost:8088/myweb/index.html
	那么request里请求行中url部分为:/myweb/index.html
	对应的相当于从webapps目录开始查找,即:
	webapps/myweb/index.html
	http://localhost:8088/index.html
	对应webapps/index.html

5. 重构代码:
	在http包中定义一个新的类:HttpResponse,表示一个具体的响应
	重构ClientHandler代码,将响应的具体细节移动至HttpResponse
	中完成.ClientHandler只负责将响应信息设置到HttpResponse
	中即可.

