package com.example.testonlinestore.domain.model.registration

import androidx.room.Entity


@Entity(tableName = "user_account")
data class UserAccount(
    val name : String,
    val surname : String,
    val number : Int
)
