package org.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class ContactEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val firstName: String,
    val lastName: String,
    val pictureUrl: String,
    val thumbnailUrl: String,
    val age: Int,
    val nationality: String,
    val gender: String,
    val userName: String,
    val phone: String,
    val cell: String,
    val email: String,
    val country: String,
    val city: String,
    val state: String,
)