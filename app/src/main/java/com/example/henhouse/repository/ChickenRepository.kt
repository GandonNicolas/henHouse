package com.example.henhouse.repository

import com.example.henhouse.dao.ChickenDao
import com.example.henhouse.entity.Chicken

class ChickenRepository(private val chickenDao: ChickenDao) {
    val allChickens: List<Chicken> = chickenDao.getAll()
}