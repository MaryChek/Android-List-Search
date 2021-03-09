package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.PagerContract
import com.example.favorite_cities.model.CitiesModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class PagerPresenterTest {
    private val model: CitiesModel = mock()
    private val view: PagerContract.View = mock()
    private val presenter: PagerPresenter = PagerPresenter(model, view)

    @Test
    fun `onViewCreated when view created then verify showPagerWithFragments invoked`() {
        presenter.onViewCreated()

        verify(view, times(1))
            .showPagerWithFragments()
    }

    @Test
    fun `onFragmentsShown when fragments are show then verify setFavoriteFragmentAsCurrent invoked`() {
        presenter.onFragmentsShown()

        verify(view, times(1))
            .setCurrentFragmentByPosition(any())
    }

    @Test
    fun `onFragmentsShown when fragments are show then verify setTitlesInTabLayout with map of titles`() {
        presenter.onFragmentsShown()

        verify(view, times(1))
            .setTitlesInTabLayout(LiST_OF_TITLE_RES_IDS)
    }

    companion object {
        private const val COUNT_FRAGMENTS = 2
        private val LiST_OF_TITLE_RES_IDS = listOf(
            R.string.name_fragment_general,
            R.string.name_fragment_favorite
        )
    }
}