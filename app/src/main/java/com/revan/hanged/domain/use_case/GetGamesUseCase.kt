package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.repository.HangedRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val hangedRepository: HangedRepository

){
    suspend operator fun invoke () : List<Game> {
        return hangedRepository.getGames()
    }

}