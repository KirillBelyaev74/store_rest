package ru.store.store_rest

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

suspend fun main(){
    getUsers().collect { user -> println(user) }
}

fun getUsers(): Flow<String> {
    return flow {
        val database = listOf("Tom", "Bob", "Sam")  // условная база данных
        var i = 1;
        for (item in database) {
            delay(800L) // имитация продолжительной работы
            println("Emit $i item")
//            emit(item) // емитируем значение
            i++
        }
    }

}