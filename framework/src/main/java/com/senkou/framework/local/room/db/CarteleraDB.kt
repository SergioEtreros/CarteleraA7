package com.senkou.framework.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senkou.framework.local.room.dao.PeliculaDao
import com.senkou.framework.local.room.dao.ProximoEstrenoDao
import com.senkou.framework.local.room.dao.SesionDao
import com.senkou.framework.local.room.entities.PeliculaEntity
import com.senkou.framework.local.room.entities.ProximoEstrenoEntity
import com.senkou.framework.local.room.entities.SesionEntity

@Database(
   entities = [
      PeliculaEntity::class,
      ProximoEstrenoEntity::class,
      SesionEntity::class
   ],
   version = 1,
   exportSchema = true,
   autoMigrations = []
)
abstract class CarteleraDB : RoomDatabase() {
   abstract fun peliculaDao(): PeliculaDao
   abstract fun sesionDao(): SesionDao

   abstract fun proximoEstrenoDao(): ProximoEstrenoDao
}