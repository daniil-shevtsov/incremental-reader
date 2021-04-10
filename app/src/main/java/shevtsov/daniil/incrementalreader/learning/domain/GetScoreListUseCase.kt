package shevtsov.daniil.incrementalreader.learning.domain

import javax.inject.Inject

class GetScoreListUseCase @Inject constructor(

) {

    operator fun invoke() = (0..5)
        .map {
            Score(
                id = it.toLong(),
                value = it.toLong()
            )
        }


}