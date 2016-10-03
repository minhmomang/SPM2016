package EJB;

import java.io.File;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConnectDBLog {
	static DB db = null;
	static MongoClient mongoClient;

	private static void ConnectDB() {
		if (db == null) {
			try {
				mongoClient = new MongoClient("10.0.10.41", 27017);
				db = mongoClient.getDB("dbJboss");
				boolean auth = db.authenticate("vinhsang", "1234".toCharArray());
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public static void close_con() {
		try {
			// db = null;
			// mongoClient.close();
		} catch (Exception e) {
		}
	}

	public static List<DBObject> getColectionByObjCustomer(String colname, DBObject obj, int dsort) {
		ConnectDB();
		List<DBObject> list = null;
		try {
			DBCollection col = db.getCollection(colname);
			DBObject sort = new BasicDBObject("date", dsort); // 1 is ascending
																// and -1 is
																// descending.
			DBCursor cursor = col.find(obj).sort(sort);
			list = cursor.toArray();
			close_con();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;

	}

	public static List<DBObject> getColectionByObj(String colname, DBObject obj) {
		ConnectDB();
		List<DBObject> list = null;
		try {
			DBCollection col = db.getCollection(colname);
			DBCursor cursor = col.find(obj);
			list = cursor.toArray();
			close_con();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;

	}

	public static AggregationOutput GroupByCollection(String colname, DBObject matchFields, DBObject groupFields) {
		ConnectDB();
		AggregationOutput output = null;
		try {

			// DBObject matchFields = new
			// BasicDBObject("email",email).append("parent",parent);
			// DBObject groupFields = new BasicDBObject("_id","$date");
			DBObject group = new BasicDBObject("$group", groupFields);
			DBObject match = new BasicDBObject("$match", matchFields);
			DBCollection col = db.getCollection(colname);
			output = col.aggregate(match, group);
			close_con();
		} catch (Exception ex) {

		}

		return output;

	}

	public static AggregationOutput GroupByCollectionNoWhere(String colname, DBObject groupFields) {
		ConnectDB();
		AggregationOutput output = null;
		DBObject group = new BasicDBObject("$group", groupFields);
		DBCollection col = db.getCollection(colname);
		output = col.aggregate(group);
		close_con();
		return output;

	}

	// return list;
	public static List<DBObject> getColectoinReturnList(String colection) {
		ConnectDB();
		List<DBObject> list = null;
		try {
			DBCollection col = db.getCollection(colection);
			DBCursor cursor = col.find();
			list = cursor.toArray();
			close_con();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	// return list;
	// obj same as query
	public static List<DBObject> getColectoinReturnList(String colection, BasicDBObject obj, Boolean sort,
			BasicDBObject objsort) {
		ConnectDB();
		List<DBObject> list = null;
		try {
			DBCollection col = db.getCollection(colection);
			DBCursor cursor = col.find(obj);
			if (sort == true) {
				cursor.sort(objsort);
			}
			list = cursor.toArray();
			close_con();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static List<DBObject> getTopColectoinReturnList(String colection, BasicDBObject obj, int n) {
		ConnectDB();
		List<DBObject> list = null;
		try {
			DBCollection col = db.getCollection(colection);
			DBCursor cursor = col.find(obj).limit(n);
			list = cursor.toArray();
			close_con();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static int insert(String colection, String[] fields, Object[] values) {
		ConnectDB();
		try {
			if (fields.length == 0) {
				return -1;
			}
			if (fields.length != values.length) {
				return -1;
			}
			BasicDBObject document = new BasicDBObject();
			for (int i = 0; i < fields.length; i++) {
				document.put(fields[i], values[i]);
			}
			DBCollection col = db.getCollection(colection);
			col.insert(document);
			close_con();
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public static int update(String colection, String[] fields, Object[] values, int[] ptype) {
		ConnectDB();
		try {
			if (fields.length == 0) {
				return -1;
			}
			if (fields.length != values.length) {
				return -1;
			}
			if (fields.length != ptype.length) {
				return -1;
			}
			BasicDBObject document_set = new BasicDBObject();
			BasicDBObject document_where = new BasicDBObject();
			for (int i = 0; i < fields.length; i++) {
				if (ptype[i] == 0) {// set
					document_set.append("$set", new BasicDBObject().append(fields[i], values[i]));
				} else if (ptype[i] == 1) {// where
					document_where.append(fields[i], values[i]);
				}

			}
			DBCollection col = db.getCollection(colection);
			col.update(document_where, document_set);
			close_con();
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public static int remove(String colection, String[] fields, Object[] values) {
		ConnectDB();
		try {
			if (fields.length == 0) {
				return -1;
			}
			if (fields.length != values.length) {
				return -1;
			}
			BasicDBObject document = new BasicDBObject();
			for (int i = 0; i < fields.length; i++) {
				document.put(fields[i], values[i]);
			}
			DBCollection col = db.getCollection(colection);
			col.remove(document);
			close_con();
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
