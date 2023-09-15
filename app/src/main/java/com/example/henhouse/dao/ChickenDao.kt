package com.example.henhouse.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.henhouse.entity.Chicken
import kotlinx.coroutines.flow.Flow

@Dao
interface ChickenDao {
    @Query("SELECT * FROM chicken")
    fun getAll(): List<Chicken>

    @Query("SELECT name FROM chicken")
    fun getAllName(): List<String>

    @Query("SELECT * FROM chicken WHERE id IN (:chickenIds)")
    fun loadAllByIds(chickenIds: IntArray): List<Chicken>

    @Query("SELECT * FROM chicken WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Chicken

    @Insert
    fun insertAll(vararg chickens: Chicken)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chicken: Chicken)

    @Update
    fun updateChickens(vararg chicken: Chicken)

    @Delete
    fun delete(user: Chicken)

    @Query("DELETE FROM chicken WHERE id = :id")
    fun deleteById(id: Int)
}