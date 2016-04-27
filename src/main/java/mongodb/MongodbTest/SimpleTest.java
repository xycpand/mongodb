package mongodb.MongodbTest;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class SimpleTest {
	public static void main(String[] args) throws UnknownHostException, MongoException {
		//创建了一个MongoDB的数据库连接对象，它默认连接到当前机器的localhost地址，端口是27017
        Mongo mg = new Mongo();
        //查询所有的Database
        for (String name : mg.getDatabaseNames()) {
            System.out.println("dbName: " + name);
        }
        //获得了一个test的数据库，如果没有这个库，mongoDB会自动创建当前数据库
        DB db = mg.getDB("test");
        //查询所有的聚集集合
        for (String name : db.getCollectionNames()) {
            System.out.println("collectionName: " + name);
        }
        //它相当于我们数据库的“表”
        DBCollection users = db.getCollection("users");
        
        //查询所有的数据
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            System.out.println(cur.next());
        }
        System.out.println(cur.count());
        System.out.println(cur.getCursorId());
        System.out.println(JSON.serialize(cur));
    }
}
