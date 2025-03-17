package com.senkou.framework

import android.app.Application
import androidx.room.Room
import com.senkou.data.BackgroundDataSource
import com.senkou.data.LocalDataSource
import com.senkou.data.PlayVideoDataSource
import com.senkou.data.RemoteDataSource
import com.senkou.framework.local.room.RoomMovieDataSource
import com.senkou.framework.local.room.db.MoviesDB
import com.senkou.framework.remote.arte7.WebMovieDatasource
import com.senkou.framework.remote.tmdb.TmdbClient
import com.senkou.framework.remote.tmdb.TmdbServerDataSource
import com.senkou.framework.remote.youtube.YoutubeDatasource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkModule {

   @Provides
   fun providesMovieDao(db: MoviesDB) = db.movieDao()

   @Provides
   fun providesSessionDao(db: MoviesDB) = db.sessionDao()

   @Provides
   fun providesUpcomingMoviesDao(db: MoviesDB) = db.upcomingMoviesDao()

   @Provides
   fun providesTmdbService(@Named("base_url") baseUrl: String) = TmdbClient(baseUrl).instance

   @Provides
   fun providesYoutubeService(app: Application) = YoutubeDatasource(app)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BindsModule {

   @Binds
   abstract fun bindsLocalDataSource(localDataSource: RoomMovieDataSource): LocalDataSource

   @Binds
   abstract fun bindsRemoteDataSource(remoteDataSource: WebMovieDatasource): RemoteDataSource

   @Binds
   abstract fun bindsBackgrundDataSource(backgroundDataSource: TmdbServerDataSource): BackgroundDataSource

   @Binds
   abstract fun bindsPlayVideoDataSource(playVideoDataSource: YoutubeDatasource): PlayVideoDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object FrameworkExtrasModule {

   @Provides
   @Singleton
   fun provideDb(app: Application) =
      Room.databaseBuilder(
         context = app,
         klass = MoviesDB::class.java,
         name = "Cartelera"
      ).build()

   @Provides
   @Singleton
   @Named("base_url")
   fun provideBaseUrl() = "https://api.themoviedb.org/3/"
}