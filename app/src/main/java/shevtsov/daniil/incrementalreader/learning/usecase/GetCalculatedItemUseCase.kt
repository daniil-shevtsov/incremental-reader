package shevtsov.daniil.incrementalreader.learning.usecase

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import shevtsov.daniil.incrementalreader.learning.domain.GetItemRepetitionsUseCase
import shevtsov.daniil.incrementalreader.learning.domain.ItemScores
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import shevtsov.daniil.incrementalreader.structure.domain.GetInformationItemsUseCase
import javax.inject.Inject

class GetCalculatedItemUseCase @Inject constructor(
    private val getInformationItemsUseCase: GetInformationItemsUseCase,
    private val getItemRepetitionsUseCase: GetItemRepetitionsUseCase,
    private val calculateDaysUntilRepetitionUseCase: CalculateDaysUntilRepetitionUseCase,
) {

    suspend operator fun invoke(): InformationItem? = getInformationItemsUseCase.invoke()
        .map { items -> chooseItem(items) }
        .single()

    private suspend fun chooseItem(items: List<InformationItem>): InformationItem? {
        val repetitions = items.filter { it.id != null }
            .map { item ->
                ItemScores(
                    itemId = item.id!!,
                    scores = getItemRepetitionsUseCase(item.id).map { it.score })
            }

        return repetitions.map { it.itemId to calculateDaysUntilRepetitionUseCase.invoke(it.scores) }
            .minByOrNull { it.second }
            ?.run { items.find { it.id == first } }
    }

}
