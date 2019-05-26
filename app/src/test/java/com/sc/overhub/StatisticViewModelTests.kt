package com.sc.overhub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sc.overhub.data.repository.ProfileRepository
import com.sc.overhub.domain.usecase.LeaveFromAccountUseCase
import com.sc.overhub.presentation.viewmodel.StatisticViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class StatisticViewModelTests : KoinTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Before
    fun before() {
        startKoin {
            modules(
                module {
                    single {
                        mock(ProfileRepository::class.java).apply {
                            Mockito.`when`(getBattleTag()).thenReturn("")
                        }
                    }
                    single { mock(LeaveFromAccountUseCase::class.java) }
                })
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun simpleTest() {
        val profileRepo by inject<ProfileRepository>()
        assertNotNull(profileRepo)
        assertEquals(profileRepo.getBattleTag(), "")
    }

    @Test
    fun notSimpleTest() {
        val viewModel = StatisticViewModel()

        assertNotNull(viewModel)
    }
}