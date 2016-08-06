# Spinner_Three_Cascaded
Spinner下拉列表框的三级联动
本次Demo将一个Json格式的txt文本文件放到了Assets目录下，注意，在Android Studio中Assets文件夹是与res，java文件平级的，在main目录下

1.新建了一个工具包，在包中创建从Assets中得到Json串（getResource().getAssets().open(fileName)）和解析Json串的静态方法，创建好对应的实体类接收解析出来的Json串内容
2.在布局中添加三个Spinner控件
3.在主函数中实例化控件，定义数据初始化方法，实例化adapter和存储数据的集合，为spinner控件setAdapter()
4.设置要级联的Spinner的setOnItemSelectedListener()监听事件
5.主要的逻辑都是在spinner控件的onItemSelected回调函数中完成，在其中加入了一个switch语句，用来判断点击的是哪个spinner的item。


注意：
1.每个Spinner都要实例化（findViewById），每个存储信息的List都要实例化，每个Adapter适配器都要实例化。
2.初始化adapter的时候不要填充错了List集合
3.填充adapter的时候不要填充错了对象。
