package com.revan.hanged.di

import com.google.firebase.firestore.FirebaseFirestore
import com.revan.hanged.data.repository.FirebaseRepositoryImpl
import com.revan.hanged.domain.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideFirestore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository (firestore : FirebaseFirestore) : FirebaseRepository {
        return FirebaseRepositoryImpl(firestore)
    }

}