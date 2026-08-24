package orvexretail.clothing.orvexstylekey.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import orvexretail.clothing.orvexstylekey.data.dao.CartItemDao
import orvexretail.clothing.orvexstylekey.data.dao.OrderDao
import orvexretail.clothing.orvexstylekey.data.database.converter.Converters
import orvexretail.clothing.orvexstylekey.data.entity.CartItemEntity
import orvexretail.clothing.orvexstylekey.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ZLFJRDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}