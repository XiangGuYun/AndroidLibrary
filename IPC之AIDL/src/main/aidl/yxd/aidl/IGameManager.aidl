// IGameManager.aidl
package yxd.aidl;
import yxd.aidl.Game;
// Declare any non-default types here with import statements
/*
在AIDL文件中支持的数据类型包括：

基本数据类型
String和CharSequence
List:只支持ArrayList,里面的元素都必须被AIDL支持
Map:只支持HashMap,里面的元素必须被AIDL 支持
实现Parcelable接口的对象
所有AIDL接口

app–>build–>generated–>soure–>aidl–>debug目录下我们找到自己的包名文件，在文件中有一个接口文件IGameManager
*/
interface IGameManager {
   List<Game>getGameList();
   void addGame(in Game game);
}
