package shevtsov.daniil.incrementalreader.learning.view

import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import shevtsov.daniil.incrementalreader.learning.domain.AddRepetitionUseCase
import shevtsov.daniil.incrementalreader.learning.domain.GetScoreListUseCase
import shevtsov.daniil.incrementalreader.learning.presentation.LearningViewModel
import shevtsov.daniil.incrementalreader.learning.usecase.GetCalculatedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase

internal class LearningFragmentTest {

    private lateinit var viewModel: LearningViewModel

    private val getScoreListUseCase: GetScoreListUseCase = mockk()
    private val getCalculatedItemUseCase: GetCalculatedItemUseCase = mockk()
    private val getSavedItemUseCase: GetSavedItemUseCase = mockk()
    private val addRepetitionUseCase: AddRepetitionUseCase = mockk()

    @BeforeEach
    fun onSetup() {
        viewModel = LearningViewModel(
            getScoreList = getScoreListUseCase,
            getCalculatedItem = getCalculatedItemUseCase,
            getSavedItem = getSavedItemUseCase,
            addRepetition = addRepetitionUseCase,
        )
    }

    @Test
    fun `lol - kek`() {

        viewModel.state.test {

        }

    }

}