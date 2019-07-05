package com.sc.overhub.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.sc.overhub.R
import com.sc.overhub.data.repository.ProfileRepository
import com.sc.overhub.domain.model.statistics.FullStatistic
import com.sc.overhub.domain.model.statistics.achivments.Achievements
import com.sc.overhub.domain.model.statistics.heroes.HeroesStats
import com.sc.overhub.domain.usecase.LeaveFromAccountUseCase
import com.sc.overhub.domain.usecase.LeaveFromAccountUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class StatisticViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    companion object {
        var score = "1234"
    }

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        score = "1234"
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

    @Before
    fun before() {
        startKoin {
            modules(
                module {
                    single { MockRepo() } bind ProfileRepository::class
                    single { LeaveFromAccountUseCaseImpl() } bind LeaveFromAccountUseCase::class
                })
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getScore() {
        val owner = LifecycleOwner {
            LifecycleRegistry(mock(LifecycleOwner::class.java)).apply {
                handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }
        }
        val viewModel = StatisticViewModel()

        viewModel.score.observe(owner, Observer {
            assertEquals("1234", it)
        })
    }

    @Test
    fun getNickname() {
        val viewModel = StatisticViewModel()
        assertEquals("X#123", viewModel.nickname.value)
    }

    @Test
    fun getImageSrcIdBronze() {
        val owner = LifecycleOwner {
            LifecycleRegistry(mock(LifecycleOwner::class.java)).apply {
                handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }
        }
        val viewModel = StatisticViewModel()

        viewModel.imageSrcId.observe(owner, Observer {
            assertEquals(R.drawable.bronze, it)
        })
    }

    @Test
    fun getImageSrcIdSilver() {
        score = "1501"
        val owner = LifecycleOwner {
            LifecycleRegistry(mock(LifecycleOwner::class.java)).apply {
                handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }
        }
        val viewModel = StatisticViewModel()

        viewModel.imageSrcId.observe(owner, Observer {
            assertEquals(R.drawable.silver, it)
        })
    }

    @Test
    fun getImageSrcIdGold() {
        score = "2030"
        val owner = LifecycleOwner {
            LifecycleRegistry(mock(LifecycleOwner::class.java)).apply {
                handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }
        }
        val viewModel = StatisticViewModel()

        viewModel.imageSrcId.observe(owner, Observer {
            assertEquals(R.drawable.gold, it)
        })
    }

    @Test
    fun getImageSrcIdPlatinum() {
        score = "2630"
        val owner = LifecycleOwner {
            LifecycleRegistry(mock(LifecycleOwner::class.java)).apply {
                handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }
        }
        val viewModel = StatisticViewModel()

        viewModel.imageSrcId.observe(owner, Observer {
            assertEquals(R.drawable.platinum, it)
        })
    }

    @Test
    fun getImageSrcIdGm() {
        score = "4530"
        val owner = LifecycleOwner {
            LifecycleRegistry(mock(LifecycleOwner::class.java)).apply {
                handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }
        }
        val viewModel = StatisticViewModel()

        viewModel.imageSrcId.observe(owner, Observer {
            assertEquals(R.drawable.gm, it)
        })
    }

    @Test
    fun leaveFromAccount() {
        var a = 0
        val viewModel = StatisticViewModel()
        assertEquals("X#123", viewModel.nickname.value)
        viewModel.leaveFromAccount { a = 1 }

        val viewModel2 = StatisticViewModel()

        assertEquals(1, a)
        assertEquals("", viewModel2.nickname.value)
    }

    class MockRepo : ProfileRepository {
        var tag = "X#123"
        override fun getBattleTag(): String {
            return tag
        }

        override fun setBattleTag(battleTag: String): Boolean {
            return true
        }

        override fun getCashedScore(): String {
            return score
        }

        override fun clearBattleTag() {
            tag = ""
        }

        override suspend fun getPlayerScore(): String {
            return score
        }

        override suspend fun getHeroesStats(): HeroesStats? {
            return null
        }

        override suspend fun getAchievementsStats(): Achievements? {
            return null
        }

        override suspend fun getAllStats(): FullStatistic? {
            return null
        }
    }
}