package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.FirebaseRepository
import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Room
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository

){
    suspend operator fun invoke () : List<Game> {
        return firebaseRepository.getGames()
    }

}