package shevtsov.daniil.incrementalreader.learning.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import shevtsov.daniil.incrementalreader.learning.domain.ScoreValue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CalculateDaysUntilRepetitionUseCaseTest {

    private lateinit var usecase: CalculateDaysUntilRepetitionUseCase

    @BeforeEach
    fun onSetup() {
        usecase = CalculateDaysUntilRepetitionUseCase()
    }

    @Test
    fun testOneRepetition() {
        val testData = listOf(2, 1, 3, 3, 4, 1, 2, 3, 4).toScores()
        val expectedDays = 9.4583

        val days = usecase.invoke(scores = testData)

        assertEquals(expectedDays, days, DELTA)
    }

    private fun List<Int>.toScores() = map { ScoreValue(it.toLong()) }

    private companion object {
        val DELTA = 0.0001
    }

}