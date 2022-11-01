package com.memoir.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.memoir.TestCoroutineScopeProvider
import com.memoir.home.model.Forecast
import com.memoir.home.model.Temperature
import com.memoir.home.model.WeatherResponse
import com.memoir.home.repo.HomeRepository

import com.memoir.home.viewmodel.HomeViewState
import com.memoir.home.viewmodel.WeatherItemViewModel
import com.memoir.home.viewmodel.WeatherViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class BeerViewModelTest {
    @Mock
    lateinit var repository: HomeRepository

    lateinit var viewModel: WeatherViewModel

    private val scopeProvider = TestCoroutineScopeProvider()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = WeatherViewModel(repository, scopeProvider)
    }

    @Test
    fun loadAllSuccess() = runTest {
        whenever(repository.fetch("hcm",WeatherViewModel.LIMIT_DATE))
            .thenReturn(WeatherResponse(listOf(Forecast(0,0,0, emptyList(),
            Temperature(0F,0F)
            ))))
        viewModel.fetch("hcm")
        Assert.assertEquals(
            HomeViewState(
                listOf(
                    WeatherItemViewModel(0, "01/00/1970","0.00 °C",0,0, "","")
                ), false, null
            ), viewModel.store.state
        )
    }

    @Test
    fun loadAnyError() = runTest {
        val exp = RuntimeException()
        whenever(repository.fetch("hcm", WeatherViewModel.LIMIT_DATE)).thenThrow(exp)
        viewModel.fetch("hcm")

        Assert.assertEquals(HomeViewState(null, false, exp), viewModel.store.state)
    }



}