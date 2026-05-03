package com.coursera.capstone

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "menu_items")
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)
@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems(): LiveData<List<MenuItemRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menuItems: List<MenuItemRoom>)

    @Query("SELECT COUNT(*) FROM menu_items")
    suspend fun isEmpty(): Int
}

@Database(entities = [MenuItemRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "little_lemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


// Extension function to map network model to Room entity
fun MenuItemNetwork.toMenuItemRoom() = MenuItemRoom(
    id = id,
    title = title,
    description = description,
    price = price,
    image = image,
    category = category
)
