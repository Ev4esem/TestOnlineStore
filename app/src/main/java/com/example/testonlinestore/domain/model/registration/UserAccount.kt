package com.example.testonlinestore.domain.model.registration

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_account")
data class UserAccount(
    val name : String,
    val surname : String,
    @PrimaryKey(autoGenerate = true)
    val number : Long
)
