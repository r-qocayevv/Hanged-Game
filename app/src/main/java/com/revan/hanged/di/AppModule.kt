package com.revan.hanged.di

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.revan.hanged.data.local.HangedDataStore
import com.revan.hanged.data.repository.DataStorePreferencesRepositoryImpl
import com.revan.hanged.data.repository.FirebaseRepositoryImpl
import com.revan.hanged.domain.repository.DataStorePreferencesRepository
import com.revan.hanged.domain.repository.FirebaseRepository
import com.revan.hanged.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWebSocketClient(): Socket {
        val options = IO.Options.builder()
            .setTransports(arrayOf("websocket"))
            .setUpgrade(true)
            .setRememberUpgrade(true)
            .setForceNew(true)
            .setReconnection(true)
            .setAuth(null)
            .build()

        return IO.socket("http://114.29.236.110:3000", options)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context : Context): HangedDataStore {
        return HangedDataStore(context = context)
    }

    @Provides
    @Singleton
    fun provideDataStorePreferencesRepository(dataStore: HangedDataStore) : DataStorePreferencesRepository {
        return DataStorePreferencesRepositoryImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideFirestore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth  () : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideNavigator () : Navigator {
        return Navigator()
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository (firestore : FirebaseFirestore,firebaseAuth: FirebaseAuth) : FirebaseRepository {
        return FirebaseRepositoryImpl(firestore,firebaseAuth)
    }

}