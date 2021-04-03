package com.srg.codetestrickmorty.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.srg.codetestrickmorty.data.features.characters.db.CharactersDao
import com.srg.codetestrickmorty.data.features.characters.db.CharactersRemoteKeys
import com.srg.codetestrickmorty.data.features.characters.db.CharactersRemoteKeysDao
import com.srg.codetestrickmorty.data.features.characters.db.Converters
import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel

@Database(
    entities = [CharacterApiModel::class, CharactersRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RickMortyDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun charactersRemoteKeysDao(): CharactersRemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: RickMortyDatabase? = null

        fun getInstance(context: Context): RickMortyDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RickMortyDatabase::class.java,
            "RickMorty.db"
        ).build()
    }
}