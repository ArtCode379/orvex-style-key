package orvexretail.clothing.orvexstylekey.di

import androidx.room.Room
import orvexretail.clothing.orvexstylekey.data.database.ZLFJRDatabase
import org.koin.dsl.module

private const val DB_NAME = "zlfjr_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = ZLFJRDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<ZLFJRDatabase>().cartItemDao() }

    single { get<ZLFJRDatabase>().orderDao() }
}