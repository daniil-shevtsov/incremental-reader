package shevtsov.daniil.incrementalreader.learning.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shevtsov.daniil.incrementalreader.storage.domain.InformationItem
import shevtsov.daniil.incrementalreader.structure.domain.GetInformationItemsUseCase
import javax.inject.Inject

class GetCalculatedItemUseCase @Inject constructor(
    private val getInformationItemsUseCase: GetInformationItemsUseCase
) {

    operator fun invoke(): Flow<InformationItem?> = getInformationItemsUseCase.invoke()
        .map { items -> items.maxByOrNull { item -> item.name } }

}
