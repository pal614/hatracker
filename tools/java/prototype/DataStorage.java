package prototype;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;

/**
 *
 * @author pal
 */
public class DataStorage
{
    static final String APP_NAME = "headachetracker.appspot.com";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        String username = // "user.name@gmail.com"
                System.console().readLine("username: ");
        String password = //"somepassword";
                new String(System.console().readPassword("password: "));
        RemoteApiOptions options = new RemoteApiOptions().server(
                APP_NAME, 443).credentials(username, password);
        RemoteApiInstaller installer = new RemoteApiInstaller();
        installer.install(options);
        try
        {
            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
            //addData(ds);
            fetchData(ds);
        } finally
        {
            installer.uninstall();
        }

    }

    public static void addData(DatastoreService ds)
    {
        Transaction trans = ds.beginTransaction();
        //Adding user
        Entity user = new Entity("User", "userId");
        user.setProperty("userId", "123");
        user.setUnindexedProperty("gender", "m");
        Key uk = ds.put(trans, user);
        System.out.println("Key of new entity is " + uk);
        //Now add preferences
        Entity prefs = new Entity("Prefs", uk);
        prefs.setProperty("somePref", true);
        Key pk = ds.put(trans, prefs);
        System.out.println("Key of new pref " + pk);
        trans.commit();
    }

    public static void fetchData(DatastoreService ds)
    {
        Query userQuery = new Query("User");
        userQuery.addFilter("userId", Query.FilterOperator.EQUAL, "123");
        
        PreparedQuery userPQ = ds.prepare(userQuery);
        for (Entity u : userPQ.asIterable())
        {
            System.out.println("User: " + u);
            
            Key uk = u.getKey();
            Query propQ = new Query("Prefs", uk);
            PreparedQuery propPQ = ds.prepare(propQ);
            Entity p = propPQ.asIterator().next();
            System.out.println("Prefs: " + p);
        }
    }
}
