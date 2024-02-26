package com.example.testonlinestore.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = DataPref.USER_DATA)

class DataStoreManager @Inject constructor (
    private val dataStore : DataStore<Preferences>
) {


    private val USER_ID_KEY = stringPreferencesKey(DataPref.USER_ID_KEY)

    /**
     * Write data from DataStore
     */
    suspend fun saveUserId(userId : String) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }


    /**
     * Read data from DataStore
     */
    val userIdFlow : Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_ID_KEY] ?: ""
    }

}

object DataPref {
    const val USER_ID_KEY = "user_id_key"
    const val USER_DATA = "user_data"
}