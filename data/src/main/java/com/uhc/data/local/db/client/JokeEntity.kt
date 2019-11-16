package com.uhc.data.local.db.client

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Constancio on 2019-11-16.
 */
@Entity(tableName = "joke")
data class JokeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null

    // TODO: add other columns
)