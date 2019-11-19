package com.uhc.data.local.db.joke

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Constancio on 2019-11-16.
 */
@Entity(tableName = "joke")
data class JokeEntity(
    @PrimaryKey
    val id: Long,

    val joke: String
)