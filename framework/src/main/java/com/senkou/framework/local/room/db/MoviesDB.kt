package com.senkou.framework.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senkou.framework.local.room.dao.MovieDao
import com.senkou.framework.local.room.dao.SessionDao
import com.senkou.framework.local.room.dao.UpcomingMoviesDao
import com.senkou.framework.local.room.entities.MovieEntity
import com.senkou.framework.local.room.entities.SessionEntity
import com.senkou.framework.local.room.entities.UpcomingMoviesEntity

@Database(
   entities = [
      MovieEntity::class,
      UpcomingMoviesEntity::class,
      SessionEntity::class
   ],
   version = 1,
   exportSchema = true,
   autoMigrations = []
)
abstract class MoviesDB : RoomDatabase() {
   abstract fun movieDao(): MovieDao
   abstract fun sessionDao(): SessionDao
   abstract fun upcomingMoviesDao(): UpcomingMoviesDao
}