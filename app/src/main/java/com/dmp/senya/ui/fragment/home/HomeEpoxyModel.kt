package com.dmp.senya.ui.fragment.home

import com.dmp.senya.R
import com.dmp.senya.data.Attraction
import com.dmp.senya.databinding.EpoxyModelHeaderBinding
import com.dmp.senya.databinding.ViewHolderAttractionBinding
import com.dmp.senya.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

sealed class HomeEpoxyModel{
    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {

        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            if (attraction.image_urls.isNotEmpty()) {
                Picasso.get().load(attraction.image_urls[0]).into(headerImageView)
            } else {
                // better error handling
            }
            monthsToVisitTextView.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }

    data class HeaderEpoxyModel(
        val headerText: String
    ) : ViewBindingKotlinModel<EpoxyModelHeaderBinding>(R.layout.epoxy_model_header) {

        override fun EpoxyModelHeaderBinding.bind() {
            headerTextView.text = headerText
        }
    }
}
