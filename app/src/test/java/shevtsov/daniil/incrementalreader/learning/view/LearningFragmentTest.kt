package shevtsov.daniil.incrementalreader.learning.view

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import shevtsov.daniil.incrementalreader.learning.domain.AddRepetitionUseCase
import shevtsov.daniil.incrementalreader.learning.domain.GetScoreListUseCase
import shevtsov.daniil.incrementalreader.learning.navigation.LearningInitArguments
import shevtsov.daniil.incrementalreader.learning.presentation.LearningViewAction
import shevtsov.daniil.incrementalreader.learning.presentation.LearningViewModel
import shevtsov.daniil.incrementalreader.learning.usecase.GetCalculatedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LearningFragmentTest {

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
    fun `kek`() = runBlockingTest {
        val calculatedItem = InformationItem(
            id = 0,
            title = "title",
            content = "content",
            creationTime = 0L,
            updateTime = 0L,
            lastReviewTime = 0L,
            parentId = null,
        )
        every { getCalculatedItemUseCase.invoke() } answers { flowOf(calculatedItem) }

        viewModel.onAction(
            LearningViewAction.ProvideArguments(LearningInitArguments.Empty)
        )

        viewModel.state.test {
//            assertEquals(expectItem(), LearningViewState.QuestionOnly("title"))
        }

    }
}