package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.PagerContract
import com.example.favorite_cities.model.cities.CitiesModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class PagerPresenterTest {
    private val model: CitiesModel = mock()
    private val view: PagerContract.View = mock()
    private val presenter: PagerPresenter = PagerPresenter(model, view)

    @Test
    fun `onViewCreated when view created then verify selectItemOnPager invoked`() {
        whenever(model.positionOfTheCurrentPage)
            .thenReturn(SIMPLE_POSITION)

        presenter.onViewCreated()

        verify(view, times(1))
            .selectItemOnPager(SIMPLE_POSITION)
    }

    @Test
    fun `onViewCreated when view created then verify setTitlesInTabLayout invoked`() {
        presenter.onViewCreated()

        verify(view, times(1))
            .setTitlesInTabLayout(LiST_OF_TITLE_RES_IDS)
    }

    @Test
    fun `onPageSelected when page selected then verify positionOfTheCurrentPage in model`() {
        presenter.onPageSelected(SIMPLE_POSITION)

        verify(model, times(1))
            .positionOfTheCurrentPage = SIMPLE_POSITION
    }

    companion object {
        private const val SIMPLE_POSITION = 1
        private val LiST_OF_TITLE_RES_IDS = listOf(
            R.string.name_fragment_general,
            R.string.name_fragment_favorite
        )
    }
}