package com.example.newsapp.ui.database

import android.content.Context
import androidx.room.*

//
//@Database(
//    entities = [ArticleModel::class],
//    version = 1,
//    exportSchema = false
//)

//@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {

//
//    abstract fun getDao(): Dao
//
//    companion object {
//        @Volatile
//        private var instance: DataBase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDataBase(context).also { instance = it }
//        }
//
//        private fun createDataBase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                DataBase::class.java,
//                "article.db"
//            ).build()
//
//    }

}