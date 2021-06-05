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

    @Test
    fun `when the same score consecutive streak - then returns correct streak`() {
        val scores = listOf(0, 0, 0, 1, 3, 3, 3, 0, 4, 4, 4, 4).toScores()
        val expectedStreak = 4

        val streak = usecase.findAnswerStreak(scores)

        assertEquals(expectedStreak, streak)
    }

    @Test
    fun `when the numbers are not consecutive - then doesn't count as streak`() {
        val scores = listOf(3, 3, 3, 0, 3, 3, 3, 0, 4, 4, 4, 4).toScores()
        val expectedStreak = 4

        val streak = usecase.findAnswerStreak(scores)

        assertEquals(expectedStreak, streak)
    }

    @Test
    fun `when the different score consecutive streak - then returns correct streak`() {
        val scores = listOf(0, 0, 0, 1, 3, 3, 3, 4, 5, 4, 3, 0, 4, 4, 4, 4).toScores()
        val expectedStreak = 7

        val streak = usecase.findAnswerStreak(scores)

        assertEquals(expectedStreak, streak)
    }

    @Test
    fun `when find last group - last group found`() {
        val expectedGroup = mutableListOf(5, -1, -2)
        val groups: MutableList<MutableList<Int>> = mutableListOf<MutableList<Int>>(
            mutableListOf(3, -1, 2),
            expectedGroup,
            mutableListOf(1, 2, 3),
        )

        val group = groups.findLastGroup { it < 0}

        assertEquals(expectedGroup, group)
    }

    @Test
    fun `when forming groups - correct groups are formed`() {
        val originalList = listOf(1,2,3,0,4,5,6)
        val expectedGroups = listOf(listOf(1,2,3),listOf(4,5,6))

        val groups = originalList.formGroups { it != 0 }

        assertEquals(expectedGroups, groups)
    }

    private fun List<Int>.toScores() = map { ScoreValue(it.toLong()) }

    private companion object {
        val DELTA = 0.0001
    }

}