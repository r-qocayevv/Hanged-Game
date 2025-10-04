package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.repository.HangedRepository
import javax.inject.Inject

class GetGameDetailUseCase @Inject constructor(
    private val hangedRepository: HangedRepository
) {
    suspend operator fun invoke(gameId: String): Game {
        return hangedRepository.getGameDetail(gameId)
    }

}