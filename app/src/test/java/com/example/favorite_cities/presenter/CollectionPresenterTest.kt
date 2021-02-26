package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class CollectionPresenterTest {
    private val model: CitiesModel = mock()
    private val view: CollectionContract.View = mock()
    private val presenter: CollectionPresenter = CollectionPresenter(model, view)

    @Test
    fun `onViewCreated when view created then verify showPagerWithFragments with count fragments`() {
        presenter.onViewCreated()

        verify(view, times(1))
            .showPagerWithFragments(COUNT_FRAGMENTS)
    }

    @Test
    fun `onFragmentsShown when getVisibleFavoriteFragment returned "true" then verify setFavoriteFragmentAsCurrent invoked`() {
        whenever(model.getVisibleFavoriteFragment())
            .thenReturn(true)

        presenter.onFragmentsShown()

        verify(view, times(1))
            .setFavoriteFragmentAsCurrent(FAVORITE_FRAGMENT_POSITION)
    }

    @Test
    fun `onFragmentsShown when getVisibleFavoriteFragment returned "false" then verify setFavoriteFragmentAsCurrent not invoked`() {
        whenever(model.getVisibleFavoriteFragment())
            .thenReturn(false)

        presenter.onFragmentsShown()

        verify(view, times(0))
            .setFavoriteFragmentAsCurrent(any())
    }
    @Test
    fun `onFragmentsShown when fragments show then verify addTitlesInTabLayout with map of titles`() {
        presenter.onFragmentsShown()

        verify(view, times(1))
            .addTitlesInTabLayout(MAP_OF_FRAGMENTS_TITLES)
    }

    companion object {
        private const val COUNT_FRAGMENTS = 2
        private val MAP_OF_FRAGMENTS_TITLES = arrayOf(
            R.string.name_fragment_general,
            R.string.name_fragment_favorite
        )
        const val FAVORITE_FRAGMENT_POSITION = 1
    }
}