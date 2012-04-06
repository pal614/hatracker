package hatracher.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import java.util.Iterator;

/**
 *
 * @author pal
 */
public class User
{
    public static Entity getUser(String openId)
    {
        Entity user = null;
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Query userQuery = new Query("User");
        userQuery.addFilter("openId", Query.FilterOperator.EQUAL, openId);
        PreparedQuery userPQ = ds.prepare(userQuery);
        Iterator<Entity> iterator = userPQ.asIterator();
        if (iterator.hasNext())
        {
            user = iterator.next();
        }
        return user;
    }
    public static long addUser(String openId, String gender)
    {
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Transaction trans = ds.beginTransaction();
        Entity user = new Entity("User", "openId");
        user.setProperty("openId", openId);
        user.setUnindexedProperty("gender", gender);
        Key uk = ds.put(trans, user);
        return uk.getId();
    }
}
