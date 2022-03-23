package com.dmp.senya.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.dmp.senya.R
import com.dmp.senya.data.Attraction
import com.dmp.senya.databinding.EpoxyModelHeaderBinding
import com.dmp.senya.databinding.ViewHolderAttractionBinding
import com.dmp.senya.ui.epoxy.LoadingEpoxyModel
import com.dmp.senya.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HomeFragmentController(
    private val onClickedCallback: (String) -> Unit
) : EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }

        if (attractions.isEmpty()) {
            // todo show empty state
            return
        }

        val firstGroup =
            attractions.filter { it.title.startsWith("s", true) || it.title.startsWith("D", true) }

        HomeEpoxyModel.HeaderEpoxyModel("Recently Viewed").id("header_1").addTo(this)
        firstGroup.forEach { attraction ->
            HomeEpoxyModel.AttractionEpoxyModel(attraction, onClickedCallback)
                .id(attraction.id)
                .addTo(this)
        }

        HomeEpoxyModel.HeaderEpoxyModel("All Attractions").id("header_2").addTo(this)
        attractions.forEach { attraction ->
            HomeEpoxyModel.AttractionEpoxyModel(attraction, onClickedCallback)
                .id(attraction.id)
                .addTo(this)
        }
    }


}