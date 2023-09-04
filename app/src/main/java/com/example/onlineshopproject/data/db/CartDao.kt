package com.example.onlineshopproject.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshopproject.data.model.basket.CartItem
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart : CartItem)

    @Query("select * from shopping_cart_table")
    fun getAllItems() : Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(item :CartItem)

    @Query("update shopping_cart_table set count =:newCount where id=:id")
    suspend fun changeCountCartItem(id : String , newCount : Int)

    @Query("select total(count) as count from shopping_cart_table")
    fun getCartItemCount() : Flow<Int>

    @Query("DELETE FROM shopping_cart_table")
    suspend fun deleteAllItems()

}