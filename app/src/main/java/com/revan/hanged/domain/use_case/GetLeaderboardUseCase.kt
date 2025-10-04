package com.revan.hanged.domain.use_case

import com.revan.hanged.domain.model.Leaderboard
import com.revan.hanged.domain.repository.HangedRepository
import javax.inject.Inject

class GetLeaderboardUseCase @Inject constructor(
    private val hangedRepository: HangedRepository
) {
    suspend operator fun invoke(): List<Leaderboard> {
        return hangedRepository.getLeaderboard()
    }
}
