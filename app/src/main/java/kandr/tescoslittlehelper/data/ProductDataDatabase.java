package kandr.tescoslittlehelper.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(
        entities = {ProductData.class},
        version = 3
)
public abstract class ProductDataDatabase extends RoomDatabase {
    public abstract ProductDataDao productDataDao();
}
