package com.memoir.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.memoir.TestCoroutineScopeProvider
import com.memoir.home.model.Property
import com.memoir.home.repo.HomeRepository

import com.memoir.home.viewmodel.HomeViewState
import com.memoir.home.viewmodel.PropertiesViewModel
import com.memoir.home.viewmodel.PropertyItemViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever

class PropertiesViewModelTest {
    @Mock
    lateinit var repository: HomeRepository

    lateinit var viewModel: PropertiesViewModel

    private val scopeProvider = TestCoroutineScopeProvider()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = PropertiesViewModel(repository, scopeProvider)
    }

    @Test
    fun loadAllSuccess() = runTest {
        whenever(repository.fetch())
            .thenReturn(listOf(Property.EMPTY))
        viewModel.fetch()
        Assert.assertEquals(
            HomeViewState(
                listOf(PropertyItemViewModel("id", false, "location", "CHF0", emptyList(), "title")), false, null
            ), viewModel.store.state
        )
    }

    @Test
    fun loadAnyError() = runTest {
        val exp = RuntimeException()
        whenever(repository.fetch()).thenThrow(exp)
        viewModel.fetch()

        Assert.assertEquals(HomeViewState(null, false, exp), viewModel.store.state)
    }

    @Test
    fun clickFav() = runTest {
        val list = listOf(Property.EMPTY)
        whenever(repository.fetch())
            .thenReturn(list)
        viewModel.fetch()

        val itemClicked = viewModel.store.state.content!!.first()
        doNothing().whenever(repository).favorite("id", false)
        viewModel.onFavClickedAt(itemClicked)


        Assert.assertEquals(
            HomeViewState(
                listOf(PropertyItemViewModel("id", true, "location", "CHF0", emptyList(), "title")), false, null
            ), viewModel.store.state
        )
    }

}